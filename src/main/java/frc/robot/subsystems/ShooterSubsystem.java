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

    /** Set the speed on the shooter
     *
     * @param speed value 0 to 1.0
     */
    public void setShooterSpeed(double speed) {

    }

    // Periodically update the dashboard and any PIDs or sensors
    @Override
    public void updatePeriodic() {

    }

}
