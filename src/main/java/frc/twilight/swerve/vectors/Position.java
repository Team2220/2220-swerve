package frc.twilight.swerve.vectors;

import edu.wpi.first.math.geometry.Pose2d;

public class Position {
  private double x;
  private double y;
  private double angle;

  public Position(double x, double y, double angle) {
    this.x = x;
    this.y = y;
    this.angle = angle;
  }

  public Position(Pose2d pose) {
    this(pose.getX(), pose.getY(), pose.getRotation().getDegrees());
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getAngle() {
    return angle;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public void setAngle(double angle) {
    this.angle = angle;
  }

  public void add(DriveVector drive, double time) {
    x += drive.getStr() * time;
    y += drive.getFwd() * time;
    angle += drive.getRcw() * time;
  }
}
