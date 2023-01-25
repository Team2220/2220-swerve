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
  SwerveModule swerveModuleFR = new SwerveModule(CANidConfig.DT_FR_DM, CANidConfig.DT_FR_SM, CANidConfig.DT_FR_SE, ModuleConfig.DT_FR_SE_OFFSET);
  SwerveModule swerveModuleBL = new SwerveModule(CANidConfig.DT_BL_DM, CANidConfig.DT_BL_SM, CANidConfig.DT_BL_SE, ModuleConfig.DT_BL_SE_OFFSET);
  SwerveModule swerveModuleBR = new SwerveModule(CANidConfig.DT_BR_DM, CANidConfig.DT_BR_SM, CANidConfig.DT_BR_SE, ModuleConfig.DT_BR_SE_OFFSET);

  // // AnalogInput enc = new AnalogInput(0);
  // WheelVector vect = new WheelVector(0, 0);

  // TunableDouble p = new TunableDouble("dP", 0, true);
  // TunableDouble i = new TunableDouble("dI", 0, true);
  // TunableDouble d = new TunableDouble("dD", 0, true);
  // TunableDouble f = new TunableDouble("dF", 0, true);

  // TunableDouble sP = new TunableDouble("sP", 0, true);
  // TunableDouble sI = new TunableDouble("sI", 0, true);
  // TunableDouble sD = new TunableDouble("sD", 0, true);
  // TunableDouble sF = new TunableDouble("sF", 0, true);

  // TunableDouble set = new TunableDouble("Vel", 0, true);
  // TunableDouble set2 = new TunableDouble("Ang", 0, true);

  int num = 0;

  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {
    Shuffleboard.getTab("PID").addNumber("enc", () -> swerveModuleFL.get().getVelocity());
    
  }

  @Override
  public void periodic() {
    // vect.setVelocity(set.getValue());
    // vect.setAngle(set2.getValue());

    // swerveModuleFL.set(vect);
    // swerveModuleFR.set(vect);
    // swerveModuleBL.set(vect);
    // swerveModuleBR.set(vect);

    // double p1 = p.getValue();
    // double i1 = i.getValue();
    // double d1 = d.getValue();
    // double f1 = f.getValue();

    // double p2 = sP.getValue();
    // double i2 = sI.getValue();
    // double d2 = sD.getValue();
    // double f2 = sF.getValue();

    // swerveModuleFL.setDrivePID(p1, i1, d1, f1);
    // swerveModuleFR.setDrivePID(p1, i1, d1, f1);
    // swerveModuleBL.setDrivePID(p1, i1, d1, f1);
    // swerveModuleBR.setDrivePID(p1, i1, d1, f1);

    // swerveModuleFL.setSteerPID(p2, i2, d2, f2);
    // swerveModuleFR.setSteerPID(p2, i2, d2, f2);
    // swerveModuleBL.setSteerPID(p2, i2, d2, f2);
    // swerveModuleBR.setSteerPID(p2, i2, d2, f2);

    // System.out.println(
    //   " FL: " + swerveModuleFL.getEncoderAngle() +
    //   " FR: " + swerveModuleFR.getEncoderAngle() +
    //   " BL: " + swerveModuleBL.getEncoderAngle() +
    //   " BR: " + swerveModuleBR.getEncoderAngle()
    // );

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
