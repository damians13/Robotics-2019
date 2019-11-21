/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.Drive;

/**
 * Add your docs here.hh
 */
public class DriveTrainSpark extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.



	public static Spark left1 = new Spark(2);
	public static Spark left2 = new Spark(3);
	public static Spark right1 = new Spark(0);
	public static Spark right2 = new Spark(1);

	public static SpeedControllerGroup left = new SpeedControllerGroup(left1, left2);
	public static SpeedControllerGroup right = new SpeedControllerGroup(right1, right2);


	public DifferentialDrive m_myRobot;

	public DriveTrainSpark() {
		m_myRobot = new DifferentialDrive(left, right);
		left1.setInverted(true);
		left2.setInverted(true);
		right1.setInverted(true);
		right2.setInverted(true);
	
	    m_myRobot.setSafetyEnabled(false);
	}
	
	@Override
	public void initDefaultCommand() {
	    setDefaultCommand(new Drive());
	}
	
	public void arcadeDrive(double speed, double rotation, boolean squared) {
		m_myRobot.arcadeDrive(speed, rotation, squared);
	}
	
	public void arcadeDrive(double speed, double rotation) {
	    this.arcadeDrive(speed, rotation, false);
	}

	// Disregard these
  
	public void stop() {
	  System.out.println("stop() in DriveTrainSpark called unexpectadly.");
	}
	public void mecanumDrive(double a, double b, double c) {
	  System.out.println("mecanumDrive() in DriveTrainSpark called unexpectadly.");
	}
}