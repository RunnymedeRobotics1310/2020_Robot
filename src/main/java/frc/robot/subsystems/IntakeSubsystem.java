package frc.robot.subsystems;

import com.torontocodingcollective.speedcontroller.TCanSpeedController;
import com.torontocodingcollective.speedcontroller.TSpeedController;
import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.intake.DefaultIntakeCommand;

/**
 *
 */
public class IntakeSubsystem extends TSubsystem {

    TSpeedController topRollerMotor =
            new TCanSpeedController(
                    RobotMap.INTAKE_TOP_MOTOR_SPEED_CONTROLLER_TYPE,
                    RobotMap.INTAKE_TOP_MOTOR_CAN_SPEED_CONTROLLER_CAN_ADDRESS,
                    RobotMap.INTAKE_TOP_MOTOR_ISINVERTED);

    TSpeedController bottomRollerMotor =
            new TCanSpeedController(
                    RobotMap.INTAKE_BOTTOM_MOTOR_SPEED_CONTROLLER_TYPE,
                    RobotMap.INTAKE_BOTTOM_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS,
                    RobotMap.INTAKE_BOTTOM_MOTOR_ISINVERTED);

    DoubleSolenoid intakePiston = new DoubleSolenoid(RobotMap.INTAKE_EXTEND_PNEUMATIC_PORT, RobotMap.INTAKE_RETRACT_PNEUMATIC_PORT);

    @Override
    public void init() {
        retractIntake();
    }

    public void setIntakeSpeed (double topSpeed, double bottomSpeed) {
        topRollerMotor.set(topSpeed);
        bottomRollerMotor.set(bottomSpeed);
    }

    public void stopIntake() {

        topRollerMotor.set(0);
        bottomRollerMotor.set(0);
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
        intakePiston.set(Value.kForward);
    }

    public void retractIntake() {
        intakePiston.set(Value.kReverse);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultIntakeCommand());
    }

    // Periodically update the dashboard and any PIDs or sensors
    @Override
    public void updatePeriodic() {
        SmartDashboard.putString("Intake Piston Extend", intakePiston.get().toString());
        SmartDashboard.putNumber("Intake Top Roller Speed", topRollerMotor.get());
        SmartDashboard.putNumber("Intake Bottom Roller Speed", bottomRollerMotor.get());
        SmartDashboard.putData("Intake Subsystem", this);
    }

}
