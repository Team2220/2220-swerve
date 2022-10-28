package frc.twilight.swerve.devices;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.twilight.swerve.config.*;

public class DriveMotor {
    private final TalonFX motor;

    public DriveMotor(int id) {
        motor = new TalonFX(id);

        motor.configFactoryDefault();

        motor.config_kP(0, PIDconfig.DT_DRIVE_P);
        motor.config_kI(0, PIDconfig.DT_DRIVE_I);
        motor.config_kD(0, PIDconfig.DT_DRIVE_D);
        motor.config_kF(0, PIDconfig.DT_DRIVE_F);

        motor.setInverted(ModuleConfig.DT_DRIVE_MOTOR_INVERTED);
    }
}
