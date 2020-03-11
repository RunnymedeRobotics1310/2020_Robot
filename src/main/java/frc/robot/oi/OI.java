package frc.robot.oi;

import com.torontocodingcollective.oi.TAxis;
import com.torontocodingcollective.oi.TButton;
import com.torontocodingcollective.oi.TButtonPressDetector;
import com.torontocodingcollective.oi.TGameController;
import com.torontocodingcollective.oi.TGameController_Logitech;
import com.torontocodingcollective.oi.TOi;
import com.torontocodingcollective.oi.TRumbleManager;
import com.torontocodingcollective.oi.TStick;
import com.torontocodingcollective.oi.TStickPosition;
import com.torontocodingcollective.oi.TToggle;
import com.torontocodingcollective.oi.TTrigger;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.HoodPosition;
import frc.robot.Robot;
import frc.robot.RobotConst;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

/**
 * Driver Controller (inherited from TOi)
 *
 * Sticks: Right Stick = Drive Stick Left Stick = Drive Stick Right Stick Press
 * = Toggle PIDs Left Stick Press = Toggle Compressor
 *
 * Buttons: Start Button = Reset Encoders and Gyro Back Button = Cancel any
 * Command
 *
 * Bumpers/Triggers: Left Bumper = Turbo shift
 *
 * POV: Any Angle = Rotate to the Pressed Angle
 *
 */
public class OI extends TOi {

    private TGameController driverController = new TGameController_Logitech(0);
    private TRumbleManager  driverRumble     = new TRumbleManager("Driver", driverController);

    private TGameController operatorController = new TGameController_Logitech(1);
    private TRumbleManager  operatorRumble     = new TRumbleManager("Operator", operatorController);


    private TToggle         compressorToggle = new TToggle(driverController, TStick.LEFT);
    private TToggle         speedPidToggle   = new TToggle(driverController, TStick.RIGHT);

    private DriveSelector   driveSelector    = new DriveSelector();

    private HoodPosition	previousHoodPosition = HoodPosition.CLOSE;
    private double		    shooterSetpoint = 0;

    private TToggle         testModeToggle   = new TToggle();

    private double rpm = 2000;
    public boolean activated = false;

    public enum TestMode {
        NONE,
        SHOOTER,
        TOP_INTAKE, BOTTOM_INTAKE,
        CAROUSEL, TOWER,
        LEFT_CLIMB, RIGHT_CLIMB,
        LEFT_DRIVE, RIGHT_DRIVE
    }

    private TestMode        testMode = TestMode.SHOOTER;
    private int             testModeOrdinal = 0;
    private double          testSpeed       = 0;
    private TButtonPressDetector nextModeDetector  = new TButtonPressDetector(driverController, TTrigger.RIGHT);
    private TButtonPressDetector prevModeDetector  = new TButtonPressDetector(driverController, TTrigger.LEFT);

    private enum OperatorMode {
        SHOOT,
        CLIMB
    }

    private OperatorMode operatorMode = OperatorMode.SHOOT;

    /* *********************************************************************
     * General Controls
     * *********************************************************************/

    public void init() {
        compressorToggle.set(true);
        speedPidToggle.set(false);
        testModeToggle.set(false);
    }

    @Override
    public boolean getCancelCommand() {
        return driverController.getButton(TButton.BACK);
    }

    public boolean getCompressorEnabled() {
        return compressorToggle.get();
    }

    @Override
    public boolean getReset() {
        return driverController.getButton(TButton.START);
    }

    public boolean isTestModeEnabled() {
        return testModeToggle.get();
    }

    public TestMode getTestMode() {
        return testMode;
    }

    public double getTestMotorSpeed() {
        return testSpeed;
    }

    private void updateTestMode() {

        if (!isTestModeEnabled()) {
            testMode = TestMode.NONE;
            testModeOrdinal = 0;
            testSpeed = 0;
            return;
        }
        else {

            if (testMode == TestMode.NONE) {
                testSpeed = 0;
            }

            if (DriverStation.getInstance().isDisabled()) {
                testSpeed = 0;
                testMode = TestMode.NONE;
                return;
            }

            int nextTestModeOrdinal = testModeOrdinal;

            if (nextModeDetector.get()) {
                nextTestModeOrdinal++;
            }
            if (prevModeDetector.get()) {
                nextTestModeOrdinal--;
            }

            // Update the testMode
            if (nextTestModeOrdinal != testModeOrdinal) {
                testModeOrdinal = nextTestModeOrdinal;
                testSpeed = 0;
                if (testModeOrdinal < 0) {
                    testModeOrdinal += TestMode.values().length;
                }
                if (testModeOrdinal >= TestMode.values().length) {
                    testModeOrdinal = 0;
                }
                testMode = TestMode.values()[testModeOrdinal];
            }

            // Update the testMotorSpeed
            if (driverController.getButton(TButton.LEFT_BUMPER)) {
                testSpeed = testSpeed - 0.005;
                if (testSpeed < -1) {
                    testSpeed = -1;
                }
            }

            if(driverController.getButton(TButton.RIGHT_BUMPER)) {
                testSpeed = testSpeed + 0.005;
                if (testSpeed > 1) {
                    testSpeed = 1;
                }
            }
        }
    }

