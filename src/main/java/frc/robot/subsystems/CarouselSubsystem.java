package frc.robot.subsystems;

import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.speedcontroller.TSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;

import frc.robot.RobotConst;
import frc.robot.RobotMap;
import frc.robot.commands.climb.DefaultClimbCommand;

/**
 *
 */
public class CarouselSubsystem extends TSubsystem {

    TSpeedController carouselMotor = new TCanSpeedController(RobotMap.CAROUSEL_ROTATORMOTOR_SPEED_CONTROLLER_TYPE, RobotMap.CAROUSEL_ROTATORMOTOR_SPEED_CONTROLLER_ADDRESS);
	
    @Override
	public void init() {

	}

	public void disableCompressor() {

	}

	public void enableCompressor() {

	}
	
	public void spinIntake () {
	    carouselMotor.set(RobotConst.CAROUSEL_INTAKE_SPEED);
	}
	
	public void spinShooter () {
	    carouselMotor.set(RobotConst.CAROUSEL_SHOOTER_SPEED);
	}
	
	public void spinStop () {
	    carouselMotor.set(0);
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
