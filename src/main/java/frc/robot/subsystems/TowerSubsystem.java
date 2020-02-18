package frc.robot.subsystems;

import com.torontocodingcollective.sensors.limitSwitch.TLimitSwitch;
import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.speedcontroller.TSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.tower.DefaultTowerCommand;

/**
 *
 */
public class TowerSubsystem extends TSubsystem {

    TSpeedController towerMotor =
            new TCanSpeedController(
                    RobotMap.TOWER_MOTOR_SPEED_CONTROLLER_TYPE,
                    RobotMap.TOWER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS,
                    RobotMap.TOWER_MOTOR_ISINVERTED);
    
    TSpeedController kickerMotor =
            new TCanSpeedController(
                    RobotMap.KICKER_MOTOR_SPEED_CONTROLLER_TYPE,
                    RobotMap.KICKER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS,
                    RobotMap.KICKER_MOTOR_ISINVERTED);

    TLimitSwitch ballDetector = new TLimitSwitch (RobotMap.TOWER_BALL_DETECT_DIO_PORT);

    @Override
    public void init() {

    }

    public void setTowerMotorSpeed(double speed) {
        towerMotor.set(speed);
    }
    
    public void setKickerMotorSpeed(double speed) {
        kickerMotor.set(speed);
    }

    public boolean isTowerFull() {

        if (ballDetector.atLimit()) {
            return true;
        }
        return false;
    }

    public void stopTowerMotor () {
        towerMotor.set(0);
        kickerMotor.set(0);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultTowerCommand());
    }

    // Periodically update the dashboard and any PIDs or sensors
    @Override
    public void updatePeriodic() {
        SmartDashboard.putNumber("Tower Speed", towerMotor.get());
        SmartDashboard.putBoolean("Tower Filled", isTowerFull());


    }

}
