package frc.robot.commands.drive;

import com.torontocodingcollective.commands.TDefaultDriveCommand;
import com.torontocodingcollective.commands.TDifferentialDrive;
import com.torontocodingcollective.oi.TStick;
import com.torontocodingcollective.oi.TStickPosition;
import com.torontocodingcollective.speedcontroller.TSpeeds;

import frc.robot.Robot;
import frc.robot.oi.OI;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Default drive command for a drive base
 */
public class DefaultDriveCommand extends TDefaultDriveCommand {

    private static final String COMMAND_NAME =
            DefaultDriveCommand.class.getSimpleName();

    OI                oi                = Robot.oi;
    DriveSubsystem driveSubsystem    = Robot.driveSubsystem;

    TDifferentialDrive differentialDrive = new TDifferentialDrive();

    public DefaultDriveCommand() {
        // The drive logic will be handled by the TDefaultDriveCommand
        // which also contains the requires(driveSubsystem) statement
        super(Robot.oi, Robot.driveSubsystem);
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

        // Check the driver controller buttons
        super.execute();

        // Drive according to the type of drive selected in the
        // operator input.
        TStickPosition leftStickPosition = oi.getDriveStickPosition(TStick.LEFT);
        // Turn the max speed down to 1/3
        leftStickPosition.y /= 1.3;

        TStickPosition rightStickPosition = oi.getDriveStickPosition(TStick.RIGHT);
        // Turn the max turn down by 4
        rightStickPosition.x /= 1.3;

        TSpeeds motorSpeeds = differentialDrive.arcadeDrive(leftStickPosition, rightStickPosition);

        driveSubsystem.setSpeed(motorSpeeds);
    }

    @Override
    protected boolean isFinished() {
        // The default command does not end
        return false;
    }
}
