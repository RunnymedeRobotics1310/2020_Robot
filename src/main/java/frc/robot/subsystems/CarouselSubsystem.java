package frc.robot.subsystems;

import com.torontocodingcollective.subsystem.TSubsystem;

import frc.robot.commands.climb.DefaultClimbCommand;

/**
 *
 */
public class CarouselSubsystem extends TSubsystem {


	@Override
	public void init() {

	}

	public void disableCompressor() {

	}

	public void enableCompressor() {

	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DefaultClimbCommand());
	}

	// Periodically update the dashboard and any PIDs or sensors
	@Override
	public void updatePeriodic() {

	}

}