    public void startRumble() {
        driverRumble.rumbleOn();
        operatorRumble.rumbleOn();
    }

    public void endRumble() {
        driverRumble.rumbleOff();
        operatorRumble.rumbleOff();
    }

    private void updateOperatorMode() {

        // Shoot takes precedence over Climb if both are pressed
        if (operatorController.getButton(TButton.BACK)) {
            operatorMode = OperatorMode.SHOOT;
            return;
        }

        if (operatorController.getButton(TButton.START)) {
            operatorMode = OperatorMode.CLIMB;
        }
    }

    /* *********************************************************************
     * Drive Controls
     * *********************************************************************/

    /**
     * Get the selected drive type
     *
     * @return {@link DriveControlType} selected on the SmartDashboard. The default
     *         drive type is {@link DriveControlType#ARCADE}
     */
    public DriveControlType getSelectedDriveType() {
        return driveSelector.getDriveControlType();
    }

    /**
     * Get the selected single stick side
     *
     * @return {@link TStick} selected on the SmartDashboard. The default single
     *         stick drive is {@link TStick#RIGHT}
     */
    public TStick getSelectedSingleStickSide() {
        return driveSelector.getSingleStickSide();
    }

    @Override
    public TStickPosition getDriveStickPosition(TStick stick) {
        return driverController.getStickPosition(stick);
    }

    public TStickPosition getOperatorStickPosition(TStick stick) {
    	if (operatorMode == OperatorMode.CLIMB) {
    		return operatorController.getStickPosition(stick);
    	}
    	return new TStickPosition(0,0);
    }

    @Override
    public int getRotateToHeading() {
        return driverController.getPOV();
        //return -1;
    }

    @Override
    public boolean getSpeedPidEnabled() {
        return speedPidToggle.get();
    }

    public void setSpeedPidEnabled(boolean state) {
        speedPidToggle.set(state);
    }

    public boolean alignTarget () {
    	if (driverController.getButton(TButton.Y)) {
            return true;
    	}
    	if (operatorMode == OperatorMode.SHOOT) {
	        if (operatorController.getButton(TButton.RIGHT_BUMPER) ) {
	            return true;
	        }
    	}
        return false;
    }

    public boolean isDriverDriving() {
        TStickPosition left = getDriveStickPosition(TStick.LEFT);
        TStickPosition right = getDriveStickPosition(TStick.RIGHT);
        if (left.y!=0 || right.x != 0) {
            return true;
        }
        return false;

    }
    /* *********************************************************************
     * Shooter Controls
     * *********************************************************************/

    public HoodPosition getHoodPosition() {
    	if (operatorMode == OperatorMode.SHOOT) {
	        if (operatorController.getPOV() == 270) {
	            previousHoodPosition = HoodPosition.CLOSE;
	        }
	        if(operatorController.getPOV() == 180) {
	            previousHoodPosition = HoodPosition.MEDIUM;
	        }
	        if(operatorController.getPOV() == 90) {
	            previousHoodPosition = HoodPosition.FAR;
	        }
    	}
        return previousHoodPosition;
    }

    public boolean shooterExtend() {
    	if (operatorMode == OperatorMode.SHOOT) {
	        if (operatorController.getPOV() == 270) {
	            return true;
	        }
    	}
        return false;
    }

    public boolean shooterRetract() {
    	if (operatorMode == OperatorMode.SHOOT) {
	        if (operatorController.getPOV() == 90) {
	            return true;
	        }
    	}
        return false;
    }


    public boolean stopShooter() {

        if (!driverController.getButton(TButton.Y)) {
            return true;
        }
        return false;
    }

    public double getShooterSetpoint() {
        return shooterSetpoint;
    }

    /**
     * This command is used to override the shooter speed to a preset value;
     * @param shooterSpeed
     */
    public void setShooterSetpoint(double shooterSetpoint) {
        this.shooterSetpoint = shooterSetpoint;
    }

    public boolean getAutoShoot() {
    	if (operatorMode == OperatorMode.SHOOT) {
    		return operatorController.getPOV() == 0;
    	}
    	return false;
    }

