package frc.twilight.swerve.vectors;

import frc.twilight.swerve.config.GeneralConfig;

public class VectorFactory {
    // Output wheel vectors in order of FR, FL, BR, BL
    public static WheelVector[] wheelVectorsFromDriveVector(DriveVector in) {
        WheelVector[] out = new WheelVector[4];
        out[0] = new WheelVector(0, 0);
        out[1] = new WheelVector(0, 0);
        out[2] = new WheelVector(0, 0);
        out[3] = new WheelVector(0, 0);

        double fwd = in.getFwd();
        double str = in.getStr();
        double rcw = in.getRcw();

        double a = str - rcw * (GeneralConfig.DT_LENGTH / GeneralConfig.DT_DIAMETER);
        double b = str + rcw * (GeneralConfig.DT_LENGTH / GeneralConfig.DT_DIAMETER);
        double c = fwd - rcw * (GeneralConfig.DT_WIDTH / GeneralConfig.DT_DIAMETER);
        double d = fwd + rcw * (GeneralConfig.DT_WIDTH / GeneralConfig.DT_DIAMETER);

        // Front Right
        out[0].setVelocity(Math.sqrt(b * b + c * c));
        out[0].setAngle(Math.atan2(b, c) * 180 / Math.PI);

        // Front Left
        out[1].setVelocity(Math.sqrt(b * b + d * d));
        out[1].setAngle(Math.atan2(b, d) * 180 / Math.PI);

        // Back Right
        out[2].setVelocity(Math.sqrt(a * a + c * c));
        out[2].setAngle(Math.atan2(a, c) * 180 / Math.PI);

        // Back Left
        out[3].setVelocity(Math.sqrt(a * a + d * d));
        out[3].setAngle(Math.atan2(a, d) * 180 / Math.PI);

        return out;
    }

    // No clue if this is actually gonna work lol, we will see
    public static DriveVector driveVectorFromWheelVectors(WheelVector FR, WheelVector FL, WheelVector BR, WheelVector BL) {
        double fwd = 
            (
                FR.getVelocity() 
                * Math.sin(
                    FR.getAngle() 
                    * Math.PI / 180
                )

                + FL.getVelocity() 
                * Math.sin(
                    FL.getAngle() 
                    * Math.PI / 180
                ) 

                + BR.getVelocity() 
                * Math.sin(
                    BR.getAngle() 
                    * Math.PI / 180
                ) 

                + BL.getVelocity() 
                * Math.sin(
                    BL.getAngle() 
                    * Math.PI / 180
                )
            ) / 4;

        double str = 
            (
                FR.getVelocity() 
                * Math.cos(
                    FR.getAngle() 
                    * Math.PI / 180
                )

                + FL.getVelocity() 
                * Math.cos(
                    FL.getAngle() 
                    * Math.PI / 180
                ) 

                + BR.getVelocity() 
                * Math.cos(
                    BR.getAngle() 
                    * Math.PI / 180
                ) 

                + BL.getVelocity() 
                * Math.cos(
                    BL.getAngle() 
                    * Math.PI / 180
                )
            ) / 4;

        double rcw = 
            (
                FR.getVelocity() 
                * Math.sin(
                    FR.getAngle() 
                    * Math.PI / 180
                ) 
                
                - FL.getVelocity() 
                * Math.sin(
                    FL.getAngle() 
                    * Math.PI / 180
                ) 
                
                + BR.getVelocity() 
                * Math.sin(
                    BR.getAngle() 
                    * Math.PI / 180
                ) 
                
                - BL.getVelocity() 
                * Math.sin(
                    BL.getAngle() 
                    * Math.PI / 180
                )    
            ) / (4 * GeneralConfig.DT_LENGTH / GeneralConfig.DT_DIAMETER);

        return new DriveVector(fwd, str, rcw);
    }
}
