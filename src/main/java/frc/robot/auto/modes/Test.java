package frc.robot.auto.modes;

import frc.robot.auto.AutoModeBase;

public class Test implements AutoModeBase {

    private double length;
    private boolean isFinished;
    private double iteration;

    public Test(double length) {
        this.length = length;
        isFinished = false;
        iteration = 0;
    }

    /**
     * This is ran before this.execute()
     * 
     * It should return true if it successfully executed the contained code, and false if there was
     * a problem executing the contained code.
     */
    @Override
    public boolean init() {
        return true;
    }

    /**
     * This is ran after this.init() and loops while this.finished() returns false
     * 
     * It should return true if it successfully executed the contained code, and false if there was
     * a problem executing the contained code.
     */
    @Override
    public boolean execute() {
        if (this.iteration < this.length) {
            System.out.println("Test!");
            return true;
        } else if (this.iteration == this.length) {
            this.isFinished = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * This is ran every length this.execute() loops through
     * 
     * It should return true/false if this.execute() is finished/not finished
     * 
     * It should never return a definitive 'true' or 'false', but should return a variable
     * set to true only once the auto-mode finishes.
     */
    @Override
    public boolean finished() {
        return isFinished;
    }

    /**
     * This is ran after this.finished returns true
     * 
     * It should return true if it successfully executed the contained code, and false if there was
     * a problem executing the contained code.
     */
    @Override
    public boolean end() {
        return true;
    }

    // Get this.length
    @Override
    public double getLength() {
        return this.length;
    }

}