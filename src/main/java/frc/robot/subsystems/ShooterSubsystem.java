package frc.robot.subsystems;

import com.torontocodingcollective.sensors.encoder.TEncoder;
import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.speedcontroller.TSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;
import com.torontocodingcollective.subsystem.TDriveSubsystem.TSide;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.HoodPosition;
import frc.robot.Robot;
import frc.robot.RobotConst;
import frc.robot.RobotMap;
import frc.robot.commands.shooter.DefaultShooterCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
/**
 *
 */
public class ShooterSubsystem extends TSubsystem {

	Solenoid stopper = new Solenoid(RobotMap.SHOOTER_STOPPER_PNEUMATIC_PORT);
	Solenoid deployer = new Solenoid(RobotMap.SHOOTER_DEPLOYER_PNEUMATIC_PORT);
	TSpeedController shooterMotor = new TCanSpeedController(RobotMap.SHOOTER_CAN_SPEED_CONTROLLER_TYPE, RobotMap.SHOOTER_CAN_SPEED_CONTROLLER_ADDRESS); 
    private HoodPosition curHoodPosition;
    TEncoder shooterEncoder = shooterMotor.getEncoder();
    
    @Override
    public void init() {
        // FIXME: Set the initial position to the value at robot setup.
        curHoodPosition = HoodPosition.CLOSE;
    };


    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultShooterCommand());
    }

    /**
     * Set the speed on the shooter
     *
     * @param speed value 0 (stopped) to 1.0 (full speed)
     **/
    
    public void setShooterMotorSpeed(double speed) {
    	shooterMotor.set(speed);
    }
    
    public void stopShooterMotor() {
       shooterMotor.set(0);
    }

    public double getShooterEncoder() {  
        return shooterEncoder.getRate();
    }
    public void setHoodPosition(HoodPosition hoodPosition) {

        switch (hoodPosition) {
        case CLOSE:
            stopper.set(false);// Do close code
            deployer.set(false);
            break;
        case MEDIUM:
            stopper.set(true);// Do med code
            deployer.set(true);
            break;
        case FAR:
            stopper.set(true);// Do far code
            deployer.set(false);
            break;
        }

        curHoodPosition = hoodPosition;
    }

    // Periodically update the dashboard and any PIDs or sensors
    @Override
    public void updatePeriodic() {
        SmartDashboard.putString("Hood Position", curHoodPosition.toString());
        SmartDashboard.putBoolean("Stopper", stopper.get());
        SmartDashboard.putBoolean("Deployer", deployer.get());
        SmartDashboard.putNumber( "Shooter Speed", shooterMotor.get());

        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");

        //read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);

    }

}
