package frc.robot.subsystems;

import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.shooter.DefaultShooterCommand;

/**
 *
 */
public class ShooterSubsystem extends TSubsystem {

    public enum HoodPosition { CLOSE, MEDIUM, FAR };

    private HoodPosition curHoodPosition;

    @Override
    public void init() {
        // FIXME: Set the initial position to the value at robot setup.
        curHoodPosition = HoodPosition.CLOSE;
    };


    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultShooterCommand());
    }

    /**
     * Set the speed on the shooter
     *
     * @param speed value 0 (stopped) to 1.0 (full speed)
     */
    public void setShooterSpeed(double speed) {

    }

    public void setHoodPosition(HoodPosition hoodPosition) {

        switch (hoodPosition) {
        case CLOSE:
            // Do close code
            break;
        case MEDIUM:
            // Do med code
            break;
        case FAR:
            // Do far code
            break;
        }

        curHoodPosition = hoodPosition;
    }

    // Periodically update the dashboard and any PIDs or sensors
    @Override
    public void updatePeriodic() {
        SmartDashboard.putString("Hood Position", curHoodPosition.toString());
    }

}
