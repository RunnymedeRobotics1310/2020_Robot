package frc.robot.oi;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoSelector {

    public static SendableChooser<String> pattern;

    public static final String            PATTERN_STRAIGHT  = "Straight";
    public static final String            PICK_UP_2_SHOOT_5    = "Pick up 2 balls and shoot 5 after";
    public static final String            NO_DRIVE       = "No drive";

    static {


        // Robot Pattern Options
        pattern = new SendableChooser<String>();
        pattern.setDefaultOption(PATTERN_STRAIGHT, PATTERN_STRAIGHT);
        pattern.addOption(NO_DRIVE, NO_DRIVE);
        pattern.addOption(PICK_UP_2_SHOOT_5, PICK_UP_2_SHOOT_5);

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

        

    public static void init() {}
}
