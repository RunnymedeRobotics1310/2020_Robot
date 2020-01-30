package frc.robot.subsystems;

import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.speedcontroller.TSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;

import frc.robot.RobotMap;
import frc.robot.commands.tower.DefaultTowerCommand;

/**
 *
 */
public class TowerSubsystem extends TSubsystem {

	TSpeedController towerMotor= new TCanSpeedController(RobotMap.TOWER_MOTOR_SPEED_CONTROLLER_TYPE, RobotMap.TOWER_MOTOR_SPEED_CONTROLLER_ADDRESS);

	@Override
	public void init() {

	}

	public void disableCompressor() {

	}

	public void enableCompressor() {

	}

	public void intake() {

	}

	public void isFull() {

	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DefaultTowerCommand());
	}

	// Periodically update the dashboard and any PIDs or sensors
	@Override
	public void updatePeriodic() {

	}

}
