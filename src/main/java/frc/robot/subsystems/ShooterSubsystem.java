package frc.robot.subsystems;

import com.torontocodingcollective.pid.TSpeedPID;
import com.torontocodingcollective.sensors.encoder.TEncoder;
import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.speedcontroller.TSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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

    private TSpeedController shooterMotor =
            new TCanSpeedController(
                    RobotMap.SHOOTER_SPEED_CONTROLLER_TYPE, RobotMap.SHOOTER_SPEED_CONTROLLER_CAN_ADDRESS,
                    RobotMap.SHOOTER_MOTOR_ISINVERTED);

    private TSpeedController shooterFollowerMotor =
            new TCanSpeedController(
                    RobotMap.SHOOTER_SPEED_FOLLOWER_TYPE, RobotMap.SHOOTER_SPEED_FOLLOWER_CAN_ADDRESS,
                    !RobotMap.SHOOTER_MOTOR_ISINVERTED);

    private TEncoder shooterEncoder = shooterMotor.getEncoder(RobotMap.SHOOTER_ENCODER_ISINVERTED);

    //private Solenoid stopper = new Solenoid(RobotMap.SHOOTER_STOPPER_PNEUMATIC_PORT);
    private DoubleSolenoid hood = new DoubleSolenoid(RobotMap.HOOD_RETRACT_PNEUMATIC_PORT,RobotMap.HOOD_EXTEND_PNEUMATIC_PORT);

    private HoodPosition curHoodPosition;

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
        if (shooterPid.isEnabled()) {
            shooterPid.setSetpoint(speed);
        }
        else {
            shooterMotor.set(speed);
            shooterFollowerMotor.set(speed);
        }
    }

    public boolean isShooterRunning() {
        if(shooterMotor.get() > 0) {
            return true;
        }
        return false;
    }

    public void stopShooterMotor() {
        shooterMotor.set(0);
        shooterFollowerMotor.set(0);
    }

    public double getShooterEncoderSpeed() {
        if (shooterEncoder == null) {
            return 0;
        }
        return shooterEncoder.getRate();
    }
    public void setHoodPosition(HoodPosition hoodPosition) {

        switch (hoodPosition) {
        case CLOSE:
            //stopper.set(false);// Do close code
            hood.set(Value.kForward);
            break;
        case MEDIUM:
            //stopper.set(true);// Do med code
            hood.set(Value.kForward);
            break;
        case FAR:
            //stopper.set(true);// Do far code
            hood.set(Value.kReverse);
            break;
        }

        curHoodPosition = hoodPosition;
    }

    // Periodically update the dashboard and any PIDs or sensors
    @Override
    public void updatePeriodic() {

        if (shooterPid.isEnabled()) {
            if (shooterEncoder != null) {
            	double rpm = (shooterEncoder.getRate()/42)*60;
                shooterPid.calculate(rpm / RobotConst.MAX_SHOOTER_SPEED);
            }
            shooterMotor.set(shooterPid.get());
            shooterFollowerMotor.set(shooterPid.get());
        }

        SmartDashboard.putString("Hood Position", curHoodPosition.toString());
        //SmartDashboard.putBoolean("Stopper", stopper.get());
        //SmartDashboard.putBoolean("Deployer", deployer.get());
        SmartDashboard.putNumber( "Shooter Speed", shooterMotor.get());
        SmartDashboard.putNumber( "Shooter Encoder Speed", getShooterEncoderSpeed());
        SmartDashboard.putData("Shooter PID", shooterPid);
        SmartDashboard.putNumber("Shooter PID Output", shooterPid.get());
    }

}
