package frc.robot.commands;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.drive.TDriveTimeCommand;
import com.torontocodingcollective.commands.gyroDrive.TDriveOnHeadingDistanceCommand;
import com.torontocodingcollective.commands.gyroDrive.TRotateToHeadingCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.drive.DriveBackwardsCommand;
import frc.robot.oi.AutoSelector;

/**
 * AutonomousCommand
 * <p>
 * This class extends the CommandGroup class which allows for a string of
 * commands to be chained together to create complex auto patterns.
 */
public class AutonomousCommand extends CommandGroup {

    public static final char LEFT   = 'L';
    public static final char RIGHT  = 'R';
    public static final char CENTER = 'C';

    /**
     * Autonomous Command
     * <p>
     * Construct an Autonomous Command to perform the auto portion of the robot
     * game. This command will be built when the constructor is called and each
     * element of the command will execute in order.
     * <p>
     * When a parallel command is started, it will act at the same time as all other
     * parallel commands and the next serial command. Parallel commands can end
     * before the serial command, however, when the serial command is complete, all
     * parallel commands will be interrupted at that time if they have not already
     * finished.
     * <p>
     * Since the commands are all constructed at the same instant (when this
     * constructor is called), the commands should not read sensor information in
     * the constructor. All commands should read any relevant sensor information
     * (speed, heading, position) in the init() method of the command. The init()
     * method will be run when the command starts and so can get the robot
     * information at the start of the command, the constructor will be run
     * immediately when the Auto CommandGroup is constructed, and will not have the
     * sensor information relevant to when the command is run.
     */
    public AutonomousCommand() {

        // getting info
        String pattern            = AutoSelector.getPattern();

        // Print out the user selection and Game config for debug later
        System.out.println("Auto Command Configuration");
        System.out.println("--------------------------");
        System.out.println("Pattern        : " + pattern);

        /* ***********************************************************
         *  Drive Straight using GyroPID control
         *  ***********************************************************/
        if (pattern.equals(AutoSelector.PATTERN_STRAIGHT)) {
            // Go forward 2 ft
        	// distance inches, degrees, speed, timeout
            this.addSequential(
                    new TDriveOnHeadingDistanceCommand(60, 0, .95, 15, TConst.BRAKE_WHEN_FINISHED,
                            Robot.oi, Robot.driveSubsystem));
        }

        /* ***********************************************************
         *  Drive Straight with with no GyroPID control
         *  ***********************************************************/
        if (pattern.equals(AutoSelector.PICK_UP_2_SHOOT_5)) {
            // Go forward 2 ft
        	// distance inches, degrees, speed, timeout
        	Robot.driveSubsystem.setGyroAngle(180);
 
        	this.addSequential(
                    new TDriveOnHeadingDistanceCommand(80, 180 , .5, 15, TConst.BRAKE_WHEN_FINISHED,
                            Robot.oi, Robot.driveSubsystem));
        	this.addSequential(
        			new DriveBackwardsCommand(12, .5));
        	
        	this.addSequential(
                    new TDriveOnHeadingDistanceCommand(18, 200 , .5, 15, TConst.BRAKE_WHEN_FINISHED,
                            Robot.oi, Robot.driveSubsystem));
        	
        	this.addSequential(
        			new TRotateToHeadingCommand( 255, Robot.oi, Robot.driveSubsystem));
        	
        	this.addSequential(
        			new DriveBackwardsCommand(100, .5));
                
        }


        /* ***********************************************************
         *  Drive forward 2ft and then drive a 3ft box pattern
         *  ***********************************************************/
        if (pattern.equals(AutoSelector.NO_DRIVE)) {
            // Go forward 2 ft
        	this.addSequential(
        			new DriveBackwardsCommand(132, .5));
        	
        }
    }
}
