package frc.robot.commands.tower;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;

import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;

/**
 *
 */
public class DefaultTowerCommand extends TSafeCommand {

    private static final String COMMAND_NAME =
            DefaultTowerCommand.class.getSimpleName();

    public DefaultTowerCommand() {

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
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        if (Robot.towerSubsystem.isTowerFull()) {
            Robot.towerSubsystem.stopTowerMotor();
            return;
        }

        if (Robot.oi.stopTower()) {
            Scheduler.getInstance().add(new StopTowerCommand());
        }
        
        if (Robot.oi.runIntakeTower()) {
            Scheduler.getInstance().add(new IntakeTowerCommand());
        }
        
        if (Robot.oi.runShooterTower()) {
            Scheduler.getInstance().add(new ShooterTowerCommand());
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

}
