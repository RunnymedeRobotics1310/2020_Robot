package frc.robot;

import com.torontocodingcollective.TConst;
import com.torontocodingcollective.speedcontroller.TCanSpeedController.TCanSpeedControllerType;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * <p>
 * This map is intended to define the wiring only. Robot constants should be put
 * in {@link RobotConst}
 */
public class RobotMap {

    // ******************************************
    // Speed Controllers and encoders
    // CAN addresses
    // ******************************************
    public static final int                     LEFT_DRIVE_SPEED_CONTROLLER_CAN_ADDRESS;
    public static final TCanSpeedControllerType LEFT_DRIVE_SPEED_CONTROLLER_TYPE;
    public static final int                     LEFT_DRIVE_FOLLOWER_SPEED_CONTROLLER_CAN_ADDRESS;
    public static final TCanSpeedControllerType LEFT_DRIVE_FOLLOWER_SPEED_CONTROLLER_TYPE;
    public static final boolean                 LEFT_DRIVE_MOTOR_ISINVERTED;
    public static final boolean                 LEFT_DRIVE_ENCODER_ISINVERTED;

    public static final int                     RIGHT_DRIVE_SPEED_CONTROLLER_ADDRESS;
    public static final TCanSpeedControllerType RIGHT_DRIVE_SPEED_CONTROLLER_TYPE;
    public static final int                     RIGHT_DRIVE_FOLLOWER_SPEED_CONTROLLER_ADDRESS;
    public static final TCanSpeedControllerType RIGHT_DRIVE_FOLLOWER_SPEED_CONTROLLER_TYPE;
    public static final boolean                 RIGHT_DRIVE_MOTOR_ISINVERTED;
    public static final boolean                 RIGHT_DRIVE_ENCODER_ISINVERTED;

    public static final int						INTAKE_TOP_MOTOR_CAN_SPEED_CONTROLLER_CAN_ADDRESS;
    public static final TCanSpeedControllerType	INTAKE_TOP_MOTOR_SPEED_CONTROLLER_TYPE;
    public static final boolean                 INTAKE_TOP_MOTOR_ISINVERTED;

    public static final int						INTAKE_BOTTOM_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS;
    public static final TCanSpeedControllerType	INTAKE_BOTTOM_MOTOR_SPEED_CONTROLLER_TYPE;
    public static final boolean                 INTAKE_BOTTOM_MOTOR_ISINVERTED;

    public static final int                     CAROUSEL_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS;
    public static final TCanSpeedControllerType CAROUSEL_MOTOR_SPEED_CONTROLLER_TYPE;
    public static final boolean                 CAROUSEL_MOTOR_ISINVERTED;
    public static final boolean                 CAROUSEL_ENCODER_ISINVERTED;
    public static final int                     CAROUSEL_MOTOR_POWER_PORT;

    public static final int						TOWER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS;
    public static final TCanSpeedControllerType	TOWER_MOTOR_SPEED_CONTROLLER_TYPE;
    public static final boolean                 TOWER_MOTOR_ISINVERTED;

    public static final int						KICKER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS;
    public static final TCanSpeedControllerType	KICKER_MOTOR_SPEED_CONTROLLER_TYPE;
    public static final boolean                 KICKER_MOTOR_ISINVERTED;

    public static final int                     SHOOTER_SPEED_CONTROLLER_CAN_ADDRESS;
    public static final TCanSpeedControllerType SHOOTER_SPEED_CONTROLLER_TYPE;
    public static final int                     SHOOTER_SPEED_FOLLOWER_CAN_ADDRESS;
    public static final TCanSpeedControllerType SHOOTER_SPEED_FOLLOWER_TYPE;
    public static final boolean                 SHOOTER_MOTOR_ISINVERTED;
    public static final boolean                 SHOOTER_ENCODER_ISINVERTED;

