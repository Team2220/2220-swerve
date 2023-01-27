package frc.twilight.swerve.vectors;

import edu.wpi.first.math.filter.SlewRateLimiter;
import frc.twilight.swerve.config.GeneralConfig;

public class DriveVector {
    // Forward/backward velocity m/s
    private double fwd;
    // Left/right velocity m/s
    private double str;
    // Rotational velocity deg/s
    private double rcw;

    SlewRateLimiter fwdLimiter = new SlewRateLimiter(GeneralConfig.DT_MAX_ACCEL);
    SlewRateLimiter strLimiter = new SlewRateLimiter(GeneralConfig.DT_MAX_ACCEL);
    SlewRateLimiter rcwLimiter = new SlewRateLimiter(GeneralConfig.DT_MAX_ROT_ACCEL);

    public DriveVector(double fwd, double str, double rcw) {
        this.fwd = fwd;
        this.str = str;
        this.rcw = rcw;
    }

    public double getFwd() {
        return fwd;
    }

    public double getStr() {
        return str;
    }

    public double getRcw() {
        return rcw;
    }

    public void setFwd(double fwd) {
        this.fwd = fwd;
    }

    public void setStr(double str) {
        this.str = str;
    }

    public void setRcw(double rcw) {
        this.rcw = rcw;
    }

    public DriveVector zeroDirection(double facing) {
        double fwdOut = (fwd * Math.cos(Math.toRadians(facing))) + (str * Math.sin(Math.toRadians(facing)));
        double strOut = (str * Math.cos(Math.toRadians(facing))) - (fwd * Math.sin(Math.toRadians(facing)));

        fwd = fwdOut;
        str = strOut;

        return this;
    }

    public DriveVector maxVel() {
        if (Math.abs(fwd) > GeneralConfig.DT_MAX_VEL) {
            double ratio = GeneralConfig.DT_MAX_VEL / Math.abs(fwd);

            fwd *= ratio;
            str *= ratio;
        }

        if (Math.abs(str) > GeneralConfig.DT_MAX_VEL) {
            double ratio = GeneralConfig.DT_MAX_VEL / Math.abs(str);

            fwd *= ratio;
            str *= ratio;
        }

        if (rcw > GeneralConfig.DT_MAX_ROT_VEL) {
            rcw = GeneralConfig.DT_MAX_ROT_VEL;
        } else if (rcw < -GeneralConfig.DT_MAX_ROT_VEL) {
            rcw = -GeneralConfig.DT_MAX_ROT_VEL;
        }

        return this;
    }

    public DriveVector maxVel(double maxVel, double maxRotVel) {
        if (Math.abs(fwd) > maxVel) {
            double ratio = maxVel / Math.abs(fwd);

            fwd *= ratio;
            str *= ratio;
        }

        if (Math.abs(str) > maxVel) {
            double ratio = maxVel / Math.abs(str);

            fwd *= ratio;
            str *= ratio;
        }

        if (rcw > maxRotVel) {
            rcw = maxRotVel;
        } else if (rcw < -maxRotVel) {
            rcw = -maxRotVel;
        }

        return this;
    }

    public DriveVector maxAccel() {
        fwd = fwdLimiter.calculate(fwd);
        str = strLimiter.calculate(str);
        rcw = rcwLimiter.calculate(rcw);

        return this;
    }
}
