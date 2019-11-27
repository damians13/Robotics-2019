/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class _MecanumDrive extends Command {
 
    double joyX = 0;
    double joyY = 0;
	double rotation = 0;

	public _MecanumDrive() {
		requires(Robot.driveTrain);
	}
	
	@Override protected void initialize() {
		System.out.println("***Mecanum drive initialized.");
	}
	
	@Override
	protected void execute() {

		//joyX = Robot.m_oi.driverControllerAxisValue(RobotMap.Joy_X_Axis);
		//joyY = -Robot.m_oi.driverControllerAxisValue(RobotMap.Joy_Y_Axis);
		//rotation = Robot.m_oi.driverControllerAxisValue(RobotMap.Joy_Z_Axis);
		joyX = Robot.m_oi.driverControllerAxisValue(RobotMap.Xbox_Left_X_Axis);
		joyY = -Robot.m_oi.driverControllerAxisValue(RobotMap.Xbox_Left_Y_Axis);
		rotation = Robot.m_oi.driverControllerAxisValue(RobotMap.Xbox_Right_Trigger) - Robot.m_oi.driverControllerAxisValue(RobotMap.Xbox_Left_Trigger);

		Robot.driveTrain.mecanumDrive(joyX, joyY, rotation);
	}
	
	@Override 
	protected boolean isFinished() {
		return false;
	}
	
	@Override protected void end() {
		Robot.driveTrain.stop(); // Sets all motor controllers to 0
	}
	@Override protected void interrupted() {}
}