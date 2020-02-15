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

    public static final int						TOWER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS;
    public static final TCanSpeedControllerType	TOWER_MOTOR_SPEED_CONTROLLER_TYPE;
    public static final boolean                 TOWER_MOTOR_ISINVERTED;

    public static final int                     SHOOTER_SPEED_CONTROLLER_CAN_ADDRESS;
    public static final TCanSpeedControllerType SHOOTER_SPEED_CONTROLLER_TYPE;
    public static final int                     SHOOTER_SPEED_FOLLOWER_CAN_ADDRESS;
    public static final TCanSpeedControllerType SHOOTER_SPEED_FOLLOWER_TYPE;
    public static final boolean                 SHOOTER_MOTOR_ISINVERTED;
    public static final boolean                 SHOOTER_ENCODER_ISINVERTED;

    // ******************************************
    // Gyro Ports
    // ******************************************
    public static final int                     GYRO_ANALOG_PORT;
    public static final boolean                 GYRO_ISINVERTED;

    // ******************************************
    // Pneumatics Ports
    // ******************************************
    public static final int                     INTAKE_EXTEND_PNEUMATIC_PORT = 0;

    public static final int						SHOOTER_STOPPER_PNEUMATIC_PORT = 1;
    public static final int						SHOOTER_DEPLOYER_PNEUMATIC_PORT = 2;

    // ******************************************
    // DIO Ports
    // ******************************************
    public static final int                      TOWER_BALL_DETECT_DIO_PORT = 0;
    public static final int                      CAROUSEL_BALL_DETECT_DIO_PORT = 1;

    // Initializers if this code will be deployed to more than one
    // robot with different mappings
    static {

        switch (RobotConst.robot) {

        case RobotConst.PRACTICE_ROBOT:

            LEFT_DRIVE_SPEED_CONTROLLER_CAN_ADDRESS           = 10;
            LEFT_DRIVE_SPEED_CONTROLLER_TYPE                  = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            LEFT_DRIVE_FOLLOWER_SPEED_CONTROLLER_CAN_ADDRESS  = 11;
            LEFT_DRIVE_FOLLOWER_SPEED_CONTROLLER_TYPE         = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            LEFT_DRIVE_MOTOR_ISINVERTED                       = TConst.INVERTED;
            LEFT_DRIVE_ENCODER_ISINVERTED                     = TConst.INVERTED;

            RIGHT_DRIVE_SPEED_CONTROLLER_ADDRESS              = 20;
            RIGHT_DRIVE_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            RIGHT_DRIVE_FOLLOWER_SPEED_CONTROLLER_ADDRESS     = 21;
            RIGHT_DRIVE_FOLLOWER_SPEED_CONTROLLER_TYPE        = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            RIGHT_DRIVE_MOTOR_ISINVERTED                      = TConst.NOT_INVERTED;
            RIGHT_DRIVE_ENCODER_ISINVERTED                    = TConst.NOT_INVERTED;

            INTAKE_TOP_MOTOR_CAN_SPEED_CONTROLLER_CAN_ADDRESS = 30;
            INTAKE_TOP_MOTOR_SPEED_CONTROLLER_TYPE            = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            INTAKE_TOP_MOTOR_ISINVERTED                       = TConst.NOT_INVERTED;

            INTAKE_BOTTOM_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS  = 31;
            INTAKE_BOTTOM_MOTOR_SPEED_CONTROLLER_TYPE         = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            INTAKE_BOTTOM_MOTOR_ISINVERTED                    = TConst.NOT_INVERTED;

            CAROUSEL_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS       = 40;
            CAROUSEL_MOTOR_SPEED_CONTROLLER_TYPE              = TCanSpeedControllerType.VICTOR_SPX;
            CAROUSEL_MOTOR_ISINVERTED                         = TConst.NOT_INVERTED;
            CAROUSEL_ENCODER_ISINVERTED                       = TConst.NOT_INVERTED;

            TOWER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS          = 45;
            TOWER_MOTOR_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.VICTOR_SPX;
            TOWER_MOTOR_ISINVERTED                            = TConst.NOT_INVERTED;

            SHOOTER_SPEED_CONTROLLER_CAN_ADDRESS              = 50;
            SHOOTER_SPEED_CONTROLLER_TYPE                     = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            SHOOTER_SPEED_FOLLOWER_CAN_ADDRESS                = 51;
            SHOOTER_SPEED_FOLLOWER_TYPE                       = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            SHOOTER_MOTOR_ISINVERTED                          = TConst.NOT_INVERTED;
            SHOOTER_ENCODER_ISINVERTED                        = TConst.NOT_INVERTED;

            GYRO_ANALOG_PORT                                  = 0; // Not used
            GYRO_ISINVERTED                                   = TConst.NOT_INVERTED;

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

            TOWER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS          = 26;
            TOWER_MOTOR_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.VICTOR_SPX;
            TOWER_MOTOR_ISINVERTED                            = TConst.NOT_INVERTED;

            SHOOTER_SPEED_CONTROLLER_CAN_ADDRESS              = 10;
            SHOOTER_SPEED_CONTROLLER_TYPE                     = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            SHOOTER_SPEED_FOLLOWER_CAN_ADDRESS                = 11;
            SHOOTER_SPEED_FOLLOWER_TYPE                       = TCanSpeedControllerType.SPARK_MAX_BRUSHLESS;
            SHOOTER_MOTOR_ISINVERTED                          = TConst.NOT_INVERTED;
            SHOOTER_ENCODER_ISINVERTED                        = TConst.NOT_INVERTED;

            GYRO_ANALOG_PORT                                  = 0; // Not used
            GYRO_ISINVERTED                                   = TConst.NOT_INVERTED;

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

            TOWER_MOTOR_SPEED_CONTROLLER_CAN_ADDRESS          = 26;
            TOWER_MOTOR_SPEED_CONTROLLER_TYPE                 = TCanSpeedControllerType.VICTOR_SPX;
            TOWER_MOTOR_ISINVERTED                            = TConst.NOT_INVERTED;

            SHOOTER_SPEED_CONTROLLER_CAN_ADDRESS              = 50;
            SHOOTER_SPEED_CONTROLLER_TYPE                     = TCanSpeedControllerType.VICTOR_SPX;
            SHOOTER_SPEED_FOLLOWER_CAN_ADDRESS                = 51;
            SHOOTER_SPEED_FOLLOWER_TYPE                       = TCanSpeedControllerType.VICTOR_SPX;
            SHOOTER_MOTOR_ISINVERTED                          = TConst.NOT_INVERTED;
            SHOOTER_ENCODER_ISINVERTED                        = TConst.NOT_INVERTED;

            GYRO_ANALOG_PORT                                  = 0;
            GYRO_ISINVERTED                                   = TConst.INVERTED;

        }
    }
}
