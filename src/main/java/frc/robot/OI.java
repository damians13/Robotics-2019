package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	//Joystick driverController = new Joystick(3);
	Joystick driverController = new Joystick(0);
	
	public Button driverA = new JoystickButton(driverController, RobotMap.Xbox_A_Button);
	public Button driverB = new JoystickButton(driverController, RobotMap.Xbox_B_Button);
	public Button driverX = new JoystickButton(driverController, RobotMap.Xbox_X_Button);
	public Button driverY = new JoystickButton(driverController, RobotMap.Xbox_Y_Button);
	public Button driverLeftBumper = new JoystickButton(driverController, RobotMap.Xbox_Left_Bumper);
	public Button driverRightBumper = new JoystickButton(driverController, RobotMap.Xbox_Right_Bumper);
	public Button driverBack = new JoystickButton(driverController, RobotMap.Xbox_Back_Button);
	public Button driverStart = new JoystickButton(driverController, RobotMap.Xbox_Start_Button);
	
	public OI() {
		// Button commands go in here
	}
	
	public double driverControllerAxisValue(int axis) {
		return driverController.getRawAxis(axis);
	}
}
