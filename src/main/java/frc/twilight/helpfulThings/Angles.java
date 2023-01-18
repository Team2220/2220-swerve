/**
 * A class containing helpful methods for working with angles.
 *
 * @author Ryan Hendrickson
 * @since 2023
 */
package frc.twilight.helpfulThings;

import frc.twilight.swerve.vectors.WheelVector;

public class Angles {

    /**
     * Offsets the given angle by the specified amount.
     * 
     * @param angle The angle to be offset.
     * @param offset The amount to offset the angle by.
     * @return The offset angle.
     */
    public static double offsetAngle(double angle, double offset) {
        double out = angle - offset;

        // out = out % 360;
        // if (out < 0)
        //     out += 360;
        // This code is commented out, it's purpose is to ensure the output angle is between 0-360
        // if you want to use it you can uncomment it.

        return out;
    }

    /**
     * Optimizes the given "set" WheelVector based on the "current" WheelVector.
     * Modifies the angle and velocity of the "set" WheelVector to be as close as possible to the "current" WheelVector, while staying within the range of 0-360 degrees.
     *
     * @param current The current WheelVector.
     * @param set The set WheelVector to be optimized.
     * @return The optimized set WheelVector.
     */
    public static WheelVector optimizeWheel(WheelVector current, WheelVector set) {
        double angle = current.getAngle();
        double angleNew = set.getAngle();

        int angleRevo = (int)(angle / 360);
        
        angle %= 360;
        angleNew %= 360;

        double velNew = set.getVelocity();

        double change = angle - angleNew;

        if (Math.abs(change) > 90 && Math.abs(change) < 270) {
            angleNew += 180;
            velNew = -velNew;
        } else if (change > 270) {
            angleNew += 360;
        } else if (change < -270) {
            angleNew -= 360;
        }

        angleNew += 360 * angleRevo;

        return new WheelVector(velNew, angleNew);
    }
}
