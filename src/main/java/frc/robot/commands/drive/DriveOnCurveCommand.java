package frc.robot.commands.drive;

import com.torontocodingcollective.commands.TDifferentialDrive;
import com.torontocodingcollective.commands.TSafeCommand;

import frc.robot.Robot;
import frc.robot.RobotConst;
import frc.robot.oi.OI;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Default drive command for a drive base
 */
public class DriveOnCurveCommand extends TSafeCommand {

    private static final String COMMAND_NAME =
            DriveOnCurveCommand.class.getSimpleName();

    OI                oi                = Robot.oi;
    DriveSubsystem driveSubsystem    = Robot.driveSubsystem;

    double speed = 0;
    double distance = 0;
    double radius = 0;
    boolean curveRight;
    double rightMotorSpeed;
    double leftMotorSpeed;
    double speedConstant;
    
    /**
     * 
     * @param distance
     * @param speed
     * @param radius
     * @param curveRight
     */
    public DriveOnCurveCommand(double distance, double speed, double radius, boolean curveRight) {
    	
        super(4, Robot.oi);
        this.speed = speed;
    	this.distance = distance;
    	this.radius = radius;
    	this.curveRight = curveRight;
    	
    	speedConstant = (radius - (RobotConst.ROBOT_WIDTH/2)) / (radius + (RobotConst.ROBOT_WIDTH/2));
    	
    	if (curveRight == true) {
    		leftMotorSpeed = speed;
    		rightMotorSpeed = speedConstant * leftMotorSpeed;
    	}
    	else {
    		rightMotorSpeed = speed;
    		leftMotorSpeed = speedConstant * rightMotorSpeed;
    	}
    	
    	
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
        driveSubsystem.setSpeed(rightMotorSpeed, leftMotorSpeed);

        
        
    }

    @Override
    protected boolean isFinished() {
    	
        
    	if(Math.abs(Robot.driveSubsystem.getDistanceInches()) > distance) {
    		return true;
    	}

    	return false;
    }
    protected void end() {
        driveSubsystem.setSpeed(0,0);

    }
}
