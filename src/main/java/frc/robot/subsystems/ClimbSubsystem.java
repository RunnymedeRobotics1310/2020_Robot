package frc.robot.subsystems;

import com.torontocodingcollective.sensors.limitSwitch.TLimitSwitch;
import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.speedcontroller.TSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;
import frc.robot.commands.climb.DefaultClimbCommand;

/**
 *
 */
public class ClimbSubsystem extends TSubsystem {

	TSpeedController leftClimbMotor = new TCanSpeedController
			(RobotMap.LEFT_CLIMB_MOTOR_SPEED_CONTROLLER_TYPE,
					RobotMap.LEFT_CLIMB_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS);

	TSpeedController rightClimbMotor = new TCanSpeedController
			(RobotMap.RIGHT_CLIMB_MOTOR_SPEED_CONTROLLER_TYPE,
					RobotMap.RIGHT_CLIMB_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS);

	DoubleSolenoid rightClimbPiston = new DoubleSolenoid(RobotMap.RIGHT_CLIMB_EXTEND_PNEUMATIC_PORT, RobotMap.RIGHT_CLIMB_RETRACT_PNEUMATIC_PORT);
	DoubleSolenoid leftClimbPiston = new DoubleSolenoid(RobotMap.LEFT_CLIMB_EXTEND_PNEUMATIC_PORT, RobotMap.LEFT_CLIMB_RETRACT_PNEUMATIC_PORT);

	TLimitSwitch leftClimbTopLimit = new TLimitSwitch (RobotMap.LEFT_CLIMB_TOP_DETECT_DIO_PORT);
	TLimitSwitch rightClimbTopLimit = new TLimitSwitch (RobotMap.RIGHT_CLIMB_TOP_DETECT_DIO_PORT);
	TLimitSwitch leftClimbBottomLimit = new TLimitSwitch (RobotMap.LEFT_CLIMB_BOTTOM_DETECT_DIO_PORT);
	TLimitSwitch rightClimbBottomLimit = new TLimitSwitch (RobotMap.RIGHT_CLIMB_BOTTOM_DETECT_DIO_PORT);

	@Override
	public void init() {

	}

	public void extendRightClimbPiston() {
		rightClimbPiston.set(Value.kForward);
	}

	public void retractRightClimbPiston() {
		rightClimbPiston.set(Value.kReverse);

	}

	public void extendLeftClimbPiston() {
		leftClimbPiston.set(Value.kForward);
	}

	public void retractLeftClimbPiston() {
		leftClimbPiston.set(Value.kReverse);
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

	public boolean isLeftClimbTopLimit() {

		if(leftClimbTopLimit.atLimit()) {
			return true;
		}
		return false;
	}

	public boolean isRightClimbTopLimit() {

		if(rightClimbTopLimit.atLimit()) {
			return true;
		}
		return false;
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

	public void stopBothClimb() {
		stopLeftClimbMotor();
		stopRightClimbMotor();

		extendLeftClimbPiston();
		extendRightClimbPiston();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DefaultClimbCommand());
	}


	// Periodically update the dashboard and any PIDs or sensors
	@Override
	public void updatePeriodic() {

		if (leftClimbMotor.get() < 0 && leftClimbBottomLimit.atLimit()) {
			leftClimbMotor.set(0);
			extendLeftClimbPiston();
		}

		if (leftClimbMotor.get()> 0 && leftClimbTopLimit.atLimit()) {
			leftClimbMotor.set(0);
		}

		if (rightClimbMotor.get() < 0 && rightClimbBottomLimit.atLimit()) {
			rightClimbMotor.set(0);
			extendRightClimbPiston();
		}

		if (rightClimbMotor.get()> 0 && rightClimbTopLimit.atLimit()) {
			rightClimbMotor.set(0);
		}

	}

}