    public static final int                     LEFT_CLIMB_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS;
    public static final TCanSpeedControllerType LEFT_CLIMB_MOTOR_SPEED_CONTROLLER_TYPE;
    public static final boolean                 LEFT_CLIMB_MOTOR_ISINVERTED;
    public static final boolean                 LEFT_CLIMB_ENCODER_ISINVERTED;
    public static final int                     RIGHT_CLIMB_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS;
    public static final TCanSpeedControllerType RIGHT_CLIMB_MOTOR_SPEED_CONTROLLER_TYPE;
    public static final boolean                 RIGHT_CLIMB_MOTOR_ISINVERTED;
    public static final boolean                 RIGHT_CLIMB_ENCODER_ISINVERTED;


    // ******************************************
    // ******************************************
    // Gyro Ports
    // ******************************************
    public static final int                     GYRO_ANALOG_PORT;
    public static final boolean                 GYRO_ISINVERTED;

    // ******************************************
    // Pneumatics Ports
    // ******************************************

    public static final int						RIGHT_CLIMB_LOCK_PNEUMATIC_PORT;
    public static final int						LEFT_CLIMB_LOCK_PNEUMATIC_PORT;

    public static final int                     INTAKE_EXTEND_PNEUMATIC_PORT;
    public static final int                     INTAKE_RETRACT_PNEUMATIC_PORT;

    public static final int						STOPPER_RETRACT_PNEUMATIC_PORT;
    public static final int						STOPPER_EXTEND_PNEUMATIC_PORT;


    public static final int						HOOD_RETRACT_PNEUMATIC_PORT;
    public static final int						HOOD_EXTEND_PNEUMATIC_PORT;


    // ******************************************
    // DIO Ports
    // ******************************************
    public static final int                      TOWER_BALL_DETECT_DIO_PORT;
    public static final int                      CAROUSEL_BALL_DETECT_DIO_PORT;

    //FIND REAL ADDRESSES
    public static final int 					 LEFT_CLIMB_BOTTOM_DETECT_DIO_PORT;
    public static final int 					 RIGHT_CLIMB_BOTTOM_DETECT_DIO_PORT;


