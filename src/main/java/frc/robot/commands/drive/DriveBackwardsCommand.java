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
public class DriveBackwardsCommand extends TDefaultDriveCommand {

    private static final String COMMAND_NAME =
            DriveBackwardsCommand.class.getSimpleName();

    OI                oi                = Robot.oi;
    DriveSubsystem driveSubsystem    = Robot.driveSubsystem;

    TDifferentialDrive differentialDrive = new TDifferentialDrive();

    double speed = 0;
    double distance = 0;
    
    public DriveBackwardsCommand(double distance, double speed) {
    	
        super(Robot.oi, Robot.driveSubsystem);
        this.speed = Math.abs(speed);
    	this.distance = Math.abs(distance);
    	
        // The drive logic will be handled by the TDefaultDriveCommand
        // which also contains the requires(driveSubsystem) statement
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
    	Robot.driveSubsystem.resetEncoders();
    	

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        // Check the driver controller buttons
        super.execute();
        driveSubsystem.setSpeed(-speed, -speed);

        
        
    }

    @Override
    protected boolean isFinished() {
        
    	
    	// The default command does not end
        return false;
    }
    protected void end() {
        driveSubsystem.setSpeed(0,0);

    }
}
