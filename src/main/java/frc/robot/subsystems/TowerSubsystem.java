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

    TSpeedController towerMotor =
            new TCanSpeedController(
                    RobotMap.TOWER_MOTOR_SPEED_CONTROLLER_TYPE,
                    RobotMap.TOWER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS,
                    RobotMap.TOWER_MOTOR_ISINVERTED);

    // The tower and kicker are a single motor on the production robot
    TSpeedController kickerMotor = null;

    TLimitSwitch ballDetector = new TLimitSwitch (RobotMap.TOWER_BALL_DETECT_DIO_PORT);

    @Override
    public void init() {
        // Only initialize the kicker motor on the practice robot
        if (RobotConst.robot.equals(RobotConst.PRACTICE_ROBOT)) {
            kickerMotor =
                    new TCanSpeedController(
                            RobotMap.KICKER_MOTOR_SPEED_CONTROLLER_TYPE,
                            RobotMap.KICKER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS,
                            RobotMap.KICKER_MOTOR_ISINVERTED);
        }
    }

    public void setTowerMotorSpeed(double speed) {
        towerMotor.set(speed);
    }

    public void setKickerMotorSpeed(double speed) {
        if (kickerMotor != null) {
            kickerMotor.set(speed);
        }
    }

    public boolean isTowerFull() {

        if (ballDetector.atLimit()) {
            return true;
        }
        return false;
    }

    public void stopTowerMotor () {
        towerMotor.set(0);

        if (kickerMotor != null) {
            kickerMotor.set(0);
        }
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultTowerCommand());
    }

    // Periodically update the dashboard and any PIDs or sensors
    @Override
    public void updatePeriodic() {

        SmartDashboard.putNumber("Tower Speed", towerMotor.get());
        if (kickerMotor != null) {
            SmartDashboard.putNumber("Kicker Speed", kickerMotor.get());
        }

        SmartDashboard.putBoolean("Tower Filled", isTowerFull());

    }

}
