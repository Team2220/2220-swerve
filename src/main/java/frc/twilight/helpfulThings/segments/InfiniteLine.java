package frc.twilight.helpfulThings.segments;

import frc.twilight.helpfulThings.Angles;

public class InfiniteLine {
  private double slope;
  private double angle;
  private double yIntercept;

  // This variable is only set if the line is vertical
  private double xIntercept;

  public InfiniteLine(double angle, double yIntercept) {
    this.angle = angle;
    this.yIntercept = yIntercept;

    slope = Angles.slopeFromAngle(angle);
  }

  public InfiniteLine(double angle, Point pointOnLine) {
    this.angle = angle;

    slope = Angles.slopeFromAngle(angle);

    if (Double.isFinite(slope)) {
      yIntercept = -pointOnLine.x * slope;
    } else {
      xIntercept = pointOnLine.x;
    }
  }

  public InfiniteLine offset(double offset) {
    double perpAngle = (angle + 90) % 360;

    perpAngle = Math.toRadians(perpAngle);

    double xOffset = Math.cos(perpAngle) * offset;
    double yOffset = Math.sin(perpAngle) * offset;

    return new InfiniteLine(angle, new Point(xOffset, yOffset));
  }

  public Point getPointFromX(double x) {
    return new Point(x, slope * x + yIntercept);
  }

  public Point getPointFromY(double y) {
    return new Point((y - yIntercept) / slope, y);
  }

  public static Point findIntersection(InfiniteLine a, InfiniteLine b) {
    // edge case: parellel lines, either intersect across the entire line or dont intersect at all
    if (a.slope == b.slope) return null;

    // edge case: a is vertical
    if (!Double.isFinite(a.slope)) return b.getPointFromX(a.xIntercept);

    // edge case: b is vertical
    if (!Double.isFinite(b.slope)) return a.getPointFromX(b.xIntercept);

    // probably more edge cases i forgot and stuff will break later.

    return a.getPointFromX((b.yIntercept - a.yIntercept) / (b.slope - a.slope));
  }
}
