package frc.twilight.swerve.vectors;

import frc.twilight.swerve.config.GeneralConfig;

public class DriveVector {
    // Forward/backward velocity m/s
    private double fwd;
    // Left/right velocity m/s
    private double str;
    // Rotational velocity deg/s
    private double rcw;

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

    public void zeroDirection(double facing) {
        double fwdOut = (fwd * Math.cos(Math.toRadians(facing))) + (str * Math.sin(Math.toRadians(facing)));
        double strOut = (str * Math.cos(Math.toRadians(facing))) - (fwd * Math.sin(Math.toRadians(facing)));

        fwd = fwdOut;
        str = strOut;
    }

    public void controlVel() {
        if (GeneralConfig.DT_VEL_CONTROL_ENABLE) {
            if (fwd > GeneralConfig.DT_MAX_VEL) {
                fwd = GeneralConfig.DT_MAX_VEL;
            } else if (fwd < -GeneralConfig.DT_MAX_VEL) {
                fwd = -GeneralConfig.DT_MAX_VEL;
            }

            if (str > GeneralConfig.DT_MAX_VEL) {
                str = GeneralConfig.DT_MAX_VEL;
            } else if (str < -GeneralConfig.DT_MAX_VEL) {
                str = -GeneralConfig.DT_MAX_VEL;
            }
        }

        if (GeneralConfig.DT_ROT_VEL_CONTROL_ENABLE) {
            if (rcw > GeneralConfig.DT_MAX_ROT_VEL) {
                rcw = GeneralConfig.DT_MAX_ROT_VEL;
            } else if (rcw < -GeneralConfig.DT_MAX_ROT_VEL) {
                rcw = -GeneralConfig.DT_MAX_ROT_VEL;
            }
        }
    }

    public void controlAccel(DriveVector current) {
        if (GeneralConfig.DT_ACCEL_CONTROL_ENABLE) {
            double fwdDiff = fwd - current.getFwd() / 0.02;
            double strDiff = str - current.getStr() / 0.02;

            if (fwdDiff > GeneralConfig.DT_MAX_ACCEL) {
                fwd = current.getFwd() + GeneralConfig.DT_MAX_ACCEL;
            } else if (fwdDiff < -GeneralConfig.DT_MAX_ACCEL) {
                fwd = current.getFwd() - GeneralConfig.DT_MAX_ACCEL;
            }

            if (strDiff > GeneralConfig.DT_MAX_ACCEL) {
                str = current.getStr() + GeneralConfig.DT_MAX_ACCEL;
            } else if (strDiff < -GeneralConfig.DT_MAX_ACCEL) {
                str = current.getStr() - GeneralConfig.DT_MAX_ACCEL;
            }
        }

        if (GeneralConfig.DT_ROT_ACCEL_CONTROL_ENABLE) {
            double rcwDiff = rcw - current.getRcw();

            if (rcwDiff > GeneralConfig.DT_MAX_ROT_ACCEL) {
                rcw = current.getRcw() + GeneralConfig.DT_MAX_ROT_ACCEL;
            } else if (rcwDiff < -GeneralConfig.DT_MAX_ROT_ACCEL) {
                rcw = current.getRcw() - GeneralConfig.DT_MAX_ROT_ACCEL;
            }
        }
    }
}
