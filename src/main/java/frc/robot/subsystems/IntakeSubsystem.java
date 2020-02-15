package frc.robot.subsystems;

import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.speedcontroller.TSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.intake.DefaultIntakeCommand;

/**
 *
 */
public class IntakeSubsystem extends TSubsystem {

    TSpeedController topRollerMotor = new TCanSpeedController(RobotMap.INTAKE_FIRSTMOTOR_SPEED_CONTROLLER_TYPE_ISINVERTED, RobotMap.INTAKE_FIRSTMOTOR_SPEED_CONTROLLER_ADDRESS);
    TSpeedController bottomRollerMotor = new TCanSpeedController(RobotMap.INTAKE_SECONDMOTOR_SPEED_CONTROLLER_TYPE_ISINVERTED, RobotMap.INTAKE_SECONDMOTOR_SPEED_CONTROLLER_ADDRESS);

    Solenoid intakePiston = new Solenoid(RobotMap.INTAKE_EXTEND_PNEUMATIC_PORT);

    @Override
    public void init() {

    }

    public void setIntakeSpeed (double topSpeed, double bottomSpeed) {
        topRollerMotor.set(topSpeed);
        bottomRollerMotor.set(bottomSpeed);
    }

    public void stopIntake() {

        topRollerMotor.set(0);
        bottomRollerMotor.set(0);

        retractIntake();
    }

    public boolean intakeRunning() {

        if (topRollerMotor.get() > 0) {
            return true;
        }

        if (bottomRollerMotor.get() > 0) {
            return true;
        }
        return false;

    }


    public void extendIntake() {
        intakePiston.set(true);;
    }

    public void retractIntake() {
        intakePiston.set(false);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultIntakeCommand());
    }

    // Periodically update the dashboard and any PIDs or sensors
    @Override
    public void updatePeriodic() {
        SmartDashboard.putBoolean("Intake Piston Extend", intakePiston.get());
        SmartDashboard.putNumber("Intake Top Roller Speed", topRollerMotor.get());
        SmartDashboard.putNumber("Intake Bottom Roller Speed", bottomRollerMotor.get());
        SmartDashboard.putData("Intake Subsystem", this);
    }

}
