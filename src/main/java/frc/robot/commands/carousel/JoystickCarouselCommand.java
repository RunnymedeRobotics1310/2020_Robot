package frc.robot.commands.carousel;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;
import com.torontocodingcollective.oi.TAxis;
import com.torontocodingcollective.oi.TStick;

import frc.robot.Robot;
import frc.robot.RobotConst;

/**
 *
 */
public class JoystickCarouselCommand extends TSafeCommand {

    private static final String COMMAND_NAME =
            JoystickCarouselCommand.class.getSimpleName();

    public JoystickCarouselCommand() {

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

        Robot.carouselSubsystem.setCarouselMotorSpeed(Robot.oi.joystickCarousel());

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	return Robot.oi.joystickCarousel() == 0.0;
	}

}
