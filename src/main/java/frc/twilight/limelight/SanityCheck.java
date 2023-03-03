package frc.twilight.limelight;

import java.util.ArrayList;
import frc.twilight.helpfulThings.CurrentTime;
import frc.twilight.limelight.Limelight.PositionUpdate;
import frc.twilight.swerve.subsystems.Swerve;
import frc.twilight.swerve.vectors.DriveVector;
import frc.twilight.swerve.vectors.Position;

public class SanityCheck {
  private final int TARGET_POSE_UPDATES = 5;
  private final double STABILITY_TOLERANCE = 0.2;

  private ArrayList<Update> updates = new ArrayList<>();

  private Limelight limelight;
  private Swerve swerve;

  public SanityCheck(Limelight limelight, Swerve swerve) {
    this.limelight = limelight;
    this.swerve = swerve;
  }

  public boolean isStable() {
    if (updates.size() < TARGET_POSE_UPDATES) return false;

    for (int i = 1; i < updates.size(); i++) {
      Update start = updates.get(i - 1);
      Update end = updates.get(i);

      DriveVector drive = DriveVector.average(start.reportedDrive, end.reportedDrive);

      double timeDiff =
          (end.time - end.reportedPos.latency) - (start.time - start.reportedPos.latency);
      timeDiff /= 1000;

      // Check X
      double xDisplacement = end.reportedPos.position.getX() - start.reportedPos.position.getX();
      xDisplacement *= timeDiff;

      if (Math.abs(xDisplacement - drive.getStr()) > STABILITY_TOLERANCE) return false;

      // Check Y
      double yDisplacement = end.reportedPos.position.getY() - start.reportedPos.position.getY();
      yDisplacement *= timeDiff;

      if (Math.abs(yDisplacement - drive.getFwd()) > STABILITY_TOLERANCE) return false;
    }

    // All good, yay!
    return true;
  }

  public boolean updateLists() {
    Update out = new Update();

    out.time = CurrentTime.getTimeMillis();
    out.reportedPos = limelight.getPose();

        if (out.reportedPos == null) {
            updates.clear();
            return false;
        }

    out.reportedDrive = swerve.getDrive();

    updates.add(out);

    if (updates.size() > TARGET_POSE_UPDATES) updates.remove(0);

    return true;
  }

  public Position getAdjustedPosition() {
    PositionUpdate current = limelight.getPose();
    DriveVector currentDrive = swerve.getDrive();
    double latency = current.latency / 1000;

    current.position.add(currentDrive, latency);

    return current.position;
  }

  private class Update {
    public DriveVector reportedDrive;
    public PositionUpdate reportedPos;
    public long time;
  }
}
