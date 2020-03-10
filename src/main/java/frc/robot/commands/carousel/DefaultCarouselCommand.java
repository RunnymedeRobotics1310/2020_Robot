package frc.robot.commands.carousel;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Robot;
import frc.robot.RobotConst;
import frc.robot.oi.OI.TestMode;

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

        if (Robot.oi.isTestModeEnabled()) {
            if (Robot.oi.getTestMode() == TestMode.CAROUSEL) {
                Robot.carouselSubsystem.setCarouselMotorSpeed(Robot.oi.getTestMotorSpeed());
            }
            else {
                Robot.carouselSubsystem.stopCarouselMotor();
            }
            // If in test mode, then do not look for other buttons
            return;
        }

        // Do not look at the Joystick in auto.
        if (DriverStation.getInstance().isAutonomous()) {
            return;
        }

        if (Robot.carouselSubsystem.isRobotFull()) {
            Robot.carouselSubsystem.stopCarouselMotor();
            return;
        }

        if (Robot.oi.joystickCarousel() != 0.0) {
            Robot.carouselSubsystem.setCarouselMotorSpeed(Robot.oi.joystickCarousel());
        }

        if (Robot.oi.stopCarousel()) {
            Robot.carouselSubsystem.stopCarouselMotor();
        }

        if (Robot.oi.runIntakeCarousel()) {
            Robot.carouselSubsystem.setCarouselMotorSpeed(RobotConst.CAROUSEL_INTAKE_SPEED);
        }

        if (Robot.oi.runShooterCarousel()) {
            Robot.carouselSubsystem.setCarouselMotorSpeed(RobotConst.CAROUSEL_SHOOTER_SPEED );
        }
        if (Robot.oi.runReverse()) {
            Robot.carouselSubsystem.setCarouselMotorSpeed(-RobotConst.CAROUSEL_SHOOTER_SPEED );
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

}
