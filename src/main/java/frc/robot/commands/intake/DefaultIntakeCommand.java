package frc.robot.commands.intake;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;

import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;

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

		// If the robot is full of balls, stop the intake

		// else look for buttons.

		if (Robot.carouselSubsystem.isCarouselFull() && Robot.towerSubsystem.isTowerFull()) {
			Robot.intakeSubsystem.stopIntake();
		}

		else {
			if (Robot.oi.runFeederIntake()) {
				Scheduler.getInstance().add(new FeederIntakeCommand());
			}
			if (Robot.oi.runGroundIntake()) {
				Scheduler.getInstance().add(new GroundIntakeCommand());
			}
		}



	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

}
