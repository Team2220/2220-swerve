/**
 * A class containing general configuration settings for the swerve drivetrain.
 *
 * @author Ryan Hendrickson
 * @since 2023
 */
package frc.twilight.swerve.config;

public final class GeneralConfig {

    /**
     * The length of the drivetrain, measured from middle of wheel to middle of wheel.
     * This value does not need to be super accurate, it is used as a ratio of length to width.
     */
    public static final double DT_LENGTH = 1;

    /**
     * The width of the drivetrain, measured from middle of wheel to middle of wheel.
     * This value does not need to be super accurate, it is used as a ratio of length to width.
     */
    public static final double DT_WIDTH = 1;

    /**
     * The starting angle of the gyroscope in degrees.
     */
    public static final double DT_START_GYRO = 0;

    /**
     * The starting x-coordinate of the drivetrain's position in meters.
     */
    public static final double DT_START_X = 0;

    /**
     * The starting y-coordinate of the drivetrain's position in meters.
     */
    public static final double DT_START_Y = 0;

    /**
     * A boolean value indicating whether to enable velocity control for the drivetrain.
     */
    public static final boolean DT_VEL_CONTROL_ENABLE = true;

    /**
     * The maximum velocity of the drivetrain in meters per second.
     */
    public static final double DT_MAX_VEL = 20;

    /**
     * A boolean value indicating whether to enable acceleration control for the drivetrain.
     */
    public static final boolean DT_ACCEL_CONTROL_ENABLE = true;

        /**
     * The maximum acceleration of the drivetrain in meters per second squared.
     */
    public static final double DT_MAX_ACCEL = 10;

    /**
     * A boolean value indicating whether to enable rotational velocity control for the drivetrain.
     */
    public static final boolean DT_ROT_VEL_CONTROL_ENABLE = true;

    /**
     * The maximum rotational velocity of the drivetrain in degrees per second.
     */
    public static final double DT_MAX_ROT_VEL = 180;

    /**
     * A boolean value indicating whether to enable rotational acceleration control for the drivetrain.
     */
    public static final boolean DT_ROT_ACCEL_CONTROL_ENABLE = true;

    /**
     * The maximum rotational acceleration of the drivetrain in degrees per second squared.
     */
    public static final double DT_MAX_ROT_ACCEL = 90;

    /**
     * The diameter of the drivetrain.
     * This value is calculated based on the DT_LENGTH and DT_WIDTH values.
     */
    public static final double DT_DIAMETER = Math.sqrt(Math.pow(DT_LENGTH, 2) + Math.pow(DT_WIDTH, 2));
}
