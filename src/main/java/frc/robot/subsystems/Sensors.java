package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.I2C;
import frc.robot.commands.SensorsPeriodic;
import frc.robot.utilities.LiDAR;

public class Sensors extends Subsystem {

    private AHRS navx;
    private LiDAR lidar;

    public Sensors() {
        navx = new AHRS(SPI.Port.kMXP, (byte) 50);
        lidar = new LiDAR(I2C.Port.kOnboard);
    }

    public double getGyroX() {
        return navx.getRawGyroX();
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

    public double getIRDistance() {
        return 0; // Make this work
    }

    @Override
    public void initDefaultCommand() {
      setDefaultCommand(new SensorsPeriodic());
    }

}