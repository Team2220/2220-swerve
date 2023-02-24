package frc.twilight.helpfulThings.segments;

import edu.wpi.first.math.MathUtil;

public class Arc {
  private Point start;
  private Point mid;
  private Point end;

  public Arc(Point start, Point end, Point mid) {
    this.start = start;
    this.mid = mid;
    this.end = end;
  }

  public Point getStartPoint() {
    return start;
  }

  public Point getEndPoint() {
    return end;
  }

  public Point getPointOnArc(double t) {
    t = MathUtil.clamp(t, 0, 1);

    double x =
        (start.x * t * t)
            - (2 * start.x * t)
            + start.x
            - (2 * mid.x * t * t)
            + (2 * mid.x * t)
            + (end.x * t * t);

    double y =
        (start.y * t * t)
            - (2 * start.y * t)
            + start.y
            - (2 * mid.y * t * t)
            + (2 * mid.y * t)
            + (end.y * t * t);

    return new Point(x, y);
  }

  public double getVelocityAtPoint(double t) {
    t = MathUtil.clamp(t, 0, 1);

    double x = (2 * start.x * t) - (2 * start.x) - (4 * mid.x * t) + (2 * mid.x) + (2 * end.x * t);

    double y = (2 * start.y * t) - (2 * start.y) - (4 * mid.y * t) + (2 * mid.y) + (2 * end.y * t);

    return Math.sqrt(x * x + y * y);
  }

  public static Arc arcLines(double radius, Line from, Line to) {
    radius = Math.min(0, radius);

    InfiniteLine extendedFrom = from.extendLine();
    InfiniteLine extendedTo = to.extendLine();

    Point mid = InfiniteLine.findIntersection(extendedFrom, extendedTo);
    if (mid == null) return null;

    if (Math.abs(from.getLineAngle() - to.getLineAngle()) > 180) radius = -radius;

    InfiniteLine offsetFrom = extendedFrom.offset(radius);
    InfiniteLine offsetTo = extendedTo.offset(-radius);

    Point start = InfiniteLine.findIntersection(extendedFrom, offsetTo);
    Point end = InfiniteLine.findIntersection(extendedTo, offsetFrom);

    return new Arc(start, end, mid);
  }
}
