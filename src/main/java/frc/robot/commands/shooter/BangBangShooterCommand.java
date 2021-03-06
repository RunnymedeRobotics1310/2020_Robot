package frc.robot.commands.shooter;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConst;

/**
 *
 */
public class BangBangShooterCommand extends TSafeCommand {

    private boolean up;
    private static final String COMMAND_NAME =
            BangBangShooterCommand.class.getSimpleName();

    public BangBangShooterCommand() {

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
        // Print the command parameters if this is the current
        // called command (it was not sub-classed)
        if (getCommandName().equals(COMMAND_NAME)) {
            logMessage(getParmDesc() + " starting");
        }
        up = true;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        
        SmartDashboard.putNumber( "Encoder Count", Robot.shooterPIDSubsystem.getShooterEncoderSpeed());
        
//            if (up && Robot.shooterSubsystem.getShooterEncoderSpeed() < 2450.00) {
//                up = false;
//                Robot.shooterSubsystem.setShooterMotorSpeed(RobotConst.SHOOTER_BANGBANG_SPEED); 
//            }
//            else if (up && Robot.shooterSubsystem.getShooterEncoderSpeed() < 2250.00) {
//                up = true;
//            }
//            else {
//                Robot.shooterSubsystem.setShooterMotorSpeed(0);  
//            }
        
     
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

}
