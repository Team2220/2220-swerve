package frc.twilight.swerve;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.twilight.helpfulThings.Angles;
import frc.twilight.swerve.config.CANidConfig;
import frc.twilight.swerve.config.GeneralConfig;
import frc.twilight.swerve.config.ModuleConfig;
// import frc.twilight.swerve.devices.Gyro;
import frc.twilight.swerve.vectors.DriveVector;
import frc.twilight.swerve.vectors.Position;
import frc.twilight.swerve.vectors.VectorFactory;
import frc.twilight.swerve.vectors.WheelVector;

public class SwerveDrive {
    private final SwerveModule frontLeft;
    private final SwerveModule frontRight;
    private final SwerveModule backLeft;
    private final SwerveModule backRight;

    // private final Gyro gyro = new Gyro();

    private double odoLastCheck = -1;
    private Position odoPosition;

    public SwerveDrive() {
        frontLeft = new SwerveModule(CANidConfig.DT_FL_DM, CANidConfig.DT_FL_SM, CANidConfig.DT_FL_SE, ModuleConfig.DT_FL_SE_OFFSET);
        frontRight = new SwerveModule(CANidConfig.DT_FR_DM, CANidConfig.DT_FR_SM, CANidConfig.DT_FR_SE, ModuleConfig.DT_FR_SE_OFFSET);
        backLeft = new SwerveModule(CANidConfig.DT_BL_DM, CANidConfig.DT_BL_SM, CANidConfig.DT_BL_SE, ModuleConfig.DT_BL_SE_OFFSET);
        backRight = new SwerveModule(CANidConfig.DT_BR_DM, CANidConfig.DT_BR_SM, CANidConfig.DT_BR_SE, ModuleConfig.DT_BR_SE_OFFSET);

        odoLastCheck = System.currentTimeMillis();

        // gyro.setPosition(GeneralConfig.DT_START_GYRO);

        odoPosition = new Position(GeneralConfig.DT_START_X, GeneralConfig.DT_START_Y, GeneralConfig.DT_START_GYRO);

        Shuffleboard.getTab("Swerve").addNumber("FL Enc Angle", () -> frontLeft.getEncoderAngle()).withPosition(0, 0);
        Shuffleboard.getTab("Swerve").addNumber("FR Enc Angle", () -> frontRight.getEncoderAngle()).withPosition(0, 1);
        Shuffleboard.getTab("Swerve").addNumber("BL Enc Angle", () -> backLeft.getEncoderAngle()).withPosition(0, 2);
        Shuffleboard.getTab("Swerve").addNumber("BR Enc Angle", () -> backRight.getEncoderAngle()).withPosition(0, 3);

        Shuffleboard.getTab("Swerve").addNumber("FL Angle", () -> frontLeft.get().getAngle()).withPosition(1, 0);
        Shuffleboard.getTab("Swerve").addNumber("FR Angle", () -> frontRight.get().getAngle()).withPosition(1, 1);
        Shuffleboard.getTab("Swerve").addNumber("BL Angle", () -> backLeft.get().getAngle()).withPosition(1, 2);
        Shuffleboard.getTab("Swerve").addNumber("BR Angle", () -> backRight.get().getAngle()).withPosition(1, 3);

        Shuffleboard.getTab("Swerve").addNumber("FL Vel", () -> frontLeft.get().getVelocity()).withPosition(2, 0);
        Shuffleboard.getTab("Swerve").addNumber("FR Vel", () -> frontRight.get().getVelocity()).withPosition(2, 1);
        Shuffleboard.getTab("Swerve").addNumber("BL Vel", () -> backLeft.get().getVelocity()).withPosition(2, 2);
        Shuffleboard.getTab("Swerve").addNumber("BR Vel", () -> backRight.get().getVelocity()).withPosition(2, 3);

    }

    public void setDrive(DriveVector vector) {
        // vector.zeroDirection(gyro.getAngle());
        
        // vector.controlVel();
        // vector.controlAccel(getDrive());

        WheelVector[] wheelVectors = VectorFactory.wheelVectorsFromDriveVector(vector);

        wheelVectors[0] = Angles.optimizeWheel(frontRight.get(), wheelVectors[0]);
        wheelVectors[1] = Angles.optimizeWheel(frontLeft.get(), wheelVectors[1]);
        wheelVectors[2] = Angles.optimizeWheel(backRight.get(), wheelVectors[2]);
        wheelVectors[3] = Angles.optimizeWheel(backLeft.get(), wheelVectors[3]);

        frontRight.set(wheelVectors[0]);
        frontLeft.set(wheelVectors[1]);
        backRight.set(wheelVectors[2]);
        backLeft.set(wheelVectors[3]);
    }

    public DriveVector getDrive() {
        DriveVector out = VectorFactory.driveVectorFromWheelVectors(frontRight.get(), frontLeft.get(), backRight.get(), backLeft.get());
        // out.zeroDirection(-gyro.getAngle());

        return out;
    }

    public void setOdo(double x, double y, double angle) {
        odoPosition = new Position(x, y, angle);
    }

    public void updateOdo() {
        double time = System.currentTimeMillis();
        double dt = (time - odoLastCheck) / 1000;
        odoLastCheck = time;

        DriveVector drive = getDrive();
        double x = odoPosition.getX() + drive.getFwd() * dt;
        double y = odoPosition.getY() + drive.getStr() * dt;
        // double angle = gyro.getAngle();

        odoPosition = new Position(x, y, 0);
    }

    public Position getOdo() {
        return odoPosition;
    }
}