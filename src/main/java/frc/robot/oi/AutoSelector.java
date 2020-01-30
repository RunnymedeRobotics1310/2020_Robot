package frc.robot.oi;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoSelector {

    public static SendableChooser<String> robotStartPosition;

    public static final String            ROBOT_LEFT   = "Robot Left";
    public static final String            ROBOT_CENTER = "Robot Center";
    public static final String            ROBOT_RIGHT  = "Robot Right";

    public static SendableChooser<String> pattern;

    public static final String            PATTERN_STRAIGHT  = "Straight";
    public static final String            PATTERN_STR_NP    = "Straight No PID";
    public static final String            NO_DRIVE       = "No drive";

    static {


        SmartDashboard.putData("Robot Start", robotStartPosition);

        // Robot Pattern Options
        pattern = new SendableChooser<String>();
        pattern.setDefaultOption(PATTERN_STRAIGHT, PATTERN_STRAIGHT);
        pattern.addOption(NO_DRIVE, NO_DRIVE);
        pattern.addOption(PATTERN_STR_NP, PATTERN_STR_NP);

        SmartDashboard.putData("Auto Pattern", pattern);
    }

    /**
     * Get the auto pattern.
     *
     * @return "Straight" or "Box"
     */
    public static String getPattern() {

        String selectedPattern = pattern.getSelected();

        if (selectedPattern == null) {
            return PATTERN_STRAIGHT;
        }

        return selectedPattern;
    }

    /**
     * Get the robot starting position on the field.
     *
     * @return 'L' for left, 'R' for right or 'C' for center
     */
    public static String getRobotStartPosition() {

        String selectedStartPosition = robotStartPosition.getSelected();

        if (selectedStartPosition == null) {
            return ROBOT_CENTER;
        }

        return selectedStartPosition;
    }

    public static void init() {}
}
