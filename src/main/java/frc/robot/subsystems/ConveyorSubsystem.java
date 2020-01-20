package frc.robot.subsystems;

import com.torontocodingcollective.subsystem.TSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.conveyor.DefaultConveyorCommand;
import frc.robot.commands.pneumatics.DefaultPneumaticsCommand;

/**
 *
 */
public class ConveyorSubsystem extends TSubsystem {


    @Override
    public void init() {
       
    }

    public void disableCompressor() {
        
    }

    public void enableCompressor() {
        
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultConveyorCommand());
    }

    // Periodically update the dashboard and any PIDs or sensors
    @Override
    public void updatePeriodic() {

    }

}
