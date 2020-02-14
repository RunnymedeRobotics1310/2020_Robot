package frc.robot;

public class RobotConst {

    public static final String  PRACTICE_ROBOT                = "TestRobot";
    public static final String  TEST_ROBOT                    = "TestRobot";
    public static final String  Y2019_ROBOT                   = "2019Robot";
    public static final String  PROD_ROBOT                    = "ProdRobot";

    // *********************************************************
    // Drive Constants
    // *********************************************************
    // Forward for the elevator is counter-clockwise when looking
    // from the back of the robot towards the front
    public static final double  MAX_LOW_GEAR_SPEED;
    public static final double  MAX_HIGH_GEAR_SPEED;

    public static final double  DRIVE_GYRO_PID_KP;
    public static final double  DRIVE_GYRO_PID_KI;
    public static final double  DRIVE_MAX_ROTATION_OUTPUT     = 0.6;

    public static final double  DRIVE_SPEED_PID_KP;
    public static final double  DRIVE_SPEED_PID_KI;

    public static final double  ENCODER_COUNTS_PER_INCH;


    // *********************************************************
    // INTAKE
    // *********************************************************
    public static final double  INTAKE_SPEED				= 0.25;

    // *********************************************************
    // Carousel Constants
    // *********************************************************
    public static final double CAROUSEL_INTAKE_SPEED = 0.15;
    public static final double CAROUSEL_SHOOTER_SPEED = 0.3;

    // *********************************************************
    // For Ultrasonic Calibration
    // *********************************************************
    public static final double  ULTRASONIC_VOLTAGE_20IN       = 0.191;
    public static final double  ULTRASONIC_VOLTAGE_40IN       = 0.383;
    public static final double  ULTRASONIC_VOLTAGE_80IN       = 0.764;

    // *********************************************************
    // Tower Constants
    // *********************************************************
    public static final double TOWER_INTAKE_SPEED  = 0.15;
    public static final double TOWER_SHOOTER_SPEED = 0.3;

    public static enum Direction {
        FORWARD, BACKWARD
    };
    // *********************************************************
    // Shooter Constants
    // *********************************************************
    public static final double SHOOTER_BANGBANG_SPEED  = 1;

    // The TorontoCodingCollective framework was developed to run on different
    // robots through the use of multiple mappings and constants.
    public static final String robot = Y2019_ROBOT;

    static {

        switch (robot) {

        case Y2019_ROBOT:

            // The low gear speed should be set just below the
            // maximum loaded speed of the robot
            MAX_LOW_GEAR_SPEED = 320.0; // Encoder counts/sec
            MAX_HIGH_GEAR_SPEED = 900.0;

            // Typically set the integral gain at 1/20 of the
            // proportional gain.  The gain can often be increased
            // above this value, but typically gives good
            // stability and acceptable performance
            DRIVE_GYRO_PID_KP = .07;
            DRIVE_GYRO_PID_KI = DRIVE_GYRO_PID_KP / 20.0;

            DRIVE_SPEED_PID_KP = 0.4;
            DRIVE_SPEED_PID_KI = DRIVE_SPEED_PID_KP / 20.0;

            ENCODER_COUNTS_PER_INCH = 55.6/2;

            break;

        case TEST_ROBOT:
        default:

            // The low gear speed should be set just below the
            // maximum loaded speed of the robot
            MAX_LOW_GEAR_SPEED = 320.0; // Encoder counts/sec
            MAX_HIGH_GEAR_SPEED = 900.0;

            // Typically set the integral gain at 1/20 of the
            // proportional gain.  The gain can often be increased
            // above this value, but typically gives good
            // stability and acceptable performance
            DRIVE_GYRO_PID_KP = .07;
            DRIVE_GYRO_PID_KI = DRIVE_GYRO_PID_KP / 20.0;

            DRIVE_SPEED_PID_KP = 0.4;
            DRIVE_SPEED_PID_KI = DRIVE_SPEED_PID_KP / 20.0;

            ENCODER_COUNTS_PER_INCH = 55.6/2;

            break;
        }

    }
}
