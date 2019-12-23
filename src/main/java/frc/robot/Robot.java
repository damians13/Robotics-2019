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
import frc.robot.utilities.PositionPredictor;
import frc.robot.subsystems.Pneumatics;

public class Robot extends TimedRobot {
	
	public static OI m_oi;
	public static DriveTrainMecanum driveTrain;
	public static Sensors sensors;
	public static Pneumatics pneumatics;
	public static PositionPredictor positionPredictor;

	public static boolean solenoidEnabled;
	public static int timeSinceEnable; // Tracked in the number of times the code has run (50 per second)

	@Override
	public void robotInit() {
		driveTrain = new DriveTrainMecanum();
		sensors = new Sensors();
		pneumatics = new Pneumatics();
		m_oi = new OI();
		positionPredictor = new PositionPredictor();

		positionPredictor.start();
	}

	@Override
	public void robotPeriodic() {
		timeSinceEnable++;
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