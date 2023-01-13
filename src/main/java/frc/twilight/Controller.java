package frc.twilight;

import edu.wpi.first.wpilibj.XboxController;

public class Controller {
    private final XboxController controller;

    private double deadzone = 0.2;

    public Controller(int port) {
      controller = new XboxController(port);
    }

    public double getLeftX() {
      return deadband(controller.getLeftX(), deadzone);
    }

    public double getLeftY() {
      return -deadband(controller.getLeftY(), deadzone);
    }

    public double getRightX() {
      return deadband(controller.getRightX(), deadzone);
    }

    public double getRightY() {
      return -deadband(controller.getRightY(), deadzone);
    }
    
    public void setDeadband(double set) {
      deadzone = set;
    }

    public boolean getAButton() {
      return controller.getAButton();
    }

    private static double deadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
          if (value > 0.0) {
            return (value - deadband) / (1.0 - deadband);
          } else {
            return (value + deadband) / (1.0 - deadband);
          }
        } else {
          return 0.0;
        }
      }
}
