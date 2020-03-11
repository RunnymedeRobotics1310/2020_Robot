package frc.robot.commands.shooter;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.HoodPosition;
import frc.robot.Robot;
import frc.robot.RobotConst;
import frc.robot.oi.OI.TestMode;

/**
 *
 */
public class DefaultShooterCommand extends TSafeCommand {

    double setpoint = 0;
    boolean set = false;

    private static final String COMMAND_NAME =
            DefaultShooterCommand.class.getSimpleName();

    public DefaultShooterCommand() {

        super(TConst.NO_COMMAND_TIMEOUT, Robot.oi);

        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterPIDSubsystem);
    }

    @Override
    protected String getCommandName() { return COMMAND_NAME; }

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
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        if (Robot.oi.isTestModeEnabled()) {

            if (Robot.oi.getTestMode() == TestMode.SHOOTER) {

                // When testing the shooter, enable the hood position pistons
                HoodPosition userSelectedHoodPostion = Robot.oi.getHoodPosition();
                Robot.shooterPIDSubsystem.setHoodPosition(userSelectedHoodPostion);

                Robot.shooterPIDSubsystem.setSetpoint(Robot.oi.getTestMotorSpeed());
            }
            else {
                Robot.shooterPIDSubsystem.stopShooterMotor();
            }
            // If in test mode, then do not look for other buttons
            return;
        }

        // Do not look at the Joystick in auto.
        if (DriverStation.getInstance().isAutonomous()) {
            return;
        }

        if (Robot.oi.getAutoShoot()) {

            if (!set) {
                // Set the hood position based on the Y-Value of the vision target
                double y = Robot.cameraSubsystem.getTargetY();

                HoodPosition autoHoodPosition = HoodPosition.FAR;
                double autoShootSpeed = RobotConst.BACK_OF_TRENCH_RPM;

                if (y > 3) {
                    autoHoodPosition = HoodPosition.CLOSE;
                    autoShootSpeed = RobotConst.INITIATION_LINE_RPM;
                }
                else if (y > -1) {
                    autoHoodPosition = HoodPosition.MEDIUM;
                    autoShootSpeed = RobotConst.FRONT_OF_TRENCH_RPM;
                }
                Robot.shooterPIDSubsystem.setHoodPosition(autoHoodPosition);
                Robot.shooterPIDSubsystem.setSetpoint(autoShootSpeed);
                set = true;
            }
            return;
        }

        HoodPosition userSelectedHoodPostion = Robot.oi.getHoodPosition();
        Robot.shooterPIDSubsystem.setHoodPosition(userSelectedHoodPostion);

        if(setpoint!=Robot.oi.getShooterSetpoint()) {
            set = false;
        }

        setpoint = Robot.oi.getShooterSetpoint();

        if(setpoint == 0) {
            Robot.shooterPIDSubsystem.stopShooterMotor();
            set = false;
        }
        else if (!set) {
            Robot.shooterPIDSubsystem.setSetpoint(setpoint);
            set = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

}
