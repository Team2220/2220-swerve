package frc.twilight.helpfulThings;

import frc.twilight.swerve.vectors.WheelVector;

public class Angles {
    public static double offsetAngle(double angle, double offset) {
        double out = angle - offset;

        // out = out % 360;
        
        // if (out < 0)
        //     out += 360;

        return out;
    }

    public static WheelVector optimizeWheel(WheelVector current, WheelVector set) {
        WheelVector out = new WheelVector(set.getVelocity(), set.getAngle());

        double currentAngle = current.getAngle();
        double setAngle = set.getAngle();

        double angleDiff = setAngle - currentAngle;

        if (angleDiff > 180) {
            out.setAngle(setAngle - 180);
            out.setVelocity(-set.getVelocity());
        } else if (angleDiff < -180) {
            out.setAngle(setAngle + 180);
            out.setVelocity(-set.getVelocity());
        }

        return out;
    }
}
