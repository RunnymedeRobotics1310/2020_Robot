package frc.robot.commands.drive;

import com.torontocodingcollective.commands.TDefaultDriveCommand;
import com.torontocodingcollective.commands.TDifferentialDrive;
import com.torontocodingcollective.oi.TStick;
import com.torontocodingcollective.oi.TStickPosition;
import com.torontocodingcollective.speedcontroller.TSpeeds;

import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;
import frc.robot.RobotConst;
import frc.robot.commands.carousel.IntakeCarouselCommand;
import frc.robot.commands.intake.FeederExtakeCommand;
import frc.robot.commands.tower.IntakeTowerCommand;
import frc.robot.oi.OI;
import frc.robot.oi.OI.TestMode;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Default drive command for a drive base
 */
public class DefaultDriveCommand extends TDefaultDriveCommand {

    private static final String COMMAND_NAME =
            DefaultDriveCommand.class.getSimpleName();

    OI                oi                = Robot.oi;
    DriveSubsystem driveSubsystem    = Robot.driveSubsystem;

    TDifferentialDrive differentialDrive = new TDifferentialDrive();

    public DefaultDriveCommand() {
        // The drive logic will be handled by the TDefaultDriveCommand
        // which also contains the requires(driveSubsystem) statement
        super(Robot.oi, Robot.driveSubsystem);
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

        // Print the command parameters if this is the current
        // called command (it was not sub-classed)
        if (getCommandName().equals(COMMAND_NAME)) {
            logMessage(getParmDesc() + " starting");
        }

        super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        if (Robot.oi.isTestModeEnabled()) {
            if (Robot.oi.getTestMode() == TestMode.LEFT_DRIVE) {
                Robot.driveSubsystem.setSpeed(Robot.oi.getTestMotorSpeed(), 0);
            }
            else if (Robot.oi.getTestMode() == TestMode.RIGHT_DRIVE) {
                Robot.driveSubsystem.setSpeed(0, Robot.oi.getTestMotorSpeed());
            }
            else {
                Robot.driveSubsystem.setSpeed(0, 0);
            }
            return;
        }

        // super method implements POV turning and compressor and 
        // PID toggles, and encoder and gyro reset.
        super.execute();
        
        // Align to a vision target
        if (Robot.oi.alignTarget()) {
            Scheduler.getInstance().add(new AlignTargetCommand());
        }
        
        // Drive according to the type of drive selected in the
        // operator input.
        TStickPosition leftStickPosition = oi.getDriveStickPosition(TStick.LEFT);
        TStickPosition rightStickPosition = oi.getDriveStickPosition(TStick.RIGHT);

        TStick singleStickSide = oi.getSelectedSingleStickSide();

        TSpeeds motorSpeeds;
        switch (oi.getSelectedDriveType()) {
        
        case SINGLE_STICK:
            TStickPosition singleStickPosition = rightStickPosition;
            if (singleStickSide == TStick.LEFT) {
                singleStickPosition = leftStickPosition;
            }
            motorSpeeds = differentialDrive.arcadeDrive(singleStickPosition);
            break;

        case TANK:
            motorSpeeds = differentialDrive.tankDrive(leftStickPosition, rightStickPosition);
            break;

        case ARCADE:
        default:
            motorSpeeds = differentialDrive.arcadeDrive(leftStickPosition, rightStickPosition);
            break;
        }
        
        // If the speeds are zero, then allow the operator to drive
        if (motorSpeeds.left == 0 && motorSpeeds.right == 0) {
        	leftStickPosition = oi.getOperatorStickPosition(TStick.LEFT);
        	leftStickPosition.x *= RobotConst.OPERATOR_MAX_DRIVE_SPEED;
        	leftStickPosition.y *= RobotConst.OPERATOR_MAX_DRIVE_SPEED;
            rightStickPosition = oi.getOperatorStickPosition(TStick.RIGHT);
            rightStickPosition.x *= RobotConst.OPERATOR_MAX_DRIVE_SPEED;
            rightStickPosition.y *= RobotConst.OPERATOR_MAX_DRIVE_SPEED;
            motorSpeeds = differentialDrive.arcadeDrive(leftStickPosition, rightStickPosition);
        }

        driveSubsystem.setSpeed(motorSpeeds);
    }

    @Override
    protected boolean isFinished() {
        // The default command does not end
        return false;
    }
}