    /**
     * This routine is called by the update periodic to set the
     * shooter speed.
     */
    public void updateShooterSpeed() {

        rpm = SmartDashboard.getNumber("RPM", 0);

        SmartDashboard.putNumber("RPM", rpm);

        if (operatorMode == OperatorMode.SHOOT) {
        	
	        if (operatorController.getButton(TButton.Y)) {
	
	            //        	shooterSetpoint = rpm;
	
	            // Front Trench
	        }
	
	        else if (operatorController.getButton(TButton.X)) {
	            //        	new GyroTurnCommand();
	            shooterSetpoint = RobotConst.INITIATION_LINE_RPM;
	            //initiation line
	            //Limelight
	        }
	
	        else if (operatorController.getButton(TButton.A)) {
	            shooterSetpoint = RobotConst.FRONT_OF_TRENCH_RPM;
	            //front trench
	        }
	
	        else if (operatorController.getButton(TButton.B)) {
	            shooterSetpoint = RobotConst.BACK_OF_TRENCH_RPM;
	            //Back trench
	        }
	        else if(operatorController.getButton(TButton.LEFT_BUMPER)) {
	            shooterSetpoint = RobotConst.RAMP_UP_RPM;
	            //ramp up button
	        }
	        else {
	            shooterSetpoint = 0;
	        }
        }
    }

    /* *********************************************************************
     * Intake Controls
     * *********************************************************************/
    /**
     * Start the feeder station intake
     */
    public boolean runFeederIntake() {

        if (driverController.getButton(TTrigger.LEFT)) {
            return true;
        }
        
        if (operatorMode == OperatorMode.SHOOT) {
	        if (operatorController.getButton(TTrigger.LEFT)) {
	            return true;
	        }
        }
        
        return false;
    }

    public boolean feederExtake() {
        if (driverController.getButton(TButton.X)) {
            return true;
        }
        return false;
    }

    /**
     * Start the ground intake
     */
    public boolean runGroundIntake() {

        if(driverController.getButton(TTrigger.RIGHT)) {
            return true;
        }
        
        if (operatorMode == OperatorMode.SHOOT) {
	        if (operatorController.getButton(TTrigger.RIGHT)) {
	            return true;
	        }
        }
        return false;
    }

    public boolean groundExtake() {
        if (driverController.getButton(TButton.B) ) {
            return true;
        }
        return false;
    }

    public boolean intakeDown(){
        if (driverController.getButton(TButton.RIGHT_BUMPER)) {
            return true;
        }
        return false;
    }

    public boolean intakeUp() {
        if (driverController.getButton(TButton.LEFT_BUMPER)) {
            return true;
        }
        return false;
    }

    /**
     * Stop the intake
     */
    public boolean stopIntake() {

        if(!groundExtake() && !runGroundIntake() && !runFeederIntake() && !feederExtake()) {
            return true;
        }
        return false;

    }

    /* *********************************************************************
     * Carousel Controls
     *
     * NOTE: Carousel is currently tied to the intake buttons
     * *********************************************************************/

    public boolean runIntakeCarousel() {
        if (       driverController.getButton(TTrigger.LEFT)
                || driverController.getButton(TTrigger.RIGHT)) {
        	return true;
        }
        
        if (operatorMode == OperatorMode.SHOOT) {
        	if (   operatorController.getButton(TTrigger.RIGHT)
                || operatorController.getButton(TTrigger.LEFT)) {
            return true;
        	}
        }
        return false;
    }

    public boolean runShooterCarousel() {
        if(     (  driverController.getButton(TButton.Y)
                || getAutoShoot())
                && Robot.shooterPIDSubsystem.isShooterReady()) {
            return true;
        }

        if (operatorMode == OperatorMode.SHOOT) {
	        if(     (  operatorController.getButton(TButton.X)
	                || operatorController.getButton(TButton.A)
	                || operatorController.getButton(TButton.B)
	                || getAutoShoot())
	                && Robot.shooterPIDSubsystem.isShooterReady()) {
	            return true;
	        }
        }
        return false;
    }

    public boolean stopCarousel() {
        if(!runIntakeCarousel() && !runShooterCarousel() && joystickCarousel() == 0.0) {
            return true;
        }
        return false;
    }

    public boolean runReverse() {
    	
    	if (operatorMode == OperatorMode.SHOOT) {
	        if (       operatorController.getButton(TStick.RIGHT) 
	        		|| operatorController.getButton(TStick.LEFT)) {
	            return true;
	        }
    	}
        return false;
    }

    public double joystickCarousel() {
    	if (operatorMode == OperatorMode.SHOOT) {
	        if (Math.abs(operatorController.getAxis(TStick.LEFT, TAxis.Y)) > 0.2) {
	            return operatorController.getAxis(TStick.LEFT, TAxis.Y);
	        }
    	}
        return 0.0;
    }

