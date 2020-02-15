package frc.robot.oi;

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

    private TToggle         compressorToggle = new TToggle(driverController, TStick.LEFT);
    private TToggle         speedPidToggle   = new TToggle(driverController, TStick.RIGHT);

    private DriveSelector   driveSelector    = new DriveSelector();

    private HoodPosition	previousHoodPosition = HoodPosition.CLOSE;
    private double		    shooterSpeed = 0;

    private TToggle         testModeToggle   = new TToggle();

    public enum TestMode {
        NONE,
        SHOOTER,
        TOP_INTAKE, BOTTOM_INTAKE,
        CAROUSEL, TOWER,
        LEFT_DRIVE, RIGHT_DRIVE
    }

    private TestMode        testMode = TestMode.SHOOTER;
    private int             testModeOrdinal = 0;
    private double          testSpeed       = 0;
    private TButtonPressDetector nextModeDetector  = new TButtonPressDetector(driverController, TTrigger.RIGHT);
    private TButtonPressDetector prevModeDetector  = new TButtonPressDetector(driverController, TTrigger.LEFT);

    /* *********************************************************************
     * General Controls
     * *********************************************************************/

    public void init() {
        compressorToggle.set(false);
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

    @Override
    public int getRotateToHeading() {
        // return driverController.getPOV();
        return -1;
    }

    @Override
    public boolean getSpeedPidEnabled() {
        return speedPidToggle.get();
    }

    public void setSpeedPidEnabled(boolean state) {
        speedPidToggle.set(state);
    }

    /* *********************************************************************
     * Shooter Controls
     * *********************************************************************/

    public HoodPosition getHoodPosition() {

        if (driverController.getPOV() == 180) {
            previousHoodPosition = HoodPosition.CLOSE;
        }
        if(driverController.getPOV() == 90 || driverController.getPOV() == 270) {
            previousHoodPosition = HoodPosition.MEDIUM;
        }
        if(driverController.getPOV() == 0) {
            previousHoodPosition = HoodPosition.FAR;
        }
        return previousHoodPosition;
    }

    public boolean stopShooter() {

        if (driverController.getButton(TTrigger.LEFT)) {
            return true;
        }
        return false;
    }

    public double getShooterSpeed() {
        return shooterSpeed;
    }

    /**
     * This command is used to override the shooter speed to a preset value;
     * @param shooterSpeed
     */
    public void setShooterSpeed(double shooterSpeed) {
        this.shooterSpeed = shooterSpeed;
    }

    /**
     * This routine is called by the update periodic to set the
     * shooter speed.
     */
    public void updateShooterSpeed() {

        if (isTestModeEnabled()) {
            shooterSpeed = 0;
            return;
        }

        if (driverController.getButton(TTrigger.LEFT)) {
            shooterSpeed = 0;
        }

        if (driverController.getButton(TTrigger.RIGHT)) {
            shooterSpeed = 0.5;
        }

        if (driverController.getButton(TButton.LEFT_BUMPER)) {
            shooterSpeed = shooterSpeed - 0.005;
            if (shooterSpeed < 0) {
                shooterSpeed = 0;
            }
        }

        if(driverController.getButton(TButton.RIGHT_BUMPER)) {
            shooterSpeed = shooterSpeed + 0.005;
            if (shooterSpeed > 1) {
                shooterSpeed = 1;
            }
        }
    }

    public boolean runShooterBB() {
        //        if (driverController.getButton(TButton.A)) {
        //            shooterSpeed = RobotConst.SHOOTER_BANGBANG_SPEED;
        //            return true;
        //        }
        return false;
    }

    /* *********************************************************************
     * Intake Controls
     * *********************************************************************/

    /**
     * Start the feeder station intake
     */
    public boolean runFeederIntake() {

        if(driverController.getButton(TButton.Y)) {
            return true;
        }
        return false;
    }

    /**
     * Start the ground intake
     */
    public boolean runGroundIntake() {

        if(driverController.getButton(TButton.A)) {
            return true;
        }
        return false;
    }

    /**
     * Stop the intake
     */
    public boolean stopIntake() {

        if (driverController.getButton(TButton.X)) {
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
        if (       driverController.getButton(TButton.A)
                || driverController.getButton(TButton.Y)) {
            return true;
        }
        return false;
    }

    public boolean runShooterCarousel() {
        if(driverController.getButton(TButton.B)) {
            return true;
        }
        return false;
    }

    public boolean stopCarousel() {
        if(driverController.getButton(TButton.X)) {
            return true;
        }
        return false;
    }

    /* *********************************************************************
     * Tower Controls
     *
     * NOTE: Tower is currently linked to the intake and shooter buttons
     * *********************************************************************/

    public boolean runIntakeTower() {
        if (       driverController.getButton(TButton.A)
                || driverController.getButton(TButton.Y)) {
            return true;
        }
        return false;
    }

    public boolean runShooterTower() {
        if(driverController.getButton(TButton.B)) {
            return true;
        }
        return false;
    }

    public boolean stopTower() {
        if(driverController.getButton(TButton.X)) {
            return true;
        }
        return false;
    }

    @Override
    public void updatePeriodic() {

        // Update all Toggles
        compressorToggle.updatePeriodic();
        speedPidToggle.updatePeriodic();
        driverRumble.updatePeriodic();

        // Press both the start and back buttons to toggle in and out of test mode.
        testModeToggle.updatePeriodic(
                driverController.getButton(TButton.START) && driverController.getButton(TButton.BACK));
        updateTestMode();

        // Update the shooter from the buttons
        updateShooterSpeed();

        // Update all SmartDashboard values
        SmartDashboard.putBoolean("Speed PID Toggle", getSpeedPidEnabled());
        SmartDashboard.putBoolean("Compressor Toggle", getCompressorEnabled());
        SmartDashboard.putString("Driver Controller", driverController.toString());
        SmartDashboard.putBoolean("Test Mode Enabled", isTestModeEnabled());
        SmartDashboard.putString("Test Mode", getTestMode().toString());
        SmartDashboard.putNumber("Test Motor Speed", getTestMotorSpeed());
    }

}
