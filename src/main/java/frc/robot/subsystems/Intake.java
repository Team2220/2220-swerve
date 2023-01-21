package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.Constants;

public class Intake {
    private TalonFX left = new TalonFX(Constants.INTAKE_LEFT_TALONFX);
    private TalonFX right = new TalonFX(Constants.INTAKE_RIGHT_TALONFX);

    public Intake(){
        left.configVoltageCompSaturation(10);
        right.configVoltageCompSaturation(10);
        
        StatorCurrentLimitConfiguration statorConfig = new StatorCurrentLimitConfiguration();
        statorConfig.enable = true;
        statorConfig.currentLimit = 10;
        right.configStatorCurrentLimit(statorConfig);
        left.configStatorCurrentLimit(statorConfig);     
        
        SupplyCurrentLimitConfiguration supplyConfig = new SupplyCurrentLimitConfiguration();
        supplyConfig.enable = true;
        supplyConfig.currentLimit = 10;
        right.configSupplyCurrentLimit(supplyConfig);
        left.configSupplyCurrentLimit(supplyConfig);

    }

    
}
