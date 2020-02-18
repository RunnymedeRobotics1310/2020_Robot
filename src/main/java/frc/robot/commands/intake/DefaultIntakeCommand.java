package frc.robot.commands.intake;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;

import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;
import frc.robot.oi.OI.TestMode;

/**
 *
 */
public class DefaultIntakeCommand extends TSafeCommand {

    private static final String COMMAND_NAME =
            DefaultIntakeCommand.class.getSimpleName();

    public DefaultIntakeCommand() {

        super(TConst.NO_COMMAND_TIMEOUT, Robot.oi);

        // Use requires() here to declare subsystem dependencies
        requires(Robot.intakeSubsystem);
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

            // If testing the bottom or the top intake, then
            // also activate the piston using the intake buttons (but not the motor)
            if (       Robot.oi.getTestMode() == TestMode.BOTTOM_INTAKE
                    || Robot.oi.getTestMode() == TestMode.TOP_INTAKE) {
                if (Robot.oi.runFeederIntake()) {
                    Robot.intakeSubsystem.retractIntake();
                }
                if (Robot.oi.runGroundIntake()) {
                    Robot.intakeSubsystem.extendIntake();
                }
            }
            else {
                Robot.intakeSubsystem.retractIntake();
            }

            if (Robot.oi.getTestMode() == TestMode.BOTTOM_INTAKE) {
                Robot.intakeSubsystem.setIntakeSpeed(0, Robot.oi.getTestMotorSpeed());
            }
            else if (Robot.oi.getTestMode() == TestMode.TOP_INTAKE) {
                Robot.intakeSubsystem.setIntakeSpeed(Robot.oi.getTestMotorSpeed(), 0);
            }
            else {
                Robot.intakeSubsystem.stopIntake();
            }
            // If in test mode, then do not look for other buttons
            return;
        }

        if (Robot.carouselSubsystem.isRobotFull()) {
            Robot.intakeSubsystem.stopIntake();
            return;
        }

        if (Robot.oi.stopIntake()) {
            Robot.intakeSubsystem.stopIntake();
            return;
        }
         
        if (Robot.oi.feederExtake()) {
            Scheduler.getInstance().add(new FeederExtakeCommand());
        }
        
        if (Robot.oi.groundExtake()) {
            Scheduler.getInstance().add(new GroundExtakeCommand());
        }

        if (Robot.oi.runFeederIntake()) {
            Scheduler.getInstance().add(new FeederIntakeCommand());
        }
        if (Robot.oi.runGroundIntake()) {
            Scheduler.getInstance().add(new GroundIntakeCommand());
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

}
