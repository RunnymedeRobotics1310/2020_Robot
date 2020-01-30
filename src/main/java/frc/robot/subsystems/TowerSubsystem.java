package frc.robot.subsystems;

import com.torontocodingcollective.subsystem.TSubsystem;

import frc.robot.commands.tower.DefaultTowerCommand;

/**
 *
 */
public class TowerSubsystem extends TSubsystem {


	@Override
	public void init() {

	}

	public void disableCompressor() {

	}

	public void enableCompressor() {

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
