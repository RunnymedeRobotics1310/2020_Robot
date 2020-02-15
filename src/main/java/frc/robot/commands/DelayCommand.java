package frc.robot.commands;

import com.torontocodingcollective.commands.TSafeCommand;

import frc.robot.Robot;
import frc.robot.oi.OI;

/**
 * Default drive command for a drive base
 */
public class DelayCommand extends TSafeCommand {

    private static final String COMMAND_NAME =
            DelayCommand.class.getSimpleName();

    OI oi = Robot.oi;

    public DelayCommand(double delay) {
        super(delay, Robot.oi);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

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

        super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return super.isFinished();
    }
}
