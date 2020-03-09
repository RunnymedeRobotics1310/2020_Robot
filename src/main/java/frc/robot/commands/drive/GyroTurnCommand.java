package frc.robot.commands.drive;

import com.kauailabs.navx.frc.AHRS;
import com.torontocodingcollective.commands.TDefaultDriveCommand;
import com.torontocodingcollective.commands.TDifferentialDrive;
import com.torontocodingcollective.commands.TSafeCommand;
import com.torontocodingcollective.oi.TStick;
import com.torontocodingcollective.oi.TStickPosition;
import com.torontocodingcollective.speedcontroller.TSpeeds;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConst;
import frc.robot.oi.OI;
import frc.robot.oi.OI.TestMode;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Default drive command for a drive base
 */
public class GyroTurnCommand extends TSafeCommand {
	private AHRS ahrs;
	PIDController turnController;
	double rotateToAngleRate;
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
	
    private static final String COMMAND_NAME =
            DefaultDriveCommand.class.getSimpleName();

    OI                oi                = Robot.oi;
    DriveSubsystem driveSubsystem    = Robot.driveSubsystem;

    TDifferentialDrive differentialDrive = new TDifferentialDrive();

    public GyroTurnCommand() {
        // The drive logic will be handled by the TDefaultDriveCommand
        // which also contains the requires(driveSubsystem) statement
    	super(Robot.oi);
        requires(Robot.driveSubsystem);
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

        
    	
    	turnController = new PIDController(RobotConst.DRIVE_GYRO_PID_KP, RobotConst.DRIVE_GYRO_PID_KI, RobotConst.DRIVE_GYRO_PID_KD);

        // Print the command parameters if this is the current
        // called command (it was not sub-classed)
        if (getCommandName().equals(COMMAND_NAME)) {
            logMessage(getParmDesc() + " starting");
        }
        turnController.setSetpoint(0.0);
        turnController.setTolerance(0.1);
        ahrs = new AHRS();
        super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
//    	turnController.calculate(driveSubsystem.getGyroAngle());
    	//read values periodically
        
    	double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
      //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
    	double turn = turnController.calculate(x);
        SmartDashboard.putNumber("Turn Speed", turn);
        if (Robot.oi.gyroTurn()) {
    	if (Math.abs(turn) < .04) {
        	driveSubsystem.setSpeed(new TSpeeds(0 ,0));
    		return;
    	}
    	SmartDashboard.putNumber("Last Turn Speed", turn);
    	driveSubsystem.setSpeed(new TSpeeds(-turn ,turn));

        }
        else {
        	return;
        }

	}

    @Override
    protected boolean isFinished() {
        // The default command does not end
    	return true;
//        return turnController.atSetpoint();

    }
}
