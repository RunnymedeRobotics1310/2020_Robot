package frc.robot.commands;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.commands.gyroDrive.TDriveBackwardsOnHeadingDistanceCommand;
import com.torontocodingcollective.commands.gyroDrive.TDriveOnHeadingDistanceCommand;
import com.torontocodingcollective.commands.gyroDrive.TRotateToHeadingCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.carousel.IntakeCarouselCommand;
import frc.robot.commands.carousel.StopCarouselCommand;
import frc.robot.commands.drive.DriveBackwardsCommand;
import frc.robot.commands.drive.DriveOnCurveCommand;
import frc.robot.commands.drive.GyroTurnCommand;
import frc.robot.commands.intake.GroundIntakeCommand;
import frc.robot.commands.intake.StopIntakeCommand;
import frc.robot.commands.shooter.SetShooterSpeedCommand;
import frc.robot.commands.shooter.ShootCommand;
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
//    	this.addSequential(new GyroTurnCommand());

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

            Robot.driveSubsystem.setGyroAngle(180);
            
            // Go forward 5 ft
        	// distance inches, degrees, speed, timeout
            this.addSequential(
                    new TDriveBackwardsOnHeadingDistanceCommand(120, 0, .3, 15, TConst.BRAKE_WHEN_FINISHED,
                            Robot.oi, Robot.driveSubsystem));
        }

        /* ***********************************************************
         *  Drive Straight with with no GyroPID control
         *  ***********************************************************/
        if (pattern.equals(AutoSelector.PICK_UP_2_SHOOT_5)) {
            // start on far left side of field lining up with opponents trench, go pick up 2 balls and shoot 5
            // distance inches, degrees, speed, timeout
            Robot.driveSubsystem.setGyroAngle(0);
 
            this.addSequential (
            		new GroundIntakeCommand());
            this.addSequential(
                    new IntakeCarouselCommand());
            // drive to pick up first ball
            this.addSequential(
                    new TDriveBackwardsOnHeadingDistanceCommand(100, 180 , 0.4, 15, TConst.BRAKE_WHEN_FINISHED,
                            Robot.oi, Robot.driveSubsystem));
            // drive back to reposition for second ball
//            this.addSequential(
//                    new TDriveOnHeadingDistanceCommand(8, 0, 0.2, 15,TConst.BRAKE_WHEN_FINISHED,
//                            Robot.oi, Robot.driveSubsystem ));
//            // rotate to position at other ball
//            this.addSequential(
//                    new TRotateToHeadingCommand(60, Robot.oi, Robot.driveSubsystem));
////            //drive into ball
//            this.addSequential(
//                    new TDriveBackwardsOnHeadingDistanceCommand(15, 240, 0.2, 15, TConst.BRAKE_WHEN_FINISHED,
//                            Robot.oi, Robot.driveSubsystem));
//            
            
            
            
            
            this.addSequential(
                    new TRotateToHeadingCommand(75, Robot.oi, Robot.driveSubsystem));
            
            this.addSequential(
                    new DelayCommand(0.5));
            
            
   
            this.addSequential(
            		new SetShooterSpeedCommand(2600));
            
            this.addSequential(
                    new StopIntakeCommand());
            
            this.addSequential(
                    new StopCarouselCommand());
            
            //this.addSequential(
//              //      new DriveOnCurveCommand(75, -1, 30, true));
//            //rotate away from trench
//            
//            //this.addSequential(
//                    //new DriveOnCurveCommand(100, -1, 70, false));
//            // curve towards shooter
//            
            this.addSequential(
                    new TDriveOnHeadingDistanceCommand(135, 75, 0.4, 15,TConst.BRAKE_WHEN_FINISHED,
                            Robot.oi, Robot.driveSubsystem ));
//            
            this.addSequential(
                    new TRotateToHeadingCommand(20, Robot.oi, Robot.driveSubsystem));
            this.addSequential(new GyroTurnCommand());
//            // position right at goal
//            
            this.addSequential(
            		new ShootCommand());
            //Start shooter midway through last drive               
        }
        
        if (pattern.equals(AutoSelector.SHOOT_3_PICK_UP_3_SHOOT_3)) {
            //auto pattern starting to the left of the goal, shooting 3 balls, get 3 more balls and shoot 3
            Robot.driveSubsystem.setGyroAngle(200);
            
            //Shoot

            this.addSequential(
                    new TDriveOnHeadingDistanceCommand(85, 220, 1, 15, TConst.BRAKE_WHEN_FINISHED,
                            Robot.oi, Robot.driveSubsystem));
            //drive backwards on angle to get closer to 3 balls
            
            this.addSequential(
                    new DriveOnCurveCommand(85, 1, 15, true));
            //rotate to align with balls 
            
            //drive backwards to the rendezvous point
            //this.addSequential(
                    //new TRotateToHeadingCommand(120, Robot.oi, Robot.driveSubsystem));
            //turn to get better angle to line up for balls         
            //this.addSequential(
                    //new TDriveOnHeadingDistanceCommand(12, 120, 1, 15, TConst.BRAKE_WHEN_FINISHED,
                        //  Robot.oi, Robot.driveSubsystem));       
            //drive backwards to line up for balls
            //this.addSequential(
                    //new TRotateToHeadingCommand(70, Robot.oi, Robot.driveSubsystem));
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
//                  new TDriveOnHeadingDistanceCommand())
           
            
        }
        
        
        if (pattern.equals(AutoSelector.SHOOT_3_PICK_UP_3_TRENCH)) {
            Robot.driveSubsystem.setGyroAngle(160);
            //shoot 3 balls
            this.addSequential(
                    new TRotateToHeadingCommand(180, Robot.oi, Robot.driveSubsystem));
            //square up to trench
            
            this.addSequential(
                    new TDriveOnHeadingDistanceCommand(156, 180, 1, 15, TConst.BRAKE_WHEN_FINISHED,
                            Robot.oi, Robot.driveSubsystem));
            //drive intake side to get balls
            
            this.addSequential(
                    new TRotateToHeadingCommand(155, Robot.oi, Robot.driveSubsystem));
            //rotate to face shooter and shoot
            
        }
        
        if (pattern.equals(AutoSelector.DRIVE_ON_CURVE)) {
            // test drive on curve command
            this.addSequential(
                    new DriveOnCurveCommand(188, -.75, 100, false));
        
      }
       
        if (pattern.equals(AutoSelector.SHOOT_5_BALLS)) {
        	
        	this.addSequential(
        			new SetShooterSpeedCommand(2200));
        	
        }
    }
}
