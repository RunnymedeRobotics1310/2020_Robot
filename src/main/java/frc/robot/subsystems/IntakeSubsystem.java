package frc.robot.subsystems;

import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.speedcontroller.TSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotConst;
import frc.robot.RobotMap;
import frc.robot.commands.intake.DefaultIntakeCommand;

/**
 *
 */
public class IntakeSubsystem extends TSubsystem {

	TSpeedController firstMotor = new TCanSpeedController(RobotMap.INTAKE_FIRSTMOTOR_SPEED_CONTROLLER_TYPE, RobotMap.INTAKE_FIRSTMOTOR_SPEED_CONTROLLER_ADDRESS);
	TSpeedController secondMotor = new TCanSpeedController(RobotMap.INTAKE_SECONDMOTOR_SPEED_CONTROLLER_TYPE, RobotMap.INTAKE_SECONDMOTOR_SPEED_CONTROLLER_ADDRESS);

	Solenoid intakePiston = new Solenoid(RobotMap.INTAKE_EXTEND_PNEUMATIC_PORT);

	@Override
	public void init() {

	}

	public void disableCompressor() {

	}

	public void enableCompressor() {

	}

	public void startGroundIntake() {
		firstMotor.set(RobotConst.INTAKE_SPEED);
		secondMotor.set(RobotConst.INTAKE_SPEED);
	}

	public void startFeederIntake() {
		firstMotor.set(-RobotConst.INTAKE_SPEED);
		secondMotor.set(RobotConst.INTAKE_SPEED);
	}

	public void stopIntake() {
		firstMotor.set(0);
		secondMotor.set(0);

		retractInake();
	}

	public void extendIntake() {
		intakePiston.set(true);;
	}

	public void retractInake() {
		intakePiston.set(false);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DefaultIntakeCommand());
	}

	// Periodically update the dashboard and any PIDs or sensors
	@Override
	public void updatePeriodic() {

	}

}
