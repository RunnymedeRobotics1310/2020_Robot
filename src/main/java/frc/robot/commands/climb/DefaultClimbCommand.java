package frc.robot.commands.climb;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.RobotConst;
import frc.robot.oi.OI.TestMode;

/**
 *
 */
public class DefaultClimbCommand extends TSafeCommand {

	private static final String COMMAND_NAME =
			DefaultClimbCommand.class.getSimpleName();

	public DefaultClimbCommand() {

		super(TConst.NO_COMMAND_TIMEOUT, Robot.oi);

		// Use requires() here to declare subsystem dependencies
		requires(Robot.climbSubsystem);
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
            if (Robot.oi.getTestMode() == TestMode.LEFT_CLIMB) {
                Robot.climbSubsystem.setLeftClimbSpeed(Robot.oi.getTestMotorSpeed());
            }
            else if (Robot.oi.getTestMode() == TestMode.RIGHT_CLIMB) {
                Robot.climbSubsystem.setRightClimbSpeed(Robot.oi.getTestMotorSpeed());
            }
            else if (Robot.oi.lockClimb()){
            	Robot.climbSubsystem.lockClimb();
            }
            else {
                Robot.climbSubsystem.setLeftClimbSpeed(0);
                Robot.climbSubsystem.setRightClimbSpeed(0);
            }
            return;
        }

        // End of match climb stop.
		if (!DriverStation.getInstance().isAutonomous() && Timer.getMatchTime() < .2) {
			Robot.climbSubsystem.lockClimb();
		}

		if (Robot.oi.runBothClimbUp()) {
			Robot.climbSubsystem.setRightClimbSpeed(RobotConst.CLIMB_SPEED_UP);
			Robot.climbSubsystem.setLeftClimbSpeed(RobotConst.CLIMB_SPEED_UP);
		}
		else if (Robot.oi.runBothClimbDown()) {
			Robot.climbSubsystem.setRightClimbSpeed(RobotConst.CLIMB_SPEED_DOWN);
			Robot.climbSubsystem.setLeftClimbSpeed(RobotConst.CLIMB_SPEED_DOWN);
		}
		else {
			if (Robot.oi.runLeftClimbUp()) {
				Robot.climbSubsystem.setLeftClimbSpeed(RobotConst.CLIMB_SPEED_UP);
			}
			else if (Robot.oi.runLeftClimbDown()) {
				Robot.climbSubsystem.setLeftClimbSpeed(RobotConst.CLIMB_SPEED_DOWN);
			}
			else {
				Robot.climbSubsystem.setLeftClimbSpeed(0);
			}

			if (Robot.oi.runRightClimbUp()) {
				Robot.climbSubsystem.setRightClimbSpeed(RobotConst.CLIMB_SPEED_UP);
			}
			else if (Robot.oi.runRightClimbDown()) {
				Robot.climbSubsystem.setRightClimbSpeed(RobotConst.CLIMB_SPEED_DOWN);
			}
			else {
				Robot.climbSubsystem.setRightClimbSpeed(0);
			}

		}

		if (Robot.oi.lockClimb()) {
			Robot.climbSubsystem.lockClimb();
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

}
