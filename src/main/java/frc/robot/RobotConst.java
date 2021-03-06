package frc.robot;

public class RobotConst {

    public static final String  PRACTICE_ROBOT                = "PracticeRobot";
    public static final String  TEST_ROBOT                    = "TestRobot";
    public static final String  Y2019_ROBOT                   = "2019Robot";
    public static final String  PROD_ROBOT                    = "ProdRobot";

    // *********************************************************
    // Drive Constants
    // *********************************************************

    public static final double  DRIVE_GYRO_PID_KP;
    public static final double  DRIVE_GYRO_PID_KI;
    public static final double  DRIVE_GYRO_PID_KD;

    public static final double  VISION_TARGET_PID_KP;
    public static final double  VISION_TARGET_PID_KI;
    public static final double  VISION_TARGET_PID_KD;

    public static final double  DRIVE_MAX_ROTATION_OUTPUT;

    public static final double  DRIVE_SPEED_PID_KP;
    public static final double  DRIVE_SPEED_PID_KI;
    public static final double  MAX_DRIVE_ENCODER_SPEED;

    public static final double  ENCODER_COUNTS_PER_INCH;

    public static final double  ROBOT_WIDTH;

    public static final double  OPERATOR_MAX_DRIVE_SPEED = 0.3;

    // *********************************************************
    // INTAKE
    // *********************************************************
    public static final double  INTAKE_SPEED				= 1;

    // *********************************************************
    // Carousel Constants
    // *********************************************************
    public static final double CAROUSEL_INTAKE_SPEED;
    public static final double CAROUSEL_SHOOTER_SPEED;
    public static final double CAROUSEL_UNJAM_SPEED;

    // *********************************************************
    // For Ultrasonic Calibration
    // *********************************************************
    public static final double  ULTRASONIC_VOLTAGE_20IN       = 0.191;
    public static final double  ULTRASONIC_VOLTAGE_40IN       = 0.383;
    public static final double  ULTRASONIC_VOLTAGE_80IN       = 0.764;

    // *********************************************************
    // Tower Constants
    // *********************************************************
    public static final double TOWER_INTAKE_SPEED  = .9;
    public static final double TOWER_SHOOTER_SPEED = 1;

    public static final double KICKER_MOTOR_SPEED = 0.4;

    // *********************************************************
    // Shooter Constants
    // *********************************************************
    public static final double SHOOTER_KP;
    public static final double SHOOTER_KI;
    public static final double SHOOTER_KD;
    public static final double SHOOTER_FF;
    
    public static final double SHOOTER_SPEED_PID_KP;
    public static final double SHOOTER_SPEED_PID_KI;
    public static final double MAX_SHOOTER_SPEED;

    public static final double INITIATION_LINE_RPM;
    public static final double FRONT_OF_TRENCH_RPM;
    public static final double BACK_OF_TRENCH_RPM;
    public static final double RAMP_UP_RPM;

    // *********************************************************
    // Climb Constants
    // *********************************************************
    public static final double CLIMB_SPEED_UP       = 0.25;
    public static final double CLIMB_SPEED_DOWN 	= -0.25;
    public static final int CLIMB_EXTENDED_ENCODER_COUNTS = 100000;

    // The TorontoCodingCollective framework was developed to run on different
    // robots through the use of multiple mappings and constants.
    public static String robot = PROD_ROBOT;

