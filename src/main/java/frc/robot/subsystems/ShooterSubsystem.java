package frc.robot.subsystems;

import com.torontocodingcollective.pid.TSpeedPID;
import com.torontocodingcollective.sensors.encoder.TEncoder;
import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.speedcontroller.TSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.HoodPosition;
import frc.robot.RobotConst;
import frc.robot.RobotMap;
import frc.robot.commands.shooter.DefaultShooterCommand;
/**
 *
 */
public class ShooterSubsystem extends TSubsystem {

    private Solenoid stopper = new Solenoid(RobotMap.SHOOTER_STOPPER_PNEUMATIC_PORT);
    private Solenoid deployer = new Solenoid(RobotMap.SHOOTER_DEPLOYER_PNEUMATIC_PORT);
    private TSpeedController shooterMotor = new TCanSpeedController(
            RobotMap.SHOOTER_CAN_SPEED_CONTROLLER_TYPE, RobotMap.SHOOTER_CAN_SPEED_CONTROLLER_ADDRESS,
            RobotMap.SHOOTER_CAN_SPEED_FOLLOWER_TYPE,  RobotMap.SHOOTER_CAN_SPEED_FOLLOWER_ADDRESS,
            RobotMap.SHOOTER_CAN_SPEED_CONTROLLER_ISINVERTED);
    private HoodPosition curHoodPosition;
    private TEncoder shooterEncoder = shooterMotor.getEncoder();

    private TSpeedPID shooterPid = new TSpeedPID(RobotConst.SHOOTER_SPEED_PID_KP, RobotConst.SHOOTER_SPEED_PID_KI);

    private boolean shooterPidEnabled = true;

    @Override
    public void init() {
        // FIXME: Set the initial position to the value at robot setup.
        curHoodPosition = HoodPosition.CLOSE;

        if (shooterPidEnabled) {
            shooterPid.enable();
        }
        else {
            shooterPid.disable();
        }
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
        if (shooterPidEnabled) {
            shooterPid.setSetpoint(speed);
        }
        else {
            shooterMotor.set(speed);
        }
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

        if (shooterPidEnabled) {
            shooterPid.calculate(shooterEncoder.getRate() / RobotConst.MAX_SHOOTER_SPEED);
            shooterMotor.set(shooterPid.get());
        }

        SmartDashboard.putString("Hood Position", curHoodPosition.toString());
        SmartDashboard.putBoolean("Stopper", stopper.get());
        SmartDashboard.putBoolean("Deployer", deployer.get());
        SmartDashboard.putNumber( "Shooter Speed", shooterMotor.get());
        SmartDashboard.putNumber( "Shooter Encoder Speed", shooterEncoder.getRate());
        SmartDashboard.putData("Shooter PID", shooterPid);
        SmartDashboard.putNumber("Shooter PID Output", shooterPid.get());
    }

}
