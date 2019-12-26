package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.I2C;
import frc.robot.Robot;
import frc.robot.commands.SensorsPeriodic;
import frc.robot.utilities.LiDAR;

public class Sensors extends Subsystem {

    private AHRS navx;
    private LiDAR lidar;

    private CANEncoder frontRightEncoder;
    private CANEncoder frontLeftEncoder;
    private CANEncoder backRightEncoder;
    private CANEncoder backLeftEncoder;

    public static enum _Encoder {
        FRONT_RIGHT, FRONT_LEFT, BACK_RIGHT, BACK_LEFT
    }

    public Sensors() {
        navx = new AHRS(SPI.Port.kMXP, (byte) 50);
        lidar = new LiDAR(I2C.Port.kOnboard);

        frontRightEncoder = new CANEncoder(Robot.driveTrain.frontRight);
        frontLeftEncoder = new CANEncoder(Robot.driveTrain.frontLeft);
        backRightEncoder = new CANEncoder(Robot.driveTrain.backRight);
        backLeftEncoder = new CANEncoder(Robot.driveTrain.backLeft);
    }

    public double getGyroX() {
        return navx.getRawGyroX();
    }

    public double getEncoderRotations(_Encoder encoder) {
        switch(encoder) {
            case FRONT_RIGHT:
                return frontRightEncoder.getPosition();
            case FRONT_LEFT:
                return frontLeftEncoder.getPosition();
            case BACK_RIGHT:
                return backRightEncoder.getPosition();
            case BACK_LEFT:
                return backLeftEncoder.getPosition();
            default:
                System.out.println("Problem getting encoder position.");
                return 0;
        }
    }

    public double getGyroY() {
        return navx.getRawGyroY();
    }

    public double getGyroZ() {
        return navx.getRawGyroZ();
    }

    public double getLidarDistance() {
        lidar.startMeasuring();
        double dist = lidar.getDistance();
        lidar.stopMeasuring();
        return dist;
    }

    public double getIrDistance() {
        return 0; // Make this work
    }

    @Override
    public void initDefaultCommand() {
      setDefaultCommand(new SensorsPeriodic());
    }

}