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

public class Drive extends Command {
 	
	double rightTrigger = 0;
	double leftTrigger = 0;
	double joystickInput = 0;
	double speed = 0;
	double rotation = 0;
	double rightTurn = 0;
	double leftTurn = 0;

	public Drive() {
		requires(Robot.driveTrain);
	}
	
	@Override protected void initialize() {}
	
	@Override
	protected void execute() {
		rightTrigger = Robot.m_oi.driverControllerAxisValue(RobotMap.Xbox_Right_Trigger);
		leftTrigger = Robot.m_oi.driverControllerAxisValue(RobotMap.Xbox_Left_Trigger);
		joystickInput = Robot.m_oi.driverControllerAxisValue(RobotMap.Xbox_Right_Y_Axis);
		
		speed = sineDrive(joystickInput, RobotMap.JoystickDeadzone, RobotMap.SPEED_SHIFT_CUTOFF);
		rightTurn = sineDrive(rightTrigger, RobotMap.TriggerDeadzone, RobotMap.SPEED_SHIFT_CUTOFF);
		leftTurn = sineDrive(leftTrigger, RobotMap.TriggerDeadzone, RobotMap.SPEED_SHIFT_CUTOFF);
		rotation = leftTurn - rightTurn; // Not sure about this, test this first

		if (speed > RobotMap.MAX_ROBOT_SPEED) {
			speed = RobotMap.MAX_ROBOT_SPEED;
		} else if (speed < RobotMap.MIN_ROBOT_SPEED) {
			speed = RobotMap.MIN_ROBOT_SPEED;
		}
		
		if (rotation > RobotMap.MAX_TURN_SPEED) {
			rotation = RobotMap.MAX_TURN_SPEED;
		} else if (rotation < -RobotMap.MAX_TURN_SPEED) {
			rotation = -RobotMap.MAX_TURN_SPEED;
		}
		
		Robot.driveTrain.arcadeDrive(speed, rotation);
	}
	
	double sineDrive(double _joystickInput, double _deadband, double _speedShiftCutoff) {
		
		double speed = 0;
		
		if (Math.abs(_joystickInput) < _deadband) {
			return 0;
		}

		speed = Math.sin(Math.abs(_joystickInput / 2) * Math.PI);
		if (Math.abs(_joystickInput) < _speedShiftCutoff) {
			speed = Math.pow(speed, 2);
		}
		
		if (_joystickInput < 0) {
			speed *= -1;
		}
		
		return speed;
	}
	
	// Pronounce "Israel" like "Is-rai-el"
	
	@Override 
	protected boolean isFinished() {
		return false;
	}
	
	
	@Override protected void end() {}
	@Override protected void interrupted() {}
	
}
