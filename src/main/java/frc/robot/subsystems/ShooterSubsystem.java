package frc.robot.subsystems;

import com.torontocodingcollective.subsystem.TSubsystem;

import frc.robot.commands.pneumatics.DefaultPneumaticsCommand;

/**
 *
 */
public class ShooterSubsystem extends TSubsystem {


	@Override
	public void init() {

	};


	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DefaultPneumaticsCommand());
	}

	// Periodically update the dashboard and any PIDs or sensors
	@Override
	public void updatePeriodic() {

	}

}
