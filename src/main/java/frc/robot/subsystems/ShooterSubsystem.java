package frc.robot.subsystems;

import com.torontocodingcollective.subsystem.TSubsystem;

import frc.robot.commands.shooter.DefaultShooterCommand;

/**
 *
 */
public class ShooterSubsystem extends TSubsystem {


	@Override
	public void init() {

	};


	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DefaultShooterCommand());
	}

	// Periodically update the dashboard and any PIDs or sensors
	@Override
	public void updatePeriodic() {

	}

}
