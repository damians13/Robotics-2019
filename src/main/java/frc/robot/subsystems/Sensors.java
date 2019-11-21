package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.SensorsPeriodic;

public class Sensors extends Subsystem {

    private AHRS navx;

    public Sensors() {
        navx = new AHRS(SPI.Port.kMXP, (byte) 50);
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

    @Override
    public void initDefaultCommand() {
      setDefaultCommand(new SensorsPeriodic());
    }

}