package frc.robot.commands.shooter;

import com.torontocodingcollective.commands.TSafeCommand;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Robot;
import frc.robot.RobotConst;

/**
 *
 */
public class ShootCommand extends TSafeCommand {

    private static final String COMMAND_NAME =
            ShootCommand.class.getSimpleName();

    public ShootCommand() {
        this(4);
    }

    public ShootCommand(double timeSec) {


        super(timeSec, Robot.oi);

        // Use requires() here to declare subsystem dependencies
        requires(Robot.towerSubsystem);
        requires(Robot.carouselSubsystem);
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
        // Print the command parameters if this is the current
        // called command (it was not sub-classed)
        if (getCommandName().equals(COMMAND_NAME)) {
            logMessage(getParmDesc() + " starting");
        }



    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        //    	if (Robot.shooterPIDSubsystem.isShooterReady == false)
        //        {
        //        return;
        //        }
        Robot.towerSubsystem.setKickerMotorSpeed(RobotConst.KICKER_MOTOR_SPEED);
        Robot.towerSubsystem.setTowerMotorSpeed(0.8);
        Robot.carouselSubsystem.setCarouselMotorSpeed(0.3);

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if (super.isFinished()) {
            return true;
        }
        return false;
    }
    @Override
    protected void end() {
        Robot.towerSubsystem.stopTowerMotor();
        Robot.carouselSubsystem.stopCarouselMotor();

        // In auto, do not stop the shooter
        if (!DriverStation.getInstance().isAutonomous()) {
            Robot.shooterPIDSubsystem.stopShooterMotor();
        }
    }
}
