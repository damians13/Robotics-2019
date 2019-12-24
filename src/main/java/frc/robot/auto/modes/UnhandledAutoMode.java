package frc.robot.auto.modes;

import frc.robot.auto.AutoModeBase;

public class UnhandledAutoMode implements AutoModeBase{

    public UnhandledAutoMode(double givenLength) {
        System.out.println("*** Problem with AutoModeScheduling, UnhandledAutoMode started with given length of " + givenLength + " ***");
    }

	@Override
	public boolean init() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean end() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getLength() {
		// TODO Auto-generated method stub
		return 0;
	}
}