/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.Drive;

/**
 * Add your docs here.
 */
public class DriveTrainCAN extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static WPI_TalonSRX frontRight;
  public static Spark frontLeft;
  public static Spark backLeft;
  public static Spark backRight;

  public static SpeedControllerGroup left;
  public static SpeedControllerGroup right;

  public DifferentialDrive m_myRobot;

  public DriveTrainCAN() {
	  frontRight = new WPI_TalonSRX(10);
    frontLeft = new Spark(0);
    backLeft = new Spark(1);
    backRight = new Spark(2);

    left = new SpeedControllerGroup(frontLeft, backLeft);
    right = new SpeedControllerGroup(frontRight, backRight);
    
		frontLeft.setInverted(true);
		backLeft.setInverted(true);
		frontRight.setInverted(true);
		backRight.setInverted(true);
   
	  m_myRobot = new DifferentialDrive(left, right);

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
    System.out.println("stop() in DriveTrainCAN called unexpectadly.");
  }
  public void mecanumDrive(double a, double b, double c) {
    System.out.println("mecanumDrive() in DriveTrainCAN called unexpectadly.");
  }
}