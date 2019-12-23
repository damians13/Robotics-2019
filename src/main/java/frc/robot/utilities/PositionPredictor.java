package frc.robot.utilities;

import frc.robot.Robot;

public class PositionPredictor extends Thread {

    /** This class tracks the position of the robot on the field, relative to the back left corner
     * of the defending zone, like so:
     *  Y . . . .
     *  ^ . . . .
     *  ^ . . . .
     *  ^ . . . .
     *  0 > > > X
     * (rot stands for clockwise rotation in degrees)
     */

    private double xDist;
    private double yDist;
    private double totalRot;

    private int previousTimer;

    // Replace these with actual values from sensors, they should be updated every time that the
    // timer in Robot (int timeSinceEnable) is updated/incremented
    double inputX; // In meters per 1/50th of a second
    double inputY; // In meters per 1/50th of a second
    double inputRot; // In degrees per 1/50th of a second

    private double xDist1;
    private double xDist2;
    private double yDist1;
    private double yDist2;
    private double rot;

    public PositionPredictor() {
        previousTimer = Robot.timeSinceEnable;

        xDist = 0;
        yDist = 0;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) { // Loop through the code until the thread is interrupted
            if (Robot.timeSinceEnable == previousTimer + 1) {
                totalRot += inputRot;
                rot = bearingToRefAngle(totalRot);

                // Determine x and y components of cartesian movement vectors
                xDist1 = xComponent(inputX, rot); // This should use the forward axis, not sure if it's right
                xDist2 = xComponent(inputY, rot + 90);
                yDist1 = yComponent(inputX, rot);
                yDist2 = yComponent(inputY, rot + 90);

                // Add x and y components of cartesian movement vectors
                xDist += xDist1 + xDist2;
                yDist += yDist1 + yDist2;

            // Deal with any desynchronization issues
            } else if (Robot.timeSinceEnable > previousTimer + 1) {
                System.out.println("Uh oh, we skipped one!  Exiting PositionPredictor...");
                this.interrupt();
                break;
            } else if (Robot.timeSinceEnable == previousTimer) {
                // No time passed, this method was run faster than the FRC code (1/50th of a second)
                // Do nothing
            } else {
                System.out.println("Uh oh, did we go back in time?  previousTimer > timeSinceEnable!  Exiting PositionPredictor...");
                this.interrupt();
                break;
            }
        }
    }

    private double bearingToRefAngle(double angle) {
        // Angle arm is on an axis
        if ((angle % 360) == 0 || (angle % 360) == 90 || (angle % 360) == 180 || (angle % 360) == 270) {
            return angle % 360;
        } else if (angle % 360 < 90) { // 1st quadrant
            return 90 - (angle % 360);
        } else if (angle % 360 > 270) { // 2nd quadrant
            return 90 - (260 - (angle % 360));
        } else if (angle % 360 < 270 && angle % 360 > 180) { // 3rd quadrant
            return 270 - (angle % 360);
        } else if (angle % 360 < 180 && angle % 360 > 90) { // 4th quadrant
            return ((angle % 360) - 90);
        } else {
            System.out.println("Error in bearingToRefAngle() in PositionPredictor.java, this shouldn't be possible!");
            return 0;
        }
    }

    private double xComponent(double mag, double dir) {
        return mag * Math.cos(90 - dir);
    }

    private double yComponent(double mag, double dir) {
        return mag * Math.sin(90 - dir);
    }

    public double getXDist() {
        return xDist;
    }

    public double getYDist() {
        return yDist;
    }

    public double getRot() {
        return rot;
    }

}