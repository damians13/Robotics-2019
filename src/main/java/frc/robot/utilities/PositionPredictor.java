package frc.robot.utilities;

import frc.robot.Robot;
import frc.robot.subsystems.Sensors;

public class PositionPredictor extends Thread {

    /** This class tracks the position of the robot on the field, relative to the back left corner
     * of the defending zone, like so:
     *  Y . . . .
     *  ^ . . . .
     *  ^ . . . .
     *  ^ . . . .
     *  0 > > > X
     * (rot stands for rotation as a true bearing)
     */

    private double xDist;
    private double yDist;
    private double totalRot;

    private int previousTimer;

    private _Vector frontRightVector;
    private _Vector frontLeftVector;
    private _Vector backRightVector;
    private _Vector backLeftVector;
    private _Vector resultantVector;

    private double prevFRdistance; // Prev stands for previous
    private double prevFLdistance;
    private double prevBRdistance;
    private double prevBLdistance;
    private double FRdistance;
    private double FLdistance;
    private double BRdistance;
    private double BLdistance;

    public PositionPredictor() {
        previousTimer = Robot.timeSinceEnable;

        xDist = 0;
        yDist = 0;

        prevFRdistance = 0;
        prevFLdistance = 0;
        prevBRdistance = 0;
        prevBLdistance = 0;
        FRdistance = 0;
        FLdistance = 0;
        BRdistance = 0;
        BLdistance = 0;

        frontRightVector = new _Vector(0, 0);
        frontLeftVector = new _Vector(0, 0);
        backRightVector = new _Vector(0, 0);
        backLeftVector = new _Vector(0, 0);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) { // Loop through the code until the thread is interrupted
            if (Robot.timeSinceEnable == previousTimer + 1) {
                totalRot += Robot.sensors.getGyroZ();

                FRdistance = rotationsToDistance(Robot.sensors.getEncoderRotations(Sensors._Encoder.FRONT_RIGHT));
                FLdistance = rotationsToDistance(Robot.sensors.getEncoderRotations(Sensors._Encoder.FRONT_LEFT));
                BRdistance = rotationsToDistance(Robot.sensors.getEncoderRotations(Sensors._Encoder.BACK_RIGHT));
                BLdistance = rotationsToDistance(Robot.sensors.getEncoderRotations(Sensors._Encoder.BACK_LEFT));

                frontRightVector.setBearingAndMag(45 + totalRot, FRdistance - prevFRdistance);
                frontLeftVector.setBearingAndMag(135 + totalRot, FLdistance - prevFLdistance);
                backRightVector.setBearingAndMag(45 + totalRot, BRdistance - prevBRdistance);
                backLeftVector.setBearingAndMag(135 + totalRot, BLdistance - prevBLdistance);

                resultantVector = MiscUtils.addVectors(frontRightVector, frontLeftVector, backRightVector, backLeftVector);

                xDist += resultantVector.getX();
                yDist += resultantVector.getY();

                prevFRdistance = FRdistance;
                prevFLdistance = FLdistance;
                prevBRdistance = BRdistance;
                prevBLdistance = BLdistance;

            // Deal with any desynchronization issues
            } else if (Robot.timeSinceEnable > previousTimer + 1) {
                System.out.println("Uh oh, we skipped one!  Exiting PositionPredictor...");
                interrupt();
                break;
            } else if (Robot.timeSinceEnable == previousTimer) {
                // No time passed, method was run faster than the FRC code (1/50th of a second)
                // Do nothing
            } else {
                System.out.println("Uh oh, did we go back in time?  previousTimer > timeSinceEnable!  Exiting PositionPredictor...");
                interrupt();
                break;
            }
        }
    }

    // Convert wheel rotations to distance (in inches)
    private double rotationsToDistance(double rotations) {
        return rotations * 6 * Math.PI; // Return rotations * circumference (6in * PI)
    }

    public double getXDist() {
        return xDist;
    }

    public double getYDist() {
        return yDist;
    }

    public double getRot() {
        return totalRot;
    }

}