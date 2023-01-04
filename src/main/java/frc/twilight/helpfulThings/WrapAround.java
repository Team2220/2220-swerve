package frc.twilight.helpfulThings;

public class WrapAround {
    public static double offsetAngle(double angle, double offset) {
        double out = angle - offset;

        out = out % 360;
        
        if (out < 0)
            out += 360;

        return out;
    }
}
