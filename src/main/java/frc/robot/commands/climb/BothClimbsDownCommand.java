package frc.robot.commands.climb;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;

import frc.robot.Robot;
import frc.robot.RobotConst;

/**
 *
 */
public class BothClimbsDownCommand extends TSafeCommand {

    private static final String COMMAND_NAME =
            BothClimbsDownCommand.class.getSimpleName();

    public BothClimbsDownCommand() {

        super(TConst.NO_COMMAND_TIMEOUT, Robot.oi);

        // Use requires() here to declare subsystem dependencies
        requires(Robot.climbSubsystem);
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
        if (Robot.oi.runLeftClimbUp() == true) {
        	Robot.climbSubsystem.setLeftClimbSpeed(RobotConst.CLIMB_SPEED_DOWN);
        	Robot.climbSubsystem.setRightClimbSpeed(RobotConst.CLIMB_SPEED_DOWN);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

       
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if (Robot.oi.runLeftClimbUp()==false) {
            return true;
        }
        return false;

}
    @Override
    protected void end() {
    	Robot.climbSubsystem.lockClimb();
    }
}