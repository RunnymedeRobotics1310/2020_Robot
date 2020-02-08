package frc.robot.commands.shooter;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;

import frc.robot.HoodPosition;
import frc.robot.Robot;
import frc.robot.RobotConst;
import frc.robot.subsystems.ShooterSubsystem;

/**
 *
 */
public class BangBangCommand extends TSafeCommand {

    private static final String COMMAND_NAME =
            BangBangCommand.class.getSimpleName();

    public BangBangCommand() {

        super(TConst.NO_COMMAND_TIMEOUT, Robot.oi);

        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterSubsystem);
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
        
            if (Robot.shooterSubsystem.getShooterEncoder() < 2450.00) {
            Robot.shooterSubsystem.setShooterMotorSpeed(RobotConst.SHOOTER_BANGBANG_SPEED); 
            }
            else if (Robot.shooterSubsystem.getShooterEncoder() >= 2450.00) {
            Robot.shooterSubsystem.setShooterMotorSpeed(0);  
            }
        
     
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

}
