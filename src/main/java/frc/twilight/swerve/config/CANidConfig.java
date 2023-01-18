/**
 * A class containing the CAN IDs for the swerve modules.
 *
 * @author Ryan Hendrickson
 * @since 2023
 */
package frc.twilight.swerve.config;

public class CANidConfig {
    /**
     * CAN ID for the front left drive motor
     */
    public static final int DT_FL_DM = 12;
    /**
     * CAN ID for the front left steer motor
     */
    public static final int DT_FL_SM = 11;

    /**
     * PWM encoder channel for the front left steer motor on the RoboRIO
     */
    public static final int DT_FL_SE = 0;

    /**
     * CAN ID for the front right drive motor
     */
    public static final int DT_FR_DM = 14;
    /**
     * CAN ID for the front right steer motor
     */
    public static final int DT_FR_SM = 13;
    
    /**
     * PWM encoder channel for the front right steer motor on the RoboRIO
     */
    public static final int DT_FR_SE = 1;

    /**
     * CAN ID for the back left drive motor
     */
    public static final int DT_BL_DM = 18;
    /**
     * CAN ID for the back left steer motor
     */
    public static final int DT_BL_SM = 17;
    
    /**
     * PWM encoder channel for the back left steer motor on the RoboRIO
     */
    public static final int DT_BL_SE = 3;

    /**
     * CAN ID for the back right drive motor
     */
    public static final int DT_BR_DM = 16;
    /**
     * CAN ID for the back right steer motor
     */
    public static final int DT_BR_SM = 15;
    
    /**
     * PWM encoder channel for the back right steer motor on the RoboRIO
     */
    public static final int DT_BR_SE = 2;
}
