package frc.robot.commands.tower;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;

import frc.robot.Robot;

/**
 *
 */
public class StopTowerCommand extends TSafeCommand {

    private static final String COMMAND_NAME =
            StopTowerCommand.class.getSimpleName();

    public StopTowerCommand() {

        super(TConst.NO_COMMAND_TIMEOUT, Robot.oi);

        // Use requires() here to declare subsystem dependencies
        requires(Robot.towerSubsystem);
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

        Robot.towerSubsystem.stopTowerMotor();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

}