    // Initializers if this code will be deployed to more than one
    // robot with different mappings
    static {

        switch (RobotConst.robot) {

        case RobotConst.PROD_ROBOT:

            LEFT_DRIVE_SPEED_CONTROLLER_CAN_ADDRESS           = 20;
            LEFT_DRIVE_SPEED_CONTROLLER_TYPE                  = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            LEFT_DRIVE_FOLLOWER_SPEED_CONTROLLER_CAN_ADDRESS  = 21;
            LEFT_DRIVE_FOLLOWER_SPEED_CONTROLLER_TYPE         = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            LEFT_DRIVE_MOTOR_ISINVERTED                       = TConst.NOT_INVERTED;
            LEFT_DRIVE_ENCODER_ISINVERTED                     = TConst.NOT_INVERTED;

            RIGHT_DRIVE_SPEED_CONTROLLER_ADDRESS              = 10;
            RIGHT_DRIVE_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            RIGHT_DRIVE_FOLLOWER_SPEED_CONTROLLER_ADDRESS     = 11;
            RIGHT_DRIVE_FOLLOWER_SPEED_CONTROLLER_TYPE        = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            RIGHT_DRIVE_MOTOR_ISINVERTED                      = TConst.INVERTED;
            RIGHT_DRIVE_ENCODER_ISINVERTED                    = TConst.INVERTED;

            INTAKE_TOP_MOTOR_CAN_SPEED_CONTROLLER_CAN_ADDRESS = 30;
            INTAKE_TOP_MOTOR_SPEED_CONTROLLER_TYPE            = TCanSpeedControllerType.VICTOR_SPX;
            INTAKE_TOP_MOTOR_ISINVERTED                       = TConst.INVERTED;

            INTAKE_BOTTOM_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS  = 31;
            INTAKE_BOTTOM_MOTOR_SPEED_CONTROLLER_TYPE         = TCanSpeedControllerType.VICTOR_SPX;
            INTAKE_BOTTOM_MOTOR_ISINVERTED                    = TConst.INVERTED;

            CAROUSEL_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS       = 39;
            CAROUSEL_MOTOR_SPEED_CONTROLLER_TYPE              = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            CAROUSEL_MOTOR_ISINVERTED                         = TConst.NOT_INVERTED;
            CAROUSEL_ENCODER_ISINVERTED                       = TConst.NOT_INVERTED;
            CAROUSEL_MOTOR_POWER_PORT                         = 11;

            TOWER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS          = 45;
            TOWER_MOTOR_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.VICTOR_SPX;
            TOWER_MOTOR_ISINVERTED                            = TConst.NOT_INVERTED;

            KICKER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS          = 46;
            KICKER_MOTOR_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.VICTOR_SPX;
            KICKER_MOTOR_ISINVERTED                            = TConst.INVERTED;

            SHOOTER_SPEED_CONTROLLER_CAN_ADDRESS              = 50;
            SHOOTER_SPEED_CONTROLLER_TYPE                     = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            SHOOTER_SPEED_FOLLOWER_CAN_ADDRESS                = 51;
            SHOOTER_SPEED_FOLLOWER_TYPE                       = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            SHOOTER_MOTOR_ISINVERTED                          = TConst.NOT_INVERTED;
            SHOOTER_ENCODER_ISINVERTED                        = TConst.NOT_INVERTED;

            GYRO_ANALOG_PORT                                  = 0; // Not used
            GYRO_ISINVERTED                                   = TConst.NOT_INVERTED;

            LEFT_CLIMB_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS     = 60;
            LEFT_CLIMB_MOTOR_SPEED_CONTROLLER_TYPE 		      = TCanSpeedControllerType.TALON_SRX;
            LEFT_CLIMB_MOTOR_ISINVERTED                       = TConst.NOT_INVERTED;
            LEFT_CLIMB_ENCODER_ISINVERTED                     = TConst.NOT_INVERTED;

            RIGHT_CLIMB_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS    = 61;
            RIGHT_CLIMB_MOTOR_SPEED_CONTROLLER_TYPE 	      = TCanSpeedControllerType.TALON_SRX;
            RIGHT_CLIMB_MOTOR_ISINVERTED                      = TConst.INVERTED;
            RIGHT_CLIMB_ENCODER_ISINVERTED                    = TConst.INVERTED;

            // ******************************************
            // Pneumatics Ports
            // ******************************************
            RIGHT_CLIMB_LOCK_PNEUMATIC_PORT = 7;
            LEFT_CLIMB_LOCK_PNEUMATIC_PORT = 6;

            INTAKE_EXTEND_PNEUMATIC_PORT = 4;
            INTAKE_RETRACT_PNEUMATIC_PORT = 5;

            STOPPER_RETRACT_PNEUMATIC_PORT = 2;
            STOPPER_EXTEND_PNEUMATIC_PORT = 3;

            HOOD_RETRACT_PNEUMATIC_PORT = 0;
            HOOD_EXTEND_PNEUMATIC_PORT = 1;

            // ******************************************
            // DIO Ports
            // ******************************************
            TOWER_BALL_DETECT_DIO_PORT = 0;
            CAROUSEL_BALL_DETECT_DIO_PORT = 1;

            LEFT_CLIMB_BOTTOM_DETECT_DIO_PORT = 3;
            RIGHT_CLIMB_BOTTOM_DETECT_DIO_PORT = 5;

            break;

        case RobotConst.PRACTICE_ROBOT:

            LEFT_DRIVE_SPEED_CONTROLLER_CAN_ADDRESS           = 20;
            LEFT_DRIVE_SPEED_CONTROLLER_TYPE                  = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            LEFT_DRIVE_FOLLOWER_SPEED_CONTROLLER_CAN_ADDRESS  = 21;
            LEFT_DRIVE_FOLLOWER_SPEED_CONTROLLER_TYPE         = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            LEFT_DRIVE_MOTOR_ISINVERTED                       = TConst.NOT_INVERTED;
            LEFT_DRIVE_ENCODER_ISINVERTED                     = TConst.NOT_INVERTED;

            RIGHT_DRIVE_SPEED_CONTROLLER_ADDRESS              = 10;
            RIGHT_DRIVE_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            RIGHT_DRIVE_FOLLOWER_SPEED_CONTROLLER_ADDRESS     = 11;
            RIGHT_DRIVE_FOLLOWER_SPEED_CONTROLLER_TYPE        = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            RIGHT_DRIVE_MOTOR_ISINVERTED                      = TConst.INVERTED;
            RIGHT_DRIVE_ENCODER_ISINVERTED                    = TConst.INVERTED;

            INTAKE_TOP_MOTOR_CAN_SPEED_CONTROLLER_CAN_ADDRESS = 30;
            INTAKE_TOP_MOTOR_SPEED_CONTROLLER_TYPE            = TCanSpeedControllerType.VICTOR_SPX;
            INTAKE_TOP_MOTOR_ISINVERTED                       = TConst.INVERTED;

            INTAKE_BOTTOM_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS  = 31;
            INTAKE_BOTTOM_MOTOR_SPEED_CONTROLLER_TYPE         = TCanSpeedControllerType.VICTOR_SPX;
            INTAKE_BOTTOM_MOTOR_ISINVERTED                    = TConst.INVERTED;

            CAROUSEL_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS       = 39;
            CAROUSEL_MOTOR_SPEED_CONTROLLER_TYPE              = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            CAROUSEL_MOTOR_ISINVERTED                         = TConst.NOT_INVERTED;
            CAROUSEL_ENCODER_ISINVERTED                       = TConst.NOT_INVERTED;
            CAROUSEL_MOTOR_POWER_PORT                         = 11;

            TOWER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS          = 45;
            TOWER_MOTOR_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.VICTOR_SPX;
            TOWER_MOTOR_ISINVERTED                            = TConst.NOT_INVERTED;

            KICKER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS          = 46;
            KICKER_MOTOR_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.VICTOR_SPX;
            KICKER_MOTOR_ISINVERTED                            = TConst.INVERTED;

            SHOOTER_SPEED_CONTROLLER_CAN_ADDRESS              = 50;
            SHOOTER_SPEED_CONTROLLER_TYPE                     = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            SHOOTER_SPEED_FOLLOWER_CAN_ADDRESS                = 51;
            SHOOTER_SPEED_FOLLOWER_TYPE                       = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            SHOOTER_MOTOR_ISINVERTED                          = TConst.NOT_INVERTED;
            SHOOTER_ENCODER_ISINVERTED                        = TConst.NOT_INVERTED;

            GYRO_ANALOG_PORT                                  = 0; // Not used
            GYRO_ISINVERTED                                   = TConst.NOT_INVERTED;

            LEFT_CLIMB_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS     = 60;
            LEFT_CLIMB_MOTOR_SPEED_CONTROLLER_TYPE            = TCanSpeedControllerType.TALON_SRX;
            LEFT_CLIMB_MOTOR_ISINVERTED                       = TConst.NOT_INVERTED;
            LEFT_CLIMB_ENCODER_ISINVERTED                     = TConst.NOT_INVERTED;

            RIGHT_CLIMB_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS    = 61;
            RIGHT_CLIMB_MOTOR_SPEED_CONTROLLER_TYPE           = TCanSpeedControllerType.TALON_SRX;
            RIGHT_CLIMB_MOTOR_ISINVERTED                      = TConst.INVERTED;
            RIGHT_CLIMB_ENCODER_ISINVERTED                    = TConst.INVERTED;

            // ******************************************
            // Pneumatics Ports
            // ******************************************
            RIGHT_CLIMB_LOCK_PNEUMATIC_PORT = 7;
            LEFT_CLIMB_LOCK_PNEUMATIC_PORT = 6;

            INTAKE_EXTEND_PNEUMATIC_PORT = 4;
            INTAKE_RETRACT_PNEUMATIC_PORT = 5;

            STOPPER_RETRACT_PNEUMATIC_PORT = 2;
            STOPPER_EXTEND_PNEUMATIC_PORT = 3;

            HOOD_RETRACT_PNEUMATIC_PORT = 0;
            HOOD_EXTEND_PNEUMATIC_PORT = 1;

            // ******************************************
            // DIO Ports
            // ******************************************
            TOWER_BALL_DETECT_DIO_PORT = 0;
            CAROUSEL_BALL_DETECT_DIO_PORT = 1;

            LEFT_CLIMB_BOTTOM_DETECT_DIO_PORT = 3;
            RIGHT_CLIMB_BOTTOM_DETECT_DIO_PORT = 5;

            break;

        case RobotConst.Y2019_ROBOT:

            LEFT_DRIVE_SPEED_CONTROLLER_CAN_ADDRESS           = 110;
            LEFT_DRIVE_SPEED_CONTROLLER_TYPE                  = TCanSpeedControllerType.TALON_SRX;
            LEFT_DRIVE_FOLLOWER_SPEED_CONTROLLER_CAN_ADDRESS  = 111;
            LEFT_DRIVE_FOLLOWER_SPEED_CONTROLLER_TYPE         = TCanSpeedControllerType.VICTOR_SPX;
            LEFT_DRIVE_MOTOR_ISINVERTED                       = TConst.INVERTED;
            LEFT_DRIVE_ENCODER_ISINVERTED                     = TConst.INVERTED;

            RIGHT_DRIVE_SPEED_CONTROLLER_ADDRESS              = 120;
            RIGHT_DRIVE_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.TALON_SRX;
            RIGHT_DRIVE_FOLLOWER_SPEED_CONTROLLER_ADDRESS     = 121;
            RIGHT_DRIVE_FOLLOWER_SPEED_CONTROLLER_TYPE        = TCanSpeedControllerType.VICTOR_SPX;
            RIGHT_DRIVE_MOTOR_ISINVERTED                      = TConst.NOT_INVERTED;
            RIGHT_DRIVE_ENCODER_ISINVERTED                    = TConst.NOT_INVERTED;

            INTAKE_TOP_MOTOR_CAN_SPEED_CONTROLLER_CAN_ADDRESS = 15;
            INTAKE_TOP_MOTOR_SPEED_CONTROLLER_TYPE            = TCanSpeedControllerType.VICTOR_SPX;
            INTAKE_TOP_MOTOR_ISINVERTED                       = TConst.NOT_INVERTED;

            INTAKE_BOTTOM_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS  = 16;
            INTAKE_BOTTOM_MOTOR_SPEED_CONTROLLER_TYPE         = TCanSpeedControllerType.VICTOR_SPX;
            INTAKE_BOTTOM_MOTOR_ISINVERTED                    = TConst.NOT_INVERTED;

            CAROUSEL_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS       = 25;
            CAROUSEL_MOTOR_SPEED_CONTROLLER_TYPE              = TCanSpeedControllerType.VICTOR_SPX;
            CAROUSEL_MOTOR_ISINVERTED                         = TConst.NOT_INVERTED;
            CAROUSEL_ENCODER_ISINVERTED                       = TConst.NOT_INVERTED;
            CAROUSEL_MOTOR_POWER_PORT                         = 10;

            TOWER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS          = 26;
            TOWER_MOTOR_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.VICTOR_SPX;
            TOWER_MOTOR_ISINVERTED                            = TConst.NOT_INVERTED;

            KICKER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS          = 36;
            KICKER_MOTOR_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.VICTOR_SPX;
            KICKER_MOTOR_ISINVERTED                            = TConst.INVERTED;

            SHOOTER_SPEED_CONTROLLER_CAN_ADDRESS              = 10;
            SHOOTER_SPEED_CONTROLLER_TYPE                     = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            SHOOTER_SPEED_FOLLOWER_CAN_ADDRESS                = 11;
            SHOOTER_SPEED_FOLLOWER_TYPE                       = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            SHOOTER_MOTOR_ISINVERTED                          = TConst.NOT_INVERTED;
            SHOOTER_ENCODER_ISINVERTED                        = TConst.NOT_INVERTED;

            GYRO_ANALOG_PORT                                  = 0; // Not used
            GYRO_ISINVERTED                                   = TConst.NOT_INVERTED;

            //FIND THE ACTUAL ADDRESS
            LEFT_CLIMB_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS     = 1;
            LEFT_CLIMB_MOTOR_SPEED_CONTROLLER_TYPE 		      = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            LEFT_CLIMB_MOTOR_ISINVERTED                       = TConst.NOT_INVERTED;
            LEFT_CLIMB_ENCODER_ISINVERTED                     = TConst.NOT_INVERTED;

            RIGHT_CLIMB_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS    = 2;
            RIGHT_CLIMB_MOTOR_SPEED_CONTROLLER_TYPE 	      = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            RIGHT_CLIMB_MOTOR_ISINVERTED                      = TConst.NOT_INVERTED;
            RIGHT_CLIMB_ENCODER_ISINVERTED                    = TConst.NOT_INVERTED;

            // ******************************************
            // Pneumatics Ports
            // ******************************************
            RIGHT_CLIMB_LOCK_PNEUMATIC_PORT = 7;
            LEFT_CLIMB_LOCK_PNEUMATIC_PORT = 6;

            INTAKE_EXTEND_PNEUMATIC_PORT = 4;
            INTAKE_RETRACT_PNEUMATIC_PORT = 5;

            STOPPER_RETRACT_PNEUMATIC_PORT = 2;
            STOPPER_EXTEND_PNEUMATIC_PORT = 3;

            HOOD_RETRACT_PNEUMATIC_PORT = 0;
            HOOD_EXTEND_PNEUMATIC_PORT = 1;

            // ******************************************
            // DIO Ports
            // ******************************************
            TOWER_BALL_DETECT_DIO_PORT = 0;
            CAROUSEL_BALL_DETECT_DIO_PORT = 1;

            LEFT_CLIMB_BOTTOM_DETECT_DIO_PORT = 3;
            RIGHT_CLIMB_BOTTOM_DETECT_DIO_PORT = 5;

            break;

        case RobotConst.TEST_ROBOT:
        default:

            LEFT_DRIVE_SPEED_CONTROLLER_CAN_ADDRESS           = 10;
            LEFT_DRIVE_SPEED_CONTROLLER_TYPE                  = TCanSpeedControllerType.TALON_SRX;
            LEFT_DRIVE_FOLLOWER_SPEED_CONTROLLER_CAN_ADDRESS  = 11;
            LEFT_DRIVE_FOLLOWER_SPEED_CONTROLLER_TYPE         = TCanSpeedControllerType.VICTOR_SPX;
            LEFT_DRIVE_MOTOR_ISINVERTED                       = TConst.INVERTED;
            LEFT_DRIVE_ENCODER_ISINVERTED                     = TConst.INVERTED;

            RIGHT_DRIVE_SPEED_CONTROLLER_ADDRESS              = 20;
            RIGHT_DRIVE_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.TALON_SRX;
            RIGHT_DRIVE_FOLLOWER_SPEED_CONTROLLER_ADDRESS     = 21;
            RIGHT_DRIVE_FOLLOWER_SPEED_CONTROLLER_TYPE        = TCanSpeedControllerType.TALON_SRX;
            RIGHT_DRIVE_MOTOR_ISINVERTED                      = TConst.NOT_INVERTED;
            RIGHT_DRIVE_ENCODER_ISINVERTED                    = TConst.NOT_INVERTED;

            INTAKE_TOP_MOTOR_CAN_SPEED_CONTROLLER_CAN_ADDRESS = 15;
            INTAKE_TOP_MOTOR_SPEED_CONTROLLER_TYPE            = TCanSpeedControllerType.VICTOR_SPX;
            INTAKE_TOP_MOTOR_ISINVERTED                       = TConst.NOT_INVERTED;

            INTAKE_BOTTOM_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS  = 16;
            INTAKE_BOTTOM_MOTOR_SPEED_CONTROLLER_TYPE         = TCanSpeedControllerType.VICTOR_SPX;
            INTAKE_BOTTOM_MOTOR_ISINVERTED                    = TConst.NOT_INVERTED;

            CAROUSEL_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS       = 25;
            CAROUSEL_MOTOR_SPEED_CONTROLLER_TYPE              = TCanSpeedControllerType.VICTOR_SPX;
            CAROUSEL_MOTOR_ISINVERTED                         = TConst.NOT_INVERTED;
            CAROUSEL_ENCODER_ISINVERTED                       = TConst.NOT_INVERTED;
            CAROUSEL_MOTOR_POWER_PORT                         = 10;

            TOWER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS          = 26;
            TOWER_MOTOR_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.VICTOR_SPX;
            TOWER_MOTOR_ISINVERTED                            = TConst.NOT_INVERTED;

            KICKER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS          = 36;
            KICKER_MOTOR_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.VICTOR_SPX;
            KICKER_MOTOR_ISINVERTED                            = TConst.INVERTED;

            SHOOTER_SPEED_CONTROLLER_CAN_ADDRESS              = 50;
            SHOOTER_SPEED_CONTROLLER_TYPE                     = TCanSpeedControllerType.VICTOR_SPX;
            SHOOTER_SPEED_FOLLOWER_CAN_ADDRESS                = 51;
            SHOOTER_SPEED_FOLLOWER_TYPE                       = TCanSpeedControllerType.VICTOR_SPX;
            SHOOTER_MOTOR_ISINVERTED                          = TConst.NOT_INVERTED;
            SHOOTER_ENCODER_ISINVERTED                        = TConst.NOT_INVERTED;

            GYRO_ANALOG_PORT                                  = 0;
            GYRO_ISINVERTED                                   = TConst.INVERTED;

            //FIND THE ACTUAL ADDRESS
            LEFT_CLIMB_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS     = 1;
            LEFT_CLIMB_MOTOR_SPEED_CONTROLLER_TYPE 		      = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            LEFT_CLIMB_MOTOR_ISINVERTED                       = TConst.NOT_INVERTED;
            LEFT_CLIMB_ENCODER_ISINVERTED                     = TConst.NOT_INVERTED;

            RIGHT_CLIMB_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS    = 2;
            RIGHT_CLIMB_MOTOR_SPEED_CONTROLLER_TYPE 	      = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            RIGHT_CLIMB_MOTOR_ISINVERTED                      = TConst.NOT_INVERTED;
            RIGHT_CLIMB_ENCODER_ISINVERTED                    = TConst.NOT_INVERTED;

            // ******************************************
            // Pneumatics Ports
            // ******************************************
            RIGHT_CLIMB_LOCK_PNEUMATIC_PORT = 7;
            LEFT_CLIMB_LOCK_PNEUMATIC_PORT = 6;

            INTAKE_EXTEND_PNEUMATIC_PORT = 4;
            INTAKE_RETRACT_PNEUMATIC_PORT = 5;

            STOPPER_RETRACT_PNEUMATIC_PORT = 2;
            STOPPER_EXTEND_PNEUMATIC_PORT = 3;

            HOOD_RETRACT_PNEUMATIC_PORT = 0;
            HOOD_EXTEND_PNEUMATIC_PORT = 1;

            // ******************************************
            // DIO Ports
            // ******************************************
            TOWER_BALL_DETECT_DIO_PORT = 0;
            CAROUSEL_BALL_DETECT_DIO_PORT = 1;

            LEFT_CLIMB_BOTTOM_DETECT_DIO_PORT = 3;
            RIGHT_CLIMB_BOTTOM_DETECT_DIO_PORT = 5;


        }
    }
}
