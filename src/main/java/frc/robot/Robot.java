/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.DriveTrainMecanum;
import frc.robot.subsystems.Sensors;
import frc.robot.subsystems.Pneumatics;

public class Robot extends TimedRobot {
	
	public static OI m_oi;
	public static DriveTrainMecanum driveTrain;
	public static Sensors sensors;
	public static Pneumatics pneumatics;

	public boolean solenoidEnabled;

	@Override
	public void robotInit() {
		m_oi = new OI();
		driveTrain = new DriveTrainMecanum();
		sensors = new Sensors();
		pneumatics = new Pneumatics();
	}

	@Override
	public void robotPeriodic() {
		
	}
	
	@Override
	public void teleopInit() {
		
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}