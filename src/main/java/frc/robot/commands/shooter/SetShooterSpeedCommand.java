package frc.robot.commands.shooter;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;

import frc.robot.Robot;

/**
 *
 */
public class SetShooterSpeedCommand extends TSafeCommand {

    private static final String COMMAND_NAME =
            SetShooterSpeedCommand.class.getSimpleName();

    private double shooterSpeed = 0;

    public SetShooterSpeedCommand(double shooterSpeed) {

        super(TConst.NO_COMMAND_TIMEOUT, Robot.oi);

        this.shooterSpeed = shooterSpeed;
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

        // Use the default command to set the shooter speed.
        // This command should only set the value in the OI
        // which can then be tweaked by the user
        Robot.oi.setShooterSpeed(shooterSpeed);

        // NOTE: This code is not necessary because the
        //       default shooter command will do this once the
        //       oi value is set
        Robot.shooterSubsystem.setShooterMotorSpeed(shooterSpeed);

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
