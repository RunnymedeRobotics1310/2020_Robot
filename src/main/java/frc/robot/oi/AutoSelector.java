package frc.robot.oi;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoSelector {

    public static SendableChooser<String> pattern;

    public static final String            PATTERN_STRAIGHT  = "Straight";
    public static final String            PICK_UP_2_SHOOT_5    = "Pick up 2 balls and shoot 5 after";
    public static final String            NO_DRIVE       = "No drive";
    public static final String            SHOOT_3_PICK_UP_3_SHOOT_3       = "Shoot 3, Pick up 3, shoot 3";
    public static final String            SHOOT_3_PICK_UP_3_TRENCH       = "Shoot 3, Pick up 3 in the trench";
    public static final String            DRIVE_ON_CURVE       = "Test drive on curve command";
    public static final String            PICK_UP_2_SHOOT_5_PICK_UP_3_SHOOT_3       = "8 ball auto: Pick up 2, shoot 5, pick up 3, shoot 3";


    
    static {


        // Robot Pattern Options
        pattern = new SendableChooser<String>();
        pattern.setDefaultOption(PATTERN_STRAIGHT, PATTERN_STRAIGHT);
        pattern.addOption(NO_DRIVE, NO_DRIVE);
        pattern.addOption(PICK_UP_2_SHOOT_5, PICK_UP_2_SHOOT_5);
        pattern.addOption(SHOOT_3_PICK_UP_3_SHOOT_3, SHOOT_3_PICK_UP_3_SHOOT_3);
        pattern.addOption(SHOOT_3_PICK_UP_3_TRENCH, SHOOT_3_PICK_UP_3_TRENCH);
        pattern.addOption(DRIVE_ON_CURVE, DRIVE_ON_CURVE);
        pattern.addOption(PICK_UP_2_SHOOT_5_PICK_UP_3_SHOOT_3, PICK_UP_2_SHOOT_5_PICK_UP_3_SHOOT_3);


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
