package frc.robot.subsystems;

import com.torontocodingcollective.sensors.encoder.TEncoder;
import com.torontocodingcollective.sensors.limitSwitch.TLimitSwitch;
import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.speedcontroller.TSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConst;
import frc.robot.RobotMap;
import frc.robot.commands.climb.DefaultClimbCommand;

/**
 *
 */
public class ClimbSubsystem extends TSubsystem {

    TSpeedController leftClimbMotor = new TCanSpeedController(
            RobotMap.LEFT_CLIMB_MOTOR_SPEED_CONTROLLER_TYPE,
            RobotMap.LEFT_CLIMB_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS,
            RobotMap.LEFT_CLIMB_MOTOR_ISINVERTED);

    TSpeedController rightClimbMotor = new TCanSpeedController(
            RobotMap.RIGHT_CLIMB_MOTOR_SPEED_CONTROLLER_TYPE,
            RobotMap.RIGHT_CLIMB_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS,
            RobotMap.RIGHT_CLIMB_MOTOR_ISINVERTED);

    Solenoid rightClimbPiston = new Solenoid(RobotMap.RIGHT_CLIMB_LOCK_PNEUMATIC_PORT);
    Solenoid leftClimbPiston = new Solenoid(RobotMap.LEFT_CLIMB_LOCK_PNEUMATIC_PORT);

    TLimitSwitch leftClimbBottomLimit = new TLimitSwitch (RobotMap.LEFT_CLIMB_BOTTOM_DETECT_DIO_PORT);
    TLimitSwitch rightClimbBottomLimit = new TLimitSwitch (RobotMap.RIGHT_CLIMB_BOTTOM_DETECT_DIO_PORT);

    TEncoder leftClimbEncoder  = leftClimbMotor.getEncoder(RobotMap.LEFT_CLIMB_ENCODER_ISINVERTED);
    TEncoder rightClimbEncoder = rightClimbMotor.getEncoder(RobotMap.RIGHT_CLIMB_ENCODER_ISINVERTED);

    @Override
    public void init() {

    }

    public boolean isRightClimbPistonExtended() {
        if (rightClimbPiston.get()) {
            return true;
        }
        return false;
    }

    public boolean isLeftClimbPistonExtended() {
        if(leftClimbPiston.get()) {
            return true;
        }
        return false;
    }

    public void extendRightClimbPiston() {
        //		rightClimbPiston.set(Value.kForward);
    }

    public void retractRightClimbPiston() {
        //		rightClimbPiston.set(Value.kReverse);

    }

    public void extendLeftClimbPiston() {
        //		leftClimbPiston.set(Value.kForward);
    }

    public void retractLeftClimbPiston() {
        //		leftClimbPiston.set(Value.kReverse);
    }

    public void setLeftClimbSpeed(double speed) {
        leftClimbMotor.set(speed);

        if (speed > 0) {
            retractLeftClimbPiston();
        }
    }

    public void setRightClimbSpeed(double speed) {
        rightClimbMotor.set(speed);

        if (speed > 0) {
            retractRightClimbPiston();
        }
    }

    public boolean isLeftClimbBottomLimit() {

        if(leftClimbBottomLimit.atLimit()) {
            return true;
        }
        return false;
    }

    public boolean isRightClimbBottomLimit() {

        if(rightClimbBottomLimit.atLimit()) {
            return true;
        }
        return false;
    }
    public void stopLeftClimbMotor() {
        leftClimbMotor.set(0);
    }

    public void stopRightClimbMotor() {
        rightClimbMotor.set(0);
    }

    public void lockClimb() {
        stopLeftClimbMotor();
        stopRightClimbMotor();

        extendLeftClimbPiston();
        extendRightClimbPiston();
    }

    public boolean isLeftClimbAtExtendedLimit() {
        if (leftClimbEncoder == null) {
            return false;
        }
        return leftClimbEncoder.get() > RobotConst.CLIMB_EXTENDED_ENCODER_COUNTS;
    }

    public boolean isRightClimbAtExtendedLimit() {
        if (rightClimbEncoder == null) {
            return false;
        }
        return rightClimbEncoder.get() > RobotConst.CLIMB_EXTENDED_ENCODER_COUNTS;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultClimbCommand());
    }

    // Periodically update the dashboard and any PIDs or sensors
    @Override
    public void updatePeriodic() {

        if (leftClimbMotor.get() < 0 && (leftClimbBottomLimit.atLimit() || isLeftClimbPistonExtended())) {
            leftClimbMotor.set(0);
            extendLeftClimbPiston();
        }

        if (leftClimbMotor.get() > 0 && isLeftClimbAtExtendedLimit()) {
            leftClimbMotor.set(0);
        }

        if (leftClimbMotor.get() > 0 && isLeftClimbPistonExtended()) {
            retractLeftClimbPiston();
        }

        if (rightClimbMotor.get() < 0 && (rightClimbBottomLimit.atLimit() || isRightClimbPistonExtended())) {
            rightClimbMotor.set(0);
            extendRightClimbPiston();
        }

        if (rightClimbMotor.get() > 0 && isRightClimbAtExtendedLimit()) {
            rightClimbMotor.set(0);
        }

        if (rightClimbMotor.get() > 0 && isRightClimbPistonExtended()) {
            retractRightClimbPiston();
        }

        SmartDashboard.putNumber("Left Climb Motor Speed", leftClimbMotor.get());
        SmartDashboard.putBoolean("Left Climb Bottom Limit", leftClimbBottomLimit.atLimit());
        SmartDashboard.putNumber("Left Climb Encoder", leftClimbEncoder.get());
        SmartDashboard.putBoolean("Left Climb Upper Limit", isLeftClimbAtExtendedLimit());

        SmartDashboard.putNumber("Right Climb Motor Speed", rightClimbMotor.get());
        SmartDashboard.putBoolean("Right Climb Bottom Limit", rightClimbBottomLimit.atLimit());
        SmartDashboard.putNumber("Right Climb Encoder", rightClimbEncoder.get());
        SmartDashboard.putBoolean("Right Climb Upper Limit", isRightClimbAtExtendedLimit());

    }

}
