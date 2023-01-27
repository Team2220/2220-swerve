// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.twilight.swerve.commands;

import frc.twilight.swerve.config.GeneralConfig;
import frc.twilight.swerve.config.PIDconfig;
import frc.twilight.swerve.subsystems.Swerve;
import frc.twilight.swerve.vectors.DriveVector;
import frc.twilight.swerve.vectors.Position;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class GoToCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Swerve m_subsystem;

  private final TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(GeneralConfig.DT_MAX_VEL, GeneralConfig.DT_MAX_ACCEL);
  private final TrapezoidProfile.Constraints constraintsRot = new TrapezoidProfile.Constraints(GeneralConfig.DT_MAX_ROT_VEL, GeneralConfig.DT_MAX_ROT_ACCEL);

  private TrapezoidProfile.State goalX = new TrapezoidProfile.State();
  private TrapezoidProfile.State goalY = new TrapezoidProfile.State();
  private TrapezoidProfile.State goalRot = new TrapezoidProfile.State();

  private PIDController pidX = new PIDController(PIDconfig.DT_AUTO_P.getValue(), PIDconfig.DT_AUTO_I.getValue(), PIDconfig.DT_AUTO_D.getValue());
  private PIDController pidY = new PIDController(PIDconfig.DT_AUTO_P.getValue(), PIDconfig.DT_AUTO_I.getValue(), PIDconfig.DT_AUTO_D.getValue());
  private PIDController pidRot = new PIDController(PIDconfig.DT_AUTO_ROT_P.getValue(), PIDconfig.DT_AUTO_ROT_I.getValue(), PIDconfig.DT_AUTO_ROT_D.getValue());

  private final double kDt = 0.02;

  /**
   * Creates a new GoToCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public GoToCommand(Swerve subsystem, Position goal) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);

    goalX = new TrapezoidProfile.State(goal.getX(), 0);
    goalY = new TrapezoidProfile.State(goal.getY(), 0);
    goalRot = new TrapezoidProfile.State(goal.getAngle(), 0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Position currentPos = m_subsystem.getOdo();
    DriveVector currentVec = m_subsystem.getDrive();
    TrapezoidProfile.State currentX = new TrapezoidProfile.State(currentPos.getX(), currentVec.getStr());
    TrapezoidProfile.State currentY = new TrapezoidProfile.State(currentPos.getY(), currentVec.getFwd());
    TrapezoidProfile.State currentRot = new TrapezoidProfile.State(currentPos.getAngle(), currentVec.getRcw());

    TrapezoidProfile profileX = new TrapezoidProfile(constraints, goalX, currentX);
    TrapezoidProfile profileY = new TrapezoidProfile(constraints, goalY, currentY);
    TrapezoidProfile profileRot = new TrapezoidProfile(constraintsRot, goalRot, currentRot);

    double xPos = profileX.calculate(kDt).position;
    double yPos = profileY.calculate(kDt).position;
    double rotPos = profileRot.calculate(kDt).position;

    double xVel = pidX.calculate(currentPos.getX(), xPos);
    double yVel = pidY.calculate(currentPos.getY(), yPos);
    double rotVel = pidRot.calculate(currentPos.getAngle(), rotPos);

    m_subsystem.setDrive(new DriveVector(xVel, yVel, rotVel));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }


}