    static {

        switch (robot) {

        case PROD_ROBOT:

            // The max drive encoder speed should be set just below the
            // maximum loaded speed of the robot
            MAX_DRIVE_ENCODER_SPEED = 5700.0; // Encoder counts/sec
            DRIVE_MAX_ROTATION_OUTPUT = 0.4;

            ROBOT_WIDTH = 30;

            DRIVE_SPEED_PID_KP = 0.4;
            DRIVE_SPEED_PID_KI = DRIVE_SPEED_PID_KP / 20.0;
            DRIVE_GYRO_PID_KP = 0.0095;
            DRIVE_GYRO_PID_KI = 0.001;
            DRIVE_GYRO_PID_KD = 0.000;

            VISION_TARGET_PID_KP = 0.006;
            VISION_TARGET_PID_KI = 0.02;
            VISION_TARGET_PID_KD = 0.000;

            INITIATION_LINE_RPM = 2500;
            FRONT_OF_TRENCH_RPM = 2790;
            BACK_OF_TRENCH_RPM = 3800;
            RAMP_UP_RPM = 2500;
            
            SHOOTER_KP = .0003;
            SHOOTER_KI = .0000000001;
            SHOOTER_KD = .000;
            SHOOTER_FF = 0.000185;

            SHOOTER_SPEED_PID_KP = 0.5;
            SHOOTER_SPEED_PID_KI = 0.05;

            MAX_SHOOTER_SPEED = 5600;

            CAROUSEL_INTAKE_SPEED = 0.25;
            CAROUSEL_SHOOTER_SPEED = 0.25;
            CAROUSEL_UNJAM_SPEED = -.4;

            ENCODER_COUNTS_PER_INCH = 50.4;

            break;

        case PRACTICE_ROBOT:

            // The max drive encoder speed should be set just below the
            // maximum loaded speed of the robot
            MAX_DRIVE_ENCODER_SPEED = 5700.0; // Encoder counts/sec
            DRIVE_MAX_ROTATION_OUTPUT = 0.4;

            ROBOT_WIDTH = 30;

            DRIVE_SPEED_PID_KP = 0.4;
            DRIVE_SPEED_PID_KI = DRIVE_SPEED_PID_KP / 20.0;
            DRIVE_GYRO_PID_KP = 0.0095;
            DRIVE_GYRO_PID_KI = 0.001;
            DRIVE_GYRO_PID_KD = 0.000;

            VISION_TARGET_PID_KP = 0.006;
            VISION_TARGET_PID_KI = 0.02;
            VISION_TARGET_PID_KD = 0.000;

            INITIATION_LINE_RPM = 2500;
            FRONT_OF_TRENCH_RPM = 2790;
            BACK_OF_TRENCH_RPM = 3800;
            RAMP_UP_RPM = 2500;
            
            SHOOTER_KP = .0003;
            SHOOTER_KI = .0000000001;
            SHOOTER_KD = .000;
            SHOOTER_FF = 0.000185;

            SHOOTER_SPEED_PID_KP = 0.5;
            SHOOTER_SPEED_PID_KI = 0.05;

            MAX_SHOOTER_SPEED = 5600;

            CAROUSEL_INTAKE_SPEED = 0.25;
            CAROUSEL_SHOOTER_SPEED = 0.25;
            CAROUSEL_UNJAM_SPEED = -.4;

            ENCODER_COUNTS_PER_INCH = 50.4;

            break;

        case Y2019_ROBOT:

            // The max drive encoder speed should be set just below the
            // maximum loaded speed of the robot
            MAX_DRIVE_ENCODER_SPEED = 320.0; // Encoder counts/sec
            DRIVE_MAX_ROTATION_OUTPUT = 0.4;

            ROBOT_WIDTH = 30;

            // Typically set the integral gain at 1/20 of the
            // proportional gain.  The gain can often be increased
            // above this value, but typically gives good
            // stability and acceptable performance
            DRIVE_GYRO_PID_KP = .07;
            DRIVE_GYRO_PID_KI = DRIVE_GYRO_PID_KP / 20.0;
            DRIVE_GYRO_PID_KD = .07;

            DRIVE_SPEED_PID_KP = 0.4;
            DRIVE_SPEED_PID_KI = DRIVE_SPEED_PID_KP / 20.0;

            VISION_TARGET_PID_KP = 0.006;
            VISION_TARGET_PID_KI = 0.02;
            VISION_TARGET_PID_KD = 0.000;

            INITIATION_LINE_RPM = 2500;
            FRONT_OF_TRENCH_RPM = 2790;
            BACK_OF_TRENCH_RPM = 3800;
            RAMP_UP_RPM = 2500;
            
            SHOOTER_KP = .0003;
            SHOOTER_KI = .0000000001;
            SHOOTER_KD = .000;
            SHOOTER_FF = 0.000185;

            SHOOTER_SPEED_PID_KP = 0.5;
            SHOOTER_SPEED_PID_KI = 0.05;

            MAX_SHOOTER_SPEED = 5600;

            CAROUSEL_INTAKE_SPEED = 0.25;
            CAROUSEL_SHOOTER_SPEED = 0.25;
            CAROUSEL_UNJAM_SPEED = -.4;

            ENCODER_COUNTS_PER_INCH = 55.6/2;

            break;

        case TEST_ROBOT:
        default:

            // The max drive encoder speed should be set just below the
            // maximum loaded speed of the robot
            MAX_DRIVE_ENCODER_SPEED = 320.0; // Encoder counts/sec
            DRIVE_MAX_ROTATION_OUTPUT = 0.4;

            ROBOT_WIDTH = 30;

            // Typically set the integral gain at 1/20 of the
            // proportional gain.  The gain can often be increased
            // above this value, but typically gives good
            // stability and acceptable performance
            DRIVE_GYRO_PID_KP = .07;
            DRIVE_GYRO_PID_KI = DRIVE_GYRO_PID_KP / 20.0;
            DRIVE_GYRO_PID_KD = .07;

            DRIVE_SPEED_PID_KP = 0.4;
            DRIVE_SPEED_PID_KI = DRIVE_SPEED_PID_KP / 20.0;

            VISION_TARGET_PID_KP = 0.006;
            VISION_TARGET_PID_KI = 0.02;
            VISION_TARGET_PID_KD = 0.000;

            INITIATION_LINE_RPM = 2500;
            FRONT_OF_TRENCH_RPM = 2790;
            BACK_OF_TRENCH_RPM = 3800;
            RAMP_UP_RPM = 2500;
            
            SHOOTER_KP = .0003;
            SHOOTER_KI = .0000000001;
            SHOOTER_KD = .000;
            SHOOTER_FF = 0.000185;

            SHOOTER_SPEED_PID_KP = 0;
            SHOOTER_SPEED_PID_KI = 0;

            MAX_SHOOTER_SPEED = 5800;

            CAROUSEL_INTAKE_SPEED = 0.25;
            CAROUSEL_SHOOTER_SPEED = 0.25;
            CAROUSEL_UNJAM_SPEED = -.4;

            ENCODER_COUNTS_PER_INCH = 55.6/2;

            break;
        }

    }
}
