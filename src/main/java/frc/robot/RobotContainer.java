// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.auto.TestPath;
import frc.twilight.Controller;
import frc.twilight.swerve.commands.ControllerDrive;
import frc.twilight.swerve.commands.GoToCommand;
import frc.twilight.swerve.commands.ResetGyro;
import frc.twilight.swerve.subsystems.Swerve;
import frc.twilight.swerve.vectors.Position;
import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final Swerve m_swerve = new Swerve();

  private final Controller m_controller = new Controller(0);

  private final ControllerDrive m_controllerDrive = new ControllerDrive(m_swerve, () -> m_controller.getLeftX(), () -> m_controller.getLeftY(), () -> m_controller.getRightX());

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new Trigger(() -> m_controller.getButtonPressed(Controller.Button.A)).onTrue(new ResetGyro(m_swerve));;

    new Trigger(() -> m_controller.getButtonPressed(Controller.Button.X)).onTrue(new TestPath(m_swerve));
    new Trigger(() -> m_controller.getButtonPressed(Controller.Button.Y)).onTrue(m_controllerDrive);

  }

  public Command getTeleopCommand() {
    return m_controllerDrive;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
