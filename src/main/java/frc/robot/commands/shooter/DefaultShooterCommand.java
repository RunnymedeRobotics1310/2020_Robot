package frc.robot.commands.shooter;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;

import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.HoodPosition;
import frc.robot.Robot;
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
        // Print the command parameters if this is the current
        // called command (it was not sub-classed)
        if (getCommandName().equals(COMMAND_NAME)) {
            logMessage(getParmDesc() + " starting");
        }
        set = false;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        if (Robot.oi.isTestModeEnabled()) {

            if (Robot.oi.getTestMode() == TestMode.SHOOTER) {

                // When testing the shooter, enable the hood position pistons
                HoodPosition userSelectedHoodPostion = Robot.oi.getHoodPosition();
                Robot.shooterPIDSubsystem.setHoodPosition(userSelectedHoodPostion);

                //Robot.shooterPIDSubsystem.setShooterMotorSpeed(Robot.oi.getTestMotorSpeed());
            }
            else {
                Robot.shooterPIDSubsystem.stopShooterMotor();
            }
            // If in test mode, then do not look for other buttons
            return;
        }

        // Set the hood position even when in test mode
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
        else if(!set){
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
