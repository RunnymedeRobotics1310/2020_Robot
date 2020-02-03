package frc.robot.subsystems;

import com.torontocodingcollective.sensors.limitSwitch.TLimitSwitch;
import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.speedcontroller.TSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConst;
import frc.robot.RobotMap;
import frc.robot.commands.tower.DefaultTowerCommand;

/**
 *
 */
public class TowerSubsystem extends TSubsystem {

	TSpeedController towerMotor= new TCanSpeedController(RobotMap.TOWER_MOTOR_SPEED_CONTROLLER_TYPE, RobotMap.TOWER_MOTOR_SPEED_CONTROLLER_ADDRESS);
	TLimitSwitch ballDetector = new TLimitSwitch (RobotMap.TOWER_BALL_DETECT_LIMIT_SWITCH);

	@Override
	public void init() {
		
	}

	public void setTowerMotorSpeed(double speed) {
		towerMotor.set(RobotConst.TOWER_INTAKE_SPEED);
	}

	public boolean isFull() {

		if (ballDetector.atLimit()) {
			return true;
		}
		else
			return false;
	}

	public void stopTowerMotor () {
		towerMotor.set(0);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DefaultTowerCommand());
	}

	// Periodically update the dashboard and any PIDs or sensors
	@Override
	public void updatePeriodic() {
		SmartDashboard.putNumber("Tower Speed", towerMotor.get());
		SmartDashboard.putBoolean("Tower Filled", ballDetector.atLimit());


	}

}
