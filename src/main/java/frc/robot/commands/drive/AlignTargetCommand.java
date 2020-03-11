package frc.robot.commands.drive;

import com.torontocodingcollective.commands.TDifferentialDrive;
import com.torontocodingcollective.commands.TSafeCommand;
import com.torontocodingcollective.speedcontroller.TSpeeds;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConst;
import frc.robot.oi.OI;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Default drive command for a drive base
 */
public class AlignTargetCommand extends TSafeCommand {

    PIDController turnController;

    private static final String COMMAND_NAME =
            DefaultDriveCommand.class.getSimpleName();

    private static final double limeLightTime = 0.4;

    OI                oi                = Robot.oi;
    DriveSubsystem driveSubsystem    = Robot.driveSubsystem;

    TDifferentialDrive differentialDrive = new TDifferentialDrive();

    public AlignTargetCommand() {
        // The drive logic will be handled by the TDefaultDriveCommand
        // which also contains the requires(driveSubsystem) statement
        super(Robot.oi);
        requires(Robot.driveSubsystem);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    protected String getParmDesc() {
        return super.getParmDesc();
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {

        super.initialize();

        // Print the command parameters if this is the current
        // called command (it was not sub-classed)
        if (getCommandName().equals(COMMAND_NAME)) {
            logMessage(getParmDesc() + " starting");
        }

        turnController = new PIDController(RobotConst.VISION_TARGET_PID_KP, RobotConst.VISION_TARGET_PID_KI, RobotConst.VISION_TARGET_PID_KD);

        turnController.setSetpoint(0.0);
        turnController.setTolerance(0.5);

        oi.startRumble();
        Robot.cameraSubsystem.setLightOn (true);

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        if (timeSinceInitialized() < limeLightTime) {
            return;
        }

        double turn = turnController.calculate(Robot.cameraSubsystem.getTargetX());

        SmartDashboard.putNumber("Turn Speed", turn);

        driveSubsystem.setSpeed(new TSpeeds(-turn ,turn));
    }

    @Override
    protected boolean isFinished() {

        // End the command early if the driver is driving
        if (Robot.oi.isDriverDriving()) {
            return true;
        }
        if (timeSinceInitialized() < limeLightTime) {
            return false;
        }
        return turnController.atSetpoint();

    }

    @Override
    protected void end() {
        driveSubsystem.setSpeed(0,0);
        oi.endRumble();

        // In auto, do not turn the light off
        if (!DriverStation.getInstance().isAutonomous()) {
            Robot.cameraSubsystem.setLightOn(false);
        }

    }
}
