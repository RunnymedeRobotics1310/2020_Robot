package frc.robot.subsystems;

import com.torontocodingcollective.sensors.encoder.TEncoder;
import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.speedcontroller.TSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.HoodPosition;
import frc.robot.RobotMap;
import frc.robot.commands.shooter.DefaultShooterCommand;
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

    public boolean setShooterMotorSpeed(double speed) {
        shooterMotor.set(speed);
        return true;
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
        SmartDashboard.putNumber( "Shooter Encoder Speed", shooterEncoder.getRate());
    }

}
