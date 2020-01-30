package frc.robot.subsystems;

import com.torontocodingcollective.sensors.limitSwitch.TLimitSwitch;
import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.speedcontroller.TSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConst;
import frc.robot.RobotMap;
import frc.robot.commands.climb.DefaultClimbCommand;

/**
 *
 */
public class CarouselSubsystem extends TSubsystem {

    TSpeedController carouselMotor = new TCanSpeedController(RobotMap.CAROUSEL_ROTATORMOTOR_SPEED_CONTROLLER_TYPE, RobotMap.CAROUSEL_ROTATORMOTOR_SPEED_CONTROLLER_ADDRESS);
    TLimitSwitch carouselFilled = new TLimitSwitch (RobotMap.CAROUSEL_BALL_DETECT_LIMIT_SWITCH);
    @Override
	public void init() {

	}

	public void spinCarouselMotor (double speed) {
	    carouselMotor.set(speed);
	}
	
	public void stopCarouselMotor () {
	    carouselMotor.set(0);
	}
	
	public boolean isFull() {
        if (carouselFilled.atLimit()) {
            return true;
        }
        else
            return false;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DefaultClimbCommand());
	}

	// Periodically update the dashboard and any PIDs or sensors
	@Override
	public void updatePeriodic() {
        SmartDashboard.putNumber("Carousel Speed", carouselMotor.get());
        SmartDashboard.putBoolean("Carousel Filled", carouselFilled.atLimit());

	}

}
