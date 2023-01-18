/**
 * A class containing configuration settings for the PID controllers used in the swerve drivetrain.
 *
 * @author Ryan Hendrickson
 * @since 2023
 */
package frc.twilight.swerve.config;

import frc.twilight.tunables.TunableDouble;

public class PIDconfig {
    /**
     * A boolean value indicating whether to enable the use of values from Shuffleboard for the PID controllers.
     */
    public static final boolean SHUFFLEBOARD_VALUES_ENABLED = false;

    /**
     * The P constant for the drive motor PID controller.
     */
    public static final TunableDouble DT_DRIVE_P = new TunableDouble("DT_DRIVE_P", 0.1, SHUFFLEBOARD_VALUES_ENABLED);

    /**
     * The I constant for the drive motor PID controller.
     */
    public static final TunableDouble DT_DRIVE_I = new TunableDouble("DT_DRIVE_I", 0, SHUFFLEBOARD_VALUES_ENABLED);

    /**
     * The D constant for the drive motor PID controller.
     */
    public static final TunableDouble DT_DRIVE_D = new TunableDouble("DT_DRIVE_D", 0.2, SHUFFLEBOARD_VALUES_ENABLED);

    /**
     * The F constant for the drive motor PID controller.
     */
    public static final TunableDouble DT_DRIVE_F = new TunableDouble("DT_DRIVE_F", 0.052, SHUFFLEBOARD_VALUES_ENABLED);

    /**
     * The P constant for the steer motor PID controller.
     */
    public static final TunableDouble DT_STEER_P = new TunableDouble("DT_STEER_P", 0.1, SHUFFLEBOARD_VALUES_ENABLED);

    /**
     * The I constant for the steer motor PID controller.
     */
    public static final TunableDouble DT_STEER_I = new TunableDouble("DT_STEER_I", 0, SHUFFLEBOARD_VALUES_ENABLED);
    
    /**
     * The D constant for the steer motor PID controller.
     */
    public static final TunableDouble DT_STEER_D = new TunableDouble("DT_STEER_D", 0.2, SHUFFLEBOARD_VALUES_ENABLED);

    /**
     * The F constant for the steer motor PID controller.
     */
    public static final TunableDouble DT_STEER_F = new TunableDouble("DT_STEER_F", 0, SHUFFLEBOARD_VALUES_ENABLED);

    /**
     * The P constant for the autonomous PID controller.
     */
    public static final TunableDouble DT_AUTO_P = new TunableDouble("DT_AUTO_P", 0, SHUFFLEBOARD_VALUES_ENABLED);

    /**
     * The I constant for the autonomous PID controller.
     */
    public static final TunableDouble DT_AUTO_I = new TunableDouble("DT_AUTO_I", 0, SHUFFLEBOARD_VALUES_ENABLED);

    /**
     * The D constant for the autonomous PID controller.
     */
    public static final TunableDouble DT_AUTO_D = new TunableDouble("DT_AUTO_D", 0, SHUFFLEBOARD_VALUES_ENABLED);

    /**
     * The F constant for the autonomous PID controller.
     */
    public static final TunableDouble DT_AUTO_F = new TunableDouble("DT_AUTO_F", 0, SHUFFLEBOARD_VALUES_ENABLED);
}

