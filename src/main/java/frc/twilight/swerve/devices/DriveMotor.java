package frc.twilight.swerve.devices;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.twilight.swerve.config.*;

public class DriveMotor {
    private static final int TIMOUT_MS = 10;

    private final TalonFX motor;
    private static final double TICKS_PER_REVOLUTION = 2048;

    private double p = PIDconfig.DT_DRIVE_P;
    private double i = PIDconfig.DT_DRIVE_I;
    private double d = PIDconfig.DT_DRIVE_D;
    private double f = PIDconfig.DT_DRIVE_F;
    
    public DriveMotor(int id) {
        motor = new TalonFX(id);

        // Reset motor settings
        motor.configFactoryDefault();

        // Set up PID
        motor.config_kP(0, p, TIMOUT_MS);
        motor.config_kI(0, i, TIMOUT_MS);
        motor.config_kD(0, d, TIMOUT_MS);
        motor.config_kF(0, f, TIMOUT_MS);

        // Sets motor inversion
        motor.setInverted(ModuleConfig.DT_DRIVE_MOTOR_INVERTED);

        // Motor is neutral when within range
        motor.configNeutralDeadband(0.001, TIMOUT_MS);

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

    public void setRPM(double rpm) {
        motor.set(ControlMode.Velocity, rpm * TICKS_PER_REVOLUTION / 600);
    }

    public double getRPM() {
        return motor.getSelectedSensorVelocity() * 600 / TICKS_PER_REVOLUTION;
    }

    public void getPosition() {
        motor.getSelectedSensorPosition();
    }

    public void setP(double p) {
        if (this.p != p) {
            motor.config_kP(0, p, TIMOUT_MS);
            this.p = p;
        }
    }

    public void setI(double i) {
        if (this.i != i) {
            motor.config_kI(0, i, TIMOUT_MS);
            this.i = i;
        }
    }

    public void setD(double d) {
        if (this.d != d) {
            motor.config_kD(0, d, TIMOUT_MS);
            this.d = d;
        }
    }

    public void setF(double f) {
        if (this.f != f) {
            motor.config_kF(0, f, TIMOUT_MS);
            this.f = f;
        }
    }

    public void setPID(double p, double i, double d, double f) {
        setP(p);
        setI(i);
        setD(d);
        setF(f);
    }

    public void setInverted(boolean inverted) {
        motor.setInverted(inverted ? ModuleConfig.DT_DRIVE_MOTOR_INVERTED : !ModuleConfig.DT_DRIVE_MOTOR_INVERTED);
    }
}
