package frc.robot.auto;

import java.util.ArrayList;

import frc.robot.auto.modes.*;

public class AutoModeScheduler {

    private ArrayList<AutoInstruction> queue;
    private AutoModeBase mode;

    public AutoModeScheduler() {
        queue  = new ArrayList<AutoInstruction>();
    }

    /**
     * In the run() method, you will see this general syntax a few times:
     * 
     * if(!mode.method())
     *     System.out.println("*** Auto-mode failed something. ***");
     * 
     * These methods return true if it works properly and false if something goes wrong.
     * This lets the above sample lines check if anything goes wrong with the code,
     * and then report it to the logs for us to see.
     */

    public void run() {
        // If ready for next auto instruction, process it and remove it from the queue
        if (mode == null && queue.size() > 0) {
            mode = instructionToMode(queue.get(0));
            System.out.println(queue.get(0) + " auto-mode starting...");
            if(!mode.init())
                System.out.println("*** Auto-mode failed to initialize. ***");
            queue.remove(0);
        }

        if (!mode.finished()) { // If the auto-mode is not finished, run it
            if(!mode.execute())
                System.out.println("*** Auto-mode failed to execute. ***");
        } else {        // If the auto-mode is finished, run mode.end() and set it to null
            if(!mode.end())
                System.out.println("*** Auto-mode failed to end. ***");
            mode = null;
        }
    }

    // Turn the instruction into the mode object
    private AutoModeBase instructionToMode(AutoInstruction autoInstruction) {
        switch(autoInstruction.instruction) {
            case AUTO_CARTESIAN_DRIVE:
                return new Test(autoInstruction.length); // TO-DO
            case TEST:
                return new Test(autoInstruction.length);
            default: // If this is called, the programmer who last changed the auto modes messed up
                return new UnhandledAutoMode(autoInstruction.length);
        }
    }

    // This is how we will schedule an auto job
    public void addToQueue(AutoInstruction instruction) {
        queue.add(instruction);
    }

    public AutoInstruction getFromQueue(int index) {
        return queue.get(index);
    }

    public void clearQueue() {
        queue.clear();
    }

    // Remove the instruction at the provided index from the queue
    public void removeIndexFromQueue(int index) {
        queue.remove(index);
    }

    // Find and remove provided instruction from the queue
    public void removeInstructionFromQueue(AutoInstruction instruction) {
        queue.remove(instruction);
    }

    public int getSizeOfQueue() {
        return queue.size();
    }
}