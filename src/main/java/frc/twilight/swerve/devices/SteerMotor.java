package frc.twilight.swerve.devices;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.twilight.swerve.config.ModuleConfig;
import frc.twilight.swerve.config.PIDconfig;

public class SteerMotor {
    private static final int TIMOUT_MS = 10;
    
    private final TalonFX motor;
    public static final double TICKS_PER_REVOLUTION = 2048;
    public static final double ANGLE_UNITS_PER_ROTATION = 360;

    public SteerMotor(int canID) {
        motor = new TalonFX(canID);

        // Reset motor settings
        motor.configFactoryDefault();

        // Set up PID
        motor.config_kP(0, PIDconfig.DT_STEER_P, TIMOUT_MS);
        motor.config_kI(0, PIDconfig.DT_STEER_I, TIMOUT_MS);
        motor.config_kD(0, PIDconfig.DT_STEER_D, TIMOUT_MS);
        motor.config_kF(0, PIDconfig.DT_STEER_F, TIMOUT_MS);

        // Sets motor inversion
        motor.setInverted(ModuleConfig.DT_STEER_MOTOR_INVERTED);

        // Motor is neutral when within range
        motor.configNeutralDeadband(0.1, TIMOUT_MS);

        // Set motor to use internal encoder
        motor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, TIMOUT_MS);

        // Seconds from neutral to full
        motor.configClosedloopRamp(0.1, TIMOUT_MS);

        // Set encoder to 0
        motor.setSelectedSensorPosition(0, 0, TIMOUT_MS);

        // Set max/min output
        motor.configPeakOutputForward(1, TIMOUT_MS);
        motor.configPeakOutputReverse(-1, TIMOUT_MS);

        // Set nominal output
        motor.configNominalOutputForward(0, TIMOUT_MS);
        motor.configNominalOutputReverse(0, TIMOUT_MS);
    }

    public void setAngle(double angle) {
        motor.set(ControlMode.Position, angle * TICKS_PER_REVOLUTION / ANGLE_UNITS_PER_ROTATION);
    }

    public double getAngle() {
        return motor.getSelectedSensorPosition() * ANGLE_UNITS_PER_ROTATION / TICKS_PER_REVOLUTION;
    }

    public double getRPM() {
        return motor.getSelectedSensorVelocity() / TICKS_PER_REVOLUTION * 600;
    }

    public void setP(double p) {
        motor.config_kP(0, p, TIMOUT_MS);
    }

    public void setI(double i) {
        motor.config_kI(0, i, TIMOUT_MS);
    }

    public void setD(double d) {
        motor.config_kD(0, d, TIMOUT_MS);
    }

    public void setF(double f) {
        motor.config_kF(0, f, TIMOUT_MS);
    }

    public void setPID(double p, double i, double d, double f) {
        setP(p);
        setI(i);
        setD(d);
        setF(f);
    }

    public void setInverted(boolean inverted) {
        motor.setInverted(inverted ? ModuleConfig.DT_STEER_MOTOR_INVERTED : !ModuleConfig.DT_STEER_MOTOR_INVERTED);
    }

    public void setEncoderPosition(double angle) {
        motor.setSelectedSensorPosition(angle * TICKS_PER_REVOLUTION / ANGLE_UNITS_PER_ROTATION, 0, TIMOUT_MS);
    }
}
