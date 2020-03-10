package frc.robot.subsystems;

import com.torontocodingcollective.sensors.limitSwitch.TLimitSwitch;
import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.speedcontroller.TSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.carousel.DefaultCarouselCommand;

/**
 *
 */
public class CarouselSubsystem extends TSubsystem {

    TSpeedController carouselMotor =
            new TCanSpeedController(
                    RobotMap.CAROUSEL_MOTOR_SPEED_CONTROLLER_TYPE,
                    RobotMap.CAROUSEL_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS,
                    RobotMap.CAROUSEL_MOTOR_ISINVERTED);

    TLimitSwitch carouselFilled = new TLimitSwitch (RobotMap.CAROUSEL_BALL_DETECT_DIO_PORT);

    @Override
    public void init() {

    }

    public void setCarouselMotorSpeed (double speed) {
        carouselMotor.set(speed);
    }

    public void stopCarouselMotor () {
        carouselMotor.set(0);
    }

    public boolean isRobotFull() {

        if (isCarouselFull() == true && Robot.towerSubsystem.isTowerFull() == true){
            return true;
        }
        return false;
    }

    public boolean isCarouselFull() {

        if (carouselFilled.atLimit()) {
            return true;
        }
        return false;
    }

    public double getCarouselMotorSpeed() {
        return carouselMotor.get();
    }

    public double getCarouselMotorCurrent() {
        return Robot.powerSubsystem.getMotorCurrent(RobotMap.CAROUSEL_MOTOR_POWER_PORT);
    }

    public boolean isJammed() {
        // Jammed if > 6A current
        return getCarouselMotorCurrent() > 6;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultCarouselCommand());
    }

    // Periodically update the dashboard and any PIDs or sensors
    @Override
    public void updatePeriodic() {

        SmartDashboard.putNumber("Carousel Speed", carouselMotor.get());
        SmartDashboard.putBoolean("Carousel Filled", isCarouselFull());
        SmartDashboard.putBoolean("Robot Filled", isRobotFull());
        SmartDashboard.putNumber("Carousel Current", getCarouselMotorCurrent());

    }

}
