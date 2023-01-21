package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {

    private TalonFX wrist = new TalonFX(0);
    private TalonFX shoulder = new TalonFX(1);

    
    
}
