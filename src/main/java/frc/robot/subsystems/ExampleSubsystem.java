// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.twilight.swerve.SwerveModule;
import frc.twilight.swerve.config.CANidConfig;
import frc.twilight.swerve.config.ModuleConfig;
import frc.twilight.swerve.devices.PWMencoder;
import frc.twilight.swerve.vectors.WheelVector;

public class ExampleSubsystem extends SubsystemBase {

  SwerveModule swerveModule = new SwerveModule(CANidConfig.DT_FL_DM, CANidConfig.DT_FL_SM, CANidConfig.DT_FL_SE, ModuleConfig.DT_FL_SE_OFFSET);
  // AnalogInput enc = new AnalogInput(0);
  WheelVector vect = new WheelVector(0.2, 0);


  int num = 0;

  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {

  }

  @Override
  public void periodic() {
    swerveModule.set(vect);
    System.out.println("Speed: " + swerveModule.get().getVelocity() + " Angle: " + swerveModule.get().getAngle());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
