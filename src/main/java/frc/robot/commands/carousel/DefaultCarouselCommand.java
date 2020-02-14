package frc.robot.commands.carousel;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;

import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;

/**
 *
 */
public class DefaultCarouselCommand extends TSafeCommand {

	private static final String COMMAND_NAME =
			DefaultCarouselCommand.class.getSimpleName();

	public DefaultCarouselCommand() {

		super(TConst.NO_COMMAND_TIMEOUT, Robot.oi);

		// Use requires() here to declare subsystem dependencies
		requires(Robot.carouselSubsystem);
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
        if (Robot.carouselSubsystem.isRobotFull()) {
            Robot.carouselSubsystem.stopCarouselMotor();
            return;
        }

        if (Robot.oi.stopRunCarousel()) {
            Scheduler.getInstance().add(new StopCarouselCommand());
        }

        if (Robot.oi.runIntakeCarousel()) {
            Scheduler.getInstance().add(new IntakeCarouselCommand());
        }

        if (Robot.oi.runShooterCarousel()) {
            Scheduler.getInstance().add(new ShooterCarouselCommand());
        }
    }

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

}
