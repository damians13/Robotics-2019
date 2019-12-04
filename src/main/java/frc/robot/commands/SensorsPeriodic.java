package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SensorsPeriodic extends Command {

    public SensorsPeriodic() {
        requires(Robot.sensors);
    }

    @Override
    protected void execute() {
        System.out.println(Robot.sensors.getLidarDistance());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}