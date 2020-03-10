package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;
import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.HoodPosition;
import frc.robot.RobotConst;
import frc.robot.RobotMap;
import frc.robot.commands.shooter.DefaultShooterCommand;
/**
 *
 */
public class ShooterPIDSubsystem extends TSubsystem {
    private CANSparkMax motor;
    private CANSparkMax motor2;

    private CANPIDController pidController;
    private CANEncoder encoder;
    private double p, i, d, ff;

    //private Solenoid stopper = new Solenoid(RobotMap.SHOOTER_STOPPER_PNEUMATIC_PORT);
    private DoubleSolenoid hood = new DoubleSolenoid(RobotMap.HOOD_RETRACT_PNEUMATIC_PORT,RobotMap.HOOD_EXTEND_PNEUMATIC_PORT);
    private DoubleSolenoid pancake = new DoubleSolenoid(RobotMap.STOPPER_EXTEND_PNEUMATIC_PORT,RobotMap.STOPPER_RETRACT_PNEUMATIC_PORT);

    private HoodPosition curHoodPosition;
    private double setpoint;
    private boolean stop;
    public boolean isShooterReady;


    private boolean shooterPidEnabled = true;

    @Override
    public void init() {
        stop = true;
        setpoint = 0;
        motor = new CANSparkMax(RobotMap.SHOOTER_SPEED_FOLLOWER_CAN_ADDRESS, MotorType.kBrushless);
        motor2 = new CANSparkMax(RobotMap.SHOOTER_SPEED_CONTROLLER_CAN_ADDRESS, MotorType.kBrushless);
        motor.setInverted(false);
        motor2.setInverted(true);
        pidController = motor.getPIDController();
        encoder = motor.getEncoder();

        // set PID coefficients
        p = RobotConst.SHOOTER_KP;
        i = RobotConst.SHOOTER_KI;
        d = RobotConst.SHOOTER_KD;
        ff = RobotConst.SHOOTER_FF;
        pidController.setP(RobotConst.SHOOTER_KP);
        pidController.setI(RobotConst.SHOOTER_KI);
        pidController.setD(RobotConst.SHOOTER_KD);
        pidController.setFF(RobotConst.SHOOTER_FF);
        pidController.setIZone(0);
        pidController.setOutputRange(-1, 1);

        setHoodPosition(HoodPosition.FAR);
    }



    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultShooterCommand());
    }

    /**
     * Set the speed on the shooter
     *
     * @param speed value 0 (stopped) to 1.0 (full speed)
     **/

    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
        this.stop = false;
        this.isShooterReady = false;
    }

    public HoodPosition getHoodPosition() {
        return curHoodPosition;
    }

    public boolean isShooterRunning() {
        if(motor.get() > 0) {
            return true;
        }
        return false;
    }


    public void stopShooterMotor() {
        motor.set(0);
        motor2.set(0);
        this.stop = true;
        this.isShooterReady = false;

    }

    public double getShooterEncoderSpeed() {
        return encoder.getVelocity();
    }

    public void setHoodPosition(HoodPosition hoodPosition) {

        switch (hoodPosition) {
        case CLOSE:
            pancake.set(Value.kReverse);// Do close code
            hood.set(Value.kForward);
            break;
        case MEDIUM:
            //stopper.set(true);// Do med code
            pancake.set(Value.kForward);
            hood.set(Value.kForward);
            break;
        case FAR:
            pancake.set(Value.kForward);// Do far code
            hood.set(Value.kReverse);
            break;
        }

        curHoodPosition = hoodPosition;
    }


    public boolean isShooterReady() {
        return isShooterReady;
    }

    // Periodically update the dashboard and any PIDs or sensors
    @Override
    public void updatePeriodic() {

        if (!isShooterReady) {
            if (encoder.getVelocity() > (setpoint - 150)) {
                isShooterReady = true;
            }
        }

        // read PID coefficients from SmartDashboard
        //        p = SmartDashboard.getNumber("P Gain", 0);
        //        i = SmartDashboard.getNumber("I Gain", 0);
        //        d = SmartDashboard.getNumber("D Gain", 0);
        //        ff = SmartDashboard.getNumber("Feed Forward", 0);
        //
        //        // if PID coefficients on SmartDashboard have changed, write new values to controller
        //        if((p != RobotConst.SHOOTER_KP)) { pidController.setP(p); }
        //        if((i != RobotConst.SHOOTER_KI)) { pidController.setI(i); }
        //        if((d != RobotConst.SHOOTER_KD)) { pidController.setD(d); }
        //        if((ff != RobotConst.SHOOTER_FF)) { pidController.setFF(ff); }
        pidController.setP(RobotConst.SHOOTER_KP);
        pidController.setI(RobotConst.SHOOTER_KI);
        pidController.setD(RobotConst.SHOOTER_KP);
        pidController.setFF(RobotConst.SHOOTER_FF);
        if(!stop) {
            pidController.setReference(setpoint, ControlType.kVelocity);
        }
        else {
            pidController.setP(0);
            pidController.setI(0);
            pidController.setD(0);
            pidController.setFF(0);
        }
        motor2.set(motor.get());

        SmartDashboard.putNumber("SetPoint", setpoint);
        SmartDashboard.putNumber("ProcessVariable", encoder.getVelocity());

        SmartDashboard.putBoolean("SPEEDMATCH", encoder.getVelocity() > 4990);
        SmartDashboard.putBoolean("SPEEDMATCH2", encoder.getVelocity() > 5050);
        SmartDashboard.putNumber("P Gain", p);
        SmartDashboard.putNumber("I Gain", i);
        SmartDashboard.putNumber("D Gain", d);
        SmartDashboard.putNumber("Feed Forward", ff);



        SmartDashboard.putString("Hood Position", curHoodPosition.toString());
    }

}
