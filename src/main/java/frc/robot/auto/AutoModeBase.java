package frc.robot.auto;

public interface AutoModeBase {
    public boolean init( /* This runs before this.execute() */ );
    public boolean execute( /* This runs the contained code repeatedly until finished */ );
    public boolean finished( /* Used to determine if this.execute() is finished */ );
    public boolean end( /* This is ran once this.finished() returns true */ );
    public double getLength( /* Return the desired length of the auto-mode execution */ );
}