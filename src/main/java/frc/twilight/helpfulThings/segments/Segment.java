package frc.twilight.helpfulThings.segments;

public interface Segment {
  public double getSegmentLength();

  public Point getPointAlongSegmentDistance(double distance);

  public Point getPointAlongSegmentPercent(double t);

  public Point getStartPoint();

  public Point getEndPoint();
}
