package frc.twilight.limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.twilight.swerve.vectors.Position;

public class Limelight extends SubsystemBase {
    private static NetworkTableInstance networkTables = NetworkTableInstance.getDefault();
    private final NetworkTable limelightTable;

    public Limelight(String hostname) {
        limelightTable = networkTables.getTable(hostname);
    }

    public PositionUpdate getPose() {
        PositionUpdate out = new PositionUpdate();
        double[] data = limelightTable.getEntry("botpose_wpiblue").getDoubleArray(new double[7]);

        if (data[0] == 0)
            return null;

        out.latency = data[6];
        out.position = new Position(data[0], data[1], data[5]);

        return out;
    }

    public class PositionUpdate {
        public double latency;
        public Position position;
    }
}
