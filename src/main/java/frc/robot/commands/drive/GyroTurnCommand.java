package frc.robot.commands.drive;

import com.kauailabs.navx.frc.AHRS;
import com.torontocodingcollective.commands.TDefaultDriveCommand;
import com.torontocodingcollective.commands.TDifferentialDrive;
import com.torontocodingcollective.commands.TSafeCommand;
import com.torontocodingcollective.oi.TStick;
import com.torontocodingcollective.oi.TStickPosition;
import com.torontocodingcollective.speedcontroller.TSpeeds;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConst;
import frc.robot.oi.OI;
import frc.robot.oi.OI.TestMode;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Default drive command for a drive base
 */
public class GyroTurnCommand extends TSafeCommand {
	private AHRS ahrs;
	PIDController turnController;
	double rotateToAngleRate;
	double setpoint;  
	
    private static final String COMMAND_NAME =
            DefaultDriveCommand.class.getSimpleName();

    OI                oi                = Robot.oi;
    DriveSubsystem driveSubsystem    = Robot.driveSubsystem;

    TDifferentialDrive differentialDrive = new TDifferentialDrive();

    public GyroTurnCommand(double setpoint) {
        // The drive logic will be handled by the TDefaultDriveCommand
        // which also contains the requires(driveSubsystem) statement
    	super(Robot.oi);
        requires(Robot.driveSubsystem);
        this.setpoint = setpoint;
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
    	
    	turnController = new PIDController(RobotConst.DRIVE_GYRO_PID_KP, RobotConst.DRIVE_GYRO_PID_KI, RobotConst.DRIVE_GYRO_PID_KD);
        turnController.enableContinuousInput(-180.0f,  180.0f);

        // Print the command parameters if this is the current
        // called command (it was not sub-classed)
        if (getCommandName().equals(COMMAND_NAME)) {
            logMessage(getParmDesc() + " starting");
        }
        turnController.setSetpoint(setpoint);
        ahrs = new AHRS();
        super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
//    	turnController.calculate(driveSubsystem.getGyroAngle());
    	
    	SmartDashboard.putNumber("Angle Angle", ahrs.getAngle());
    	}

    @Override
    protected boolean isFinished() {
        // The default command does not end
        return false;
    }
}
