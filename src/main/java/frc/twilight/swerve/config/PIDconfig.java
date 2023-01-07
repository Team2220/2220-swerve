package frc.twilight.swerve.config;

public class PIDconfig {
    // PID values for the drive motor
    public static final double DT_DRIVE_P = 0; // An error of 1 rps results in ___ V output
    public static final double DT_DRIVE_I = 0; // An error of 1 rps increases output by ___ V each second
    public static final double DT_DRIVE_D = 0; // An acceleration of 1 rps/s results in ___ V output
    public static final double DT_DRIVE_F = 0;



    // PID values for the steer motor
    public static final double DT_STEER_P = 0.2; // old 1
    public static final double DT_STEER_I = 0.0; // old 0
    public static final double DT_STEER_D = 0.1; // old 70
    public static final double DT_STEER_F = 0;



    // Autonomous PID constants
    public static final double DT_AUTO_P = 0;
    public static final double DT_AUTO_I = 0;
    public static final double DT_AUTO_D = 0;
    public static final double DT_AUTO_F = 0;
}
