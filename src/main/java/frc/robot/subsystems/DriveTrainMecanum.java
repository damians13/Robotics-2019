package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands._MecanumDrive;
import frc.robot.utilities.PID;

public class DriveTrainMecanum extends Subsystem {
    // https://www.desmos.com/calculator/mjjpxtbf11
    public Spark frontLeft;
    public Spark frontRight;
    public Spark backLeft;
    public Spark backRight;

    double joyX = 0;
    double joyY = 0;
    double rotation = 0;
    double angle = 0;
    double frontLeftPower = 0;
    double frontRightPower = 0;
    double backLeftPower = 0;
    double backRightPower = 0;
    double totalGyroDifference = 0;

    //                           kP kI kD target
    private PID gyroPID = new PID(RobotMap.MeasuredMaxTurnSpeed / 360, 0, 0, 0);
    public _MecanumDrive drive;

    public DriveTrainMecanum() {
        frontLeft = new Spark(0);
        frontRight = new Spark(1);
        backLeft = new Spark(2);
        backRight = new Spark(3);

        frontRight.setInverted(true);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new _MecanumDrive());
    }

    public void mecanumDrive(double joystickX, double joystickY, double rotation) {
        // Deadband inputs
        if (Math.abs(rotation) < RobotMap.ROTATION_DEADBAND)
            rotation = 0;
        if (Math.abs(joystickX) < RobotMap.JOY_DEADBAND)
            joystickX = 0;
        if (Math.abs(joystickY) < RobotMap.JOY_DEADBAND)
            joystickY = 0;

        if (Math.abs(rotation) >= RobotMap.GYRO_TOGGLE) {
            totalGyroDifference = 0;
            gyroPID.resetTarget(0);
        } else {
            totalGyroDifference += Robot.sensors.getGyroZ() / 50;

            if (totalGyroDifference < RobotMap.GYRO_DEADBAND) {
                rotation = 0;
            } else {
                rotation = -totalGyroDifference;
            }

            if (Math.abs(rotation) > RobotMap.MAX_TURN_SPEED)
                rotation *= RobotMap.MAX_TURN_SPEED / Math.abs(rotation);

            rotation *= gyroPID.getOutput(Robot.sensors.getGyroZ());
        }
        //System.out.println("\nTotal Gyro Difference: " + totalGyroDifference + "\nRotation: " + rotation + "\n");
        
        /*
        Before setting rotation to totalGyroDifference, multiply it by a
        ratio of how many degrees the robot turns per 0.02 second interval
        */

        // Cap rotation to RobotMap value
        if (Math.abs(rotation) > RobotMap.MAX_TURN_SPEED)
            rotation *= RobotMap.MAX_TURN_SPEED / Math.abs(rotation);

        // Calculate speed for each wheel
        frontRightPower = joystickY - joystickX - rotation;
        frontLeftPower = joystickY + joystickX + rotation;
        backLeftPower = joystickY - joystickX + rotation;
        backRightPower = joystickY + joystickX - rotation;

        // Cap speeds to RobotMap values
        for (double speed : new double[] { frontLeftPower, frontRightPower, backLeftPower, backRightPower }) {
            if (speed > RobotMap.MAX_ROBOT_SPEED) {
                speed = RobotMap.MAX_ROBOT_SPEED;
            } else if (speed < RobotMap.MIN_ROBOT_SPEED) {
                speed = RobotMap.MIN_ROBOT_SPEED;
            }
        }
        if (Math.abs(frontLeftPower) > RobotMap.MAX_ROBOT_SPEED)
            frontLeftPower *= RobotMap.MAX_ROBOT_SPEED / Math.abs(frontLeftPower);
        if (Math.abs(frontRightPower) > RobotMap.MAX_ROBOT_SPEED)
            frontRightPower *= RobotMap.MAX_ROBOT_SPEED / Math.abs(frontRightPower);
        if (Math.abs(backLeftPower) > RobotMap.MAX_ROBOT_SPEED)
            backLeftPower *= RobotMap.MAX_ROBOT_SPEED / Math.abs(backLeftPower);
        if (Math.abs(backRightPower) > RobotMap.MAX_ROBOT_SPEED)
            backRightPower *= RobotMap.MAX_ROBOT_SPEED / Math.abs(backRightPower);

        //System.out.println("X: " + joystickX + "\nY: " + joystickY + "\nZ: " + rotation);
        //System.out.println("fleft: " + frontLeftPower + "\nfright: " + frontRightPower + "\nbleft: " + backLeftPower + "\nbright: " + backRightPower);
        // Power the motors
        frontLeft.set(frontLeftPower);
        frontRight.set(frontRightPower);
        backLeft.set(backLeftPower);
        backRight.set(backRightPower);
    }

    public void stop() {
        frontLeft.set(0);
        frontRight.set(0);
        backLeft.set(0);
        backRight.set(0);
    }

    public void arcadeDrive(double a, double b) {
        System.out.println("Unexpected input from Drive.java!!");
    }
}