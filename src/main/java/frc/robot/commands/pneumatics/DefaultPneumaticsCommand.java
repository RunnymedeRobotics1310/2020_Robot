package frc.robot.commands.pneumatics;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;

import frc.robot.HoodPosition;
import frc.robot.Robot;

/**
 *
 */
public class DefaultPneumaticsCommand extends TSafeCommand {

    private static final String COMMAND_NAME =
            DefaultPneumaticsCommand.class.getSimpleName();

    public DefaultPneumaticsCommand() {

        super(TConst.NO_COMMAND_TIMEOUT, Robot.oi);

        // Use requires() here to declare subsystem dependencies
        requires(Robot.pneumaticsSubsystem);
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

        if (Robot.oi.getCompressorEnabled()) {
            Robot.pneumaticsSubsystem.enableCompressor();
        } else {
            Robot.pneumaticsSubsystem.disableCompressor();
        }
        
        if (Robot.oi.isTestModeEnabled()) {
            return;
        }
        
        if (Robot.oi.intakeUp()) {
        	Robot.intakeSubsystem.extendIntake();
        }
        
        if (Robot.oi.intakeDown()) {
        	Robot.intakeSubsystem.retractIntake();
        }
        
        if (Robot.oi.shooterExtend()) {
        	Robot.shooterPIDSubsystem.setHoodPosition(HoodPosition.FAR);
        }
        
        if (Robot.oi.shooterRetract()) {
        	Robot.shooterPIDSubsystem.setHoodPosition(HoodPosition.CLOSE);
        }
        
        if (Robot.oi.intakeDown()) {
        	Robot.intakeSubsystem.retractIntake();
        }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

}
