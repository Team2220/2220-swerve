package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {

    private TalonFX wrist = new TalonFX(Constants.WRIST_TALONFX);
    private TalonFX shoulder = new TalonFX(Constants.SHOULDER_TALONFX);

    ShuffleboardTab arm = Shuffleboard.getTab("arm");
    GenericEntry shoulderSB = arm.add("shoulder angle", 0).getEntry();
    GenericEntry wristSB = arm.add("wrist angle", 0).getEntry();

    public Arm() {
        wrist.configVoltageCompSaturation(10);
        shoulder.configVoltageCompSaturation(10);

        wrist.setInverted(Constants.WRIST_INVERTED);
        shoulder.setInverted(Constants.SHOULDER_INVERTED);

        SupplyCurrentLimitConfiguration supplyConfig = new SupplyCurrentLimitConfiguration();
        supplyConfig.currentLimit = 10;
        supplyConfig.enable = true;
        shoulder.configSupplyCurrentLimit(supplyConfig);
        wrist.configSupplyCurrentLimit(supplyConfig);

        StatorCurrentLimitConfiguration config = new StatorCurrentLimitConfiguration();
        config.currentLimit = 10;
        config.enable = true;
        shoulder.configStatorCurrentLimit(config);
        wrist.configStatorCurrentLimit(config);

    }

    public void setWristPercentOutput(double value) {
        wrist.set(TalonFXControlMode.PercentOutput, value);
    }

    public void setShoulderPercentOutput(double value) {
        shoulder.set(TalonFXControlMode.PercentOutput, value);
    }

    // @Override
    // public class Arm periodic();

}
