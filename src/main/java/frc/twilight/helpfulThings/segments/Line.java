package frc.twilight.helpfulThings.segments;

import edu.wpi.first.math.MathUtil;

public class Line implements Segment {
    private double angle;
    private double slope;
    private double length;
    private double xLength;
    private double yLength;

    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

        calculate();
    }

    private void calculate() {
        xLength = end.x - start.x;
        yLength = end.y - start.y;

        length = Math.sqrt(xLength * xLength + yLength * yLength);

        // Account for vertical line
        if (xLength == 0) 
            slope = Double.NaN;
        else 
            slope = yLength / xLength;
        
        angle = Math.toDegrees(Math.atan2(yLength, xLength));
    }

    public double getSegmentLength() {
        return length;
    }

    public Point getStartPoint() {
        return start;
    }

    public Point getEndPoint() {
        return end;
    }

    public Point getPointAlongSegmentPercent(double t) {
        MathUtil.clamp(t, 0, 1);

        return 
            new Point(
                start.x + (xLength * t), 
                start.y + (yLength * t)
            );
    }

    public Point getPointAlongSegmentDistance(double distance) {
        return getPointAlongSegmentPercent(distance / length);
    }

    public double getLineSlope() {
        return slope;
    }

    public double getLineAngle() {
        return angle;
    }

    public InfiniteLine extendLine() {
        return new InfiniteLine(
            angle,
            start
        );
    }
}