    /* *********************************************************************
     * Tower Controls
     *
     * NOTE: Tower is currently linked to the intake and shooter buttons
     * *********************************************************************/

    public boolean runIntakeTower() {
        if ((      driverController.getButton(TTrigger.LEFT)
                || driverController.getButton(TTrigger.RIGHT))
                && !Robot.towerSubsystem.isTowerFull()) {
            return true;
        }

        if (operatorMode == OperatorMode.SHOOT) {
	        if ((      operatorController.getButton(TTrigger.LEFT)
	                || operatorController.getButton(TTrigger.RIGHT))
	                && !Robot.towerSubsystem.isTowerFull()) {
	            return true;
	        }
    	}
        return false;
    }

    public boolean runShooterTower() {
        if(     (  driverController.getButton(TButton.Y)
                || getAutoShoot())
                && Robot.shooterPIDSubsystem.isShooterReady()) {
            return true;
        }
        
        if (operatorMode == OperatorMode.SHOOT) {
	        if(     (  operatorController.getButton(TButton.X)
	                || operatorController.getButton(TButton.A)
	                || operatorController.getButton(TButton.B)
	                || getAutoShoot())
	                && Robot.shooterPIDSubsystem.isShooterReady()) {
	            return true;
	        }
        }
        return false;
    }
    
    public boolean runReverseTower() {
        if (operatorMode == OperatorMode.SHOOT) {
	        if (operatorController.getButton(TStick.RIGHT)) {
	            return true;
	        }
        }
        return false;
    }

    public boolean stopTower() {
        if (       !runIntakeTower()
                && !runShooterTower()
                && !runReverseTower()) {
            return true;
        }
        return false;
    }


    /* *********************************************************************
     * Climb Controls
     * *********************************************************************/

    public boolean runLeftClimbUp() {
    	if (operatorMode == OperatorMode.CLIMB) {
	        if (operatorController.getButton(TTrigger.LEFT)) {
	            return true;
	        }
    	}
        return false;
    }

    public boolean runLeftClimbDown() {
    	if (operatorMode == OperatorMode.CLIMB) {
	        if (operatorController.getButton(TButton.LEFT_BUMPER)) {
	            return true;
	        }
    	}
        return false;
    }

    public boolean runRightClimbUp() {
    	if (operatorMode == OperatorMode.CLIMB) {
	        if (operatorController.getButton(TTrigger.RIGHT)) {
	            return true;
	        }
    	}
        return false;
    }

    public boolean runRightClimbDown() {
    	if (operatorMode == OperatorMode.CLIMB) {
	        if (operatorController.getButton(TButton.RIGHT_BUMPER)) {
	            return true;
	        }
    	}
        return false;
    }

    public boolean runBothClimbUp() {
    	if (operatorMode == OperatorMode.CLIMB) {
	        if (operatorController.getButton(TButton.Y)) {
	            return true;
	        }
    	}
        return false;
    }

    public boolean runBothClimbDown() {
    	if (operatorMode == OperatorMode.CLIMB) {
	        if (operatorController.getButton(TButton.A)) {
	            return true;
	        }
    	}
        return false;
    }

    public boolean lockClimb() {
    	if (operatorMode == OperatorMode.CLIMB) {
	        if (operatorController.getButton(TButton.B)) {
	            return true;
	        }
    	}
        return false;
    }



    @Override
    public void updatePeriodic() {

    	// Update the Operator Mode
    	updateOperatorMode();
    	
        // Update all Toggles
        compressorToggle.updatePeriodic();
        speedPidToggle.updatePeriodic();
        driverRumble.updatePeriodic();
        operatorRumble.updatePeriodic();

        // Press both the start and back buttons to toggle in and out of test mode.
        testModeToggle.updatePeriodic(
                driverController.getButton(TButton.START) && driverController.getButton(TButton.BACK));
        updateTestMode();

        // Update the shooter from the buttons
        updateShooterSpeed();

        // Update all SmartDashboard values
        SmartDashboard.putNumber("Joystick", joystickCarousel());
        SmartDashboard.putBoolean("Speed PID Toggle", getSpeedPidEnabled());
        SmartDashboard.putBoolean("Compressor Toggle", getCompressorEnabled());
        SmartDashboard.putString("Driver Controller", driverController.toString());
        SmartDashboard.putBoolean("Test Mode Enabled", isTestModeEnabled());
        SmartDashboard.putString("Test Mode", getTestMode().toString());
        SmartDashboard.putNumber("Test Motor Speed", getTestMotorSpeed());
        SmartDashboard.putString("Operator Mode", operatorMode.toString());
    }

}
