package frc.twilight.swerve.commands;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.twilight.helpfulThings.segments.Arc;
import frc.twilight.helpfulThings.segments.Line;
import frc.twilight.helpfulThings.segments.Point;
import frc.twilight.swerve.config.GeneralConfig;
import frc.twilight.swerve.subsystems.Swerve;
import frc.twilight.swerve.vectors.Position;

public class SequentialMove extends CommandBase {
    private Swerve swerve;

    private Position[] positions;
    private Line[] lines;
    private Arc[] arcs;
    private double[] arcMultiplier;

    private double radius;
    private double endMovTol;
    private double endRotTol;

    private double totalLength;

    private Point[] points;

    public int kDt = 0;

    public SequentialMove(Swerve swerve, double radius, double endMovTol, double endRotTol, Position... positions) {
        if (positions.length < 1)
            return;

        this.swerve = swerve;

        this.positions = positions;
        this.radius = radius;
        this.endMovTol = endMovTol;
        this.endRotTol = endRotTol;
    }

    public SequentialMove(Swerve swerve, Position... positions) {
        this(swerve, 0.75, 0.05, 2.5, positions);
    }

    @Override
    public void initialize() {
        lines = new Line[positions.length];
        arcs = new Arc[positions.length];
        arcMultiplier = new double[positions.length];
        totalLength = 0;

        Position current = swerve.getOdo();

        lines[0] = new Line(
            new Point(current.getX(), current.getY()),
            new Point (positions[0].getX(), positions[0].getY())
        );

        totalLength += lines[0].getSegmentLength();

        for (int i = 1; i < positions.length; i++) {
            lines[i] = new Line(
                new Point(positions[i - 1].getX(), positions[i - 1].getY()),
                new Point(positions[i].getX(), positions[i].getY())
            );

            totalLength += lines[i].getSegmentLength();

            arcs[i] = Arc.arcLines(radius, lines[i - 1], lines[i]);

            arcMultiplier[i] = arcs[i].getVelocityAtPoint(0);

            totalLength += arcMultiplier[i];
        }

        State start = new State(0, 0);
        State end = new State(totalLength, 0);
        Constraints constraints = new Constraints(GeneralConfig.DT_MAX_VEL.getValue(), GeneralConfig.DT_MAX_ACCEL.getValue());



        
    }
}
