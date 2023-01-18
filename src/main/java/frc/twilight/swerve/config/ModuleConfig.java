/**
 * A class containing configuration settings for the swerve modules.
 *
 * @author Ryan Hendrickson
 * @since 2023
 */
package frc.twilight.swerve.config;

public class ModuleConfig {

    /**
     * The diameter of the wheels on the swerve modules in meters.
     */
    public static final double DT_WHEEL_DIAMETER = 0.10033;

    /**
     * The gear ratio of the drive motors on the swerve modules.
     */
    public static final double DT_DRIVE_GEAR_RATIO = (14.0 / 50.0) * (28.0 / 16.0) * (15.0 / 45.0);

    /**
     * A boolean value indicating whether the drive motors on the swerve modules are inverted.
     */
    public static final boolean DT_DRIVE_MOTOR_INVERTED = false;

    /**
     * The gear ratio of the steer motors on the swerve modules.
     */
    public static final double DT_STEER_GEAR_RATIO = 7.0 / 150.0;

    /**
     * A boolean value indicating whether the steer motors on the swerve modules are inverted.
     */
    public static final boolean DT_STEER_MOTOR_INVERTED = true;

    /**
     * The gear ratio of the steer encoders on the swerve modules.
     */
    public static final double DT_STEER_ENCODER_GEAR_RATIO = 1;

    /**
     * A boolean value indicating whether the steer encoders on the swerve modules are inverted.
     */
    public static final boolean DT_STEER_ENCODER_INVERTED = false;

    /**
     * The offset of the steer encoder on the front left swerve module.
     */
    public static final double DT_FL_SE_OFFSET = 128.1335;

    /**
     * The offset of the steer encoder on the front right swerve module.
     */
    public static final double DT_FR_SE_OFFSET = 333.5449;

    /**
     * The offset of the steer encoder on the back left swerve module.
     */
    public static final double DT_BL_SE_OFFSET = 125.0684;

    /**
     * The offset of the steer encoder on the back right swerve module.
     */
    public static final double DT_BR_SE_OFFSET = 182.8125;
}
