// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.twilight.tunables.TunableDouble;
import frc.twilight.swerve.SwerveModule;
import frc.twilight.swerve.config.CANidConfig;
import frc.twilight.swerve.config.ModuleConfig;
import frc.twilight.swerve.vectors.WheelVector;

public class ExampleSubsystem extends SubsystemBase {

  SwerveModule swerveModuleFL = new SwerveModule(CANidConfig.DT_FL_DM, CANidConfig.DT_FL_SM, CANidConfig.DT_FL_SE, ModuleConfig.DT_FL_SE_OFFSET);
  // SwerveModule swerveModuleFR = new SwerveModule(CANidConfig.DT_FR_DM, CANidConfig.DT_FR_SM, CANidConfig.DT_FR_SE, ModuleConfig.DT_FR_SE_OFFSET);
  // SwerveModule swerveModuleBL = new SwerveModule(CANidConfig.DT_BL_DM, CANidConfig.DT_BL_SM, CANidConfig.DT_BL_SE, ModuleConfig.DT_BL_SE_OFFSET);
  // SwerveModule swerveModuleBR = new SwerveModule(CANidConfig.DT_BR_DM, CANidConfig.DT_BR_SM, CANidConfig.DT_BR_SE, ModuleConfig.DT_BR_SE_OFFSET);

  // AnalogInput enc = new AnalogInput(0);
  WheelVector vect = new WheelVector(0, 0);

  TunableDouble p = new TunableDouble("P", 0, true);
  TunableDouble i = new TunableDouble("I", 0, true);
  TunableDouble d = new TunableDouble("D", 0, true);
  TunableDouble f = new TunableDouble("F", 0, true);

  TunableDouble set = new TunableDouble("Vel", 0, true);

  int num = 0;

  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {
    Shuffleboard.getTab("PID").addNumber("enc", () -> swerveModuleFL.get().getVelocity());
    
  }

  @Override
  public void periodic() {
    vect.setVelocity(set.getValue());

    swerveModuleFL.set(vect);

    swerveModuleFL.setDrivePID(p.getValue(), i.getValue(), d.getValue(), f.getValue());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
