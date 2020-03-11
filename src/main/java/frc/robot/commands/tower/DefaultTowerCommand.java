package frc.robot.commands.tower;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.TSafeCommand;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Robot;
import frc.robot.RobotConst;
import frc.robot.oi.OI.TestMode;

/**
 *
 */
public class DefaultTowerCommand extends TSafeCommand {

    private static final String COMMAND_NAME =
            DefaultTowerCommand.class.getSimpleName();

    public DefaultTowerCommand() {

        super(TConst.NO_COMMAND_TIMEOUT, Robot.oi);

        // Use requires() here to declare subsystem dependencies
        requires(Robot.towerSubsystem);
    }

    @Override
    protected String getCommandName() { return COMMAND_NAME; }

    @Override
    protected String getParmDesc() {
        return super.getParmDesc();
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        // Print the command parameters if this is the current
        // called command (it was not sub-classed)
        if (getCommandName().equals(COMMAND_NAME)) {
            logMessage(getParmDesc() + " starting");
        }
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        if (Robot.oi.isTestModeEnabled()) {
            if (Robot.oi.getTestMode() == TestMode.TOWER) {
                Robot.towerSubsystem.setTowerMotorSpeed(Robot.oi.getTestMotorSpeed());
            }
            else {
                Robot.towerSubsystem.stopTowerMotor();
            }
            // If in test mode, then do not look for other buttons
            return;
        }

        // Do not look at the Joystick in auto.
        if (DriverStation.getInstance().isAutonomous()) {
            return;
        }

        // Always shoot before stopping at the sensor
        if (Robot.oi.runShooterTower()) {
            if (Robot.shooterPIDSubsystem.isShooterRunning()) {
                Robot.towerSubsystem.setTowerMotorSpeed(RobotConst.TOWER_SHOOTER_SPEED);
                Robot.towerSubsystem.setKickerMotorSpeed(RobotConst.KICKER_MOTOR_SPEED);
            }
            return;
        }

        // Always check for shooting first ^^^
        if (Robot.towerSubsystem.isTowerFull()) {
            Robot.towerSubsystem.stopTowerMotor();
            return;
        }

        if (Robot.oi.stopTower()) {
            Robot.towerSubsystem.stopTowerMotor();
        }

        if (Robot.oi.runIntakeTower()) {
            Robot.towerSubsystem.setTowerMotorSpeed(RobotConst.TOWER_INTAKE_SPEED);
            Robot.towerSubsystem.setKickerMotorSpeed(RobotConst.KICKER_MOTOR_SPEED);

        }

        if (Robot.oi.runReverseTower()) {
            Robot.towerSubsystem.setTowerMotorSpeed(-RobotConst.TOWER_INTAKE_SPEED * 0.7);
            Robot.towerSubsystem.setKickerMotorSpeed(-RobotConst.KICKER_MOTOR_SPEED * 0.7);
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

}
