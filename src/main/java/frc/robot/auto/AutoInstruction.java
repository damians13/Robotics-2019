package frc.robot.auto;

public class AutoInstruction {

    // i.e. new AutoInstruction("DRIVE_FORWARD", 6); would automatically drive forward 6 meters
    public enum Instruction {
        AUTO_CARTESIAN_DRIVE,
        TEST;
    }

    Instruction instruction;
    double length;

    public AutoInstruction(Instruction instruction, double length) {
        this.instruction = instruction;
        this.length = length;
    }
}