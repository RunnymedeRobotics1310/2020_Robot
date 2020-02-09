package frc.robot.commands;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.drive.TDriveTimeCommand;
import com.torontocodingcollective.commands.gyroDrive.TDriveOnHeadingDistanceCommand;
import com.torontocodingcollective.commands.gyroDrive.TRotateToHeadingCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.drive.DriveBackwardsCommand;
import frc.robot.commands.drive.DriveOnCurveCommand;
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
            // Go forward 5 ft
        	// distance inches, degrees, speed, timeout
            this.addSequential(
                    new TDriveOnHeadingDistanceCommand(120, 0, .95, 15, TConst.BRAKE_WHEN_FINISHED,
                            Robot.oi, Robot.driveSubsystem));
        }

        /* ***********************************************************
         *  Drive Straight with with no GyroPID control
         *  ***********************************************************/
        if (pattern.equals(AutoSelector.PICK_UP_2_SHOOT_5)) {
        	// start on far left side of field lining up with opponents trench, go pick up 2 balls and shoot 5
        	// distance inches, degrees, speed, timeout
        	Robot.driveSubsystem.setGyroAngle(180);
 
        	
        	//Start intake motor
        	
        	this.addSequential(
                    new TDriveOnHeadingDistanceCommand(95, 180 , 1, 15, TConst.BRAKE_WHEN_FINISHED,
                            Robot.oi, Robot.driveSubsystem));
        	
        	this.addSequential(
        			new DriveBackwardsCommand(12, 1));
        	
        	this.addSequential(
        			new TRotateToHeadingCommand(200, Robot.oi, Robot.driveSubsystem));
        	
        	this.addSequential(
                    new TDriveOnHeadingDistanceCommand(12, 200, 1, 15, TConst.BRAKE_WHEN_FINISHED,
                            Robot.oi, Robot.driveSubsystem));
        	
        	this.addSequential(
        			new TRotateToHeadingCommand(255, Robot.oi, Robot.driveSubsystem));
        	
        	this.addSequential(
        			new DriveBackwardsCommand(188, 1));
        	
        	this.addSequential(
        			new TRotateToHeadingCommand(190, Robot.oi, Robot.driveSubsystem));
        	
        	//Start shooter midway through last drive
                
        }
        
        if (pattern.equals(AutoSelector.SHOOT_3_PICK_UP_3_SHOOT_3)) {
        	//auto pattern starting to the left of the goal, shooting 3 balls, get 3 more balls and shoot 3
        	Robot.driveSubsystem.setGyroAngle(200);
        	
        	//Shoot

        	this.addSequential(
        			new TDriveOnHeadingDistanceCommand(112, 220, 1, 15, TConst.BRAKE_WHEN_FINISHED,
        					Robot.oi, Robot.driveSubsystem));
        	//drive backwards to the rendezvous point
        	this.addSequential(
        			new TRotateToHeadingCommand(120, Robot.oi, Robot.driveSubsystem));
        	//turn to get better angle to line up for balls			
        	this.addSequential(
        			new TDriveOnHeadingDistanceCommand(12, 120, 1, 15, TConst.BRAKE_WHEN_FINISHED,
        					Robot.oi, Robot.driveSubsystem));		
        	//drive backwards to line up for balls
        	this.addSequential(
        			new TRotateToHeadingCommand(70, Robot.oi, Robot.driveSubsystem));
        	//rotate in line with balls
        	this.addSequential(
        			new TDriveOnHeadingDistanceCommand(40, 70, 1, 15, TConst.BRAKE_WHEN_FINISHED,
        					Robot.oi, Robot.driveSubsystem));
        	//drive into balls with intake side
        	this.addSequential(
        			new TRotateToHeadingCommand(200, Robot.oi, Robot.driveSubsystem));
        	//line up for shot and shoot
        }
        


        /* ***********************************************************
         *  Drive forward 2ft and then drive a 3ft box pattern
         *  ***********************************************************/
        if (pattern.equals(AutoSelector.NO_DRIVE)) {
//            this.addSequential(
//            		new TDriveOnHeadingDistanceCommand())
        	
        }
        
        
        if (pattern.equals(AutoSelector.SHOOT_3_PICK_UP_3_TRENCH)) {
        	Robot.driveSubsystem.setGyroAngle(150);
        	//shoot 3 balls
        	this.addSequential(
        			new TRotateToHeadingCommand(180, Robot.oi, Robot.driveSubsystem));
        	
        	this.addSequential(
        			new TDriveOnHeadingDistanceCommand(156, 180, 1, 15, TConst.BRAKE_WHEN_FINISHED,
        					Robot.oi, Robot.driveSubsystem));
        	this.addSequential(
        			new TRotateToHeadingCommand(155, Robot.oi, Robot.driveSubsystem));
        	
        }
        
        if (pattern.equals(AutoSelector.DRIVE_ON_CURVE)) {
        	this.addSequential(
        			new DriveOnCurveCommand(15, .5, 12, true));
      	
      }
        
    }
}
