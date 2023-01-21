package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
    private DutyCycleEncoder wristEncoder = new DutyCycleEncoder(Constants.WRIST_DUTYENCODER);
    private DutyCycleEncoder armEncoder = new DutyCycleEncoder(Constants.SHOULDER_DUTYENCODER); 
       
    private TalonFX wrist = new TalonFX(Constants.WRIST_TALONFX);
    private TalonFX shoulder = new TalonFX(Constants.SHOULDER_TALONFX);
   
    public Arm() {
        wrist.configVoltageCompSaturation(10);
        shoulder.configVoltageCompSaturation(10);

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

}
