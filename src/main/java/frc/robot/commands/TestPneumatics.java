/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class TestPneumatics extends Command {

    public static DoubleSolenoid testSolenoid;

  public GrabberSolenoids() {
    requires(Robot.pneumatics);

    testSolenoid = new DoubleSolenoid(2, 4);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (!Robot.solenoidEnabled) {
        testSolenoid.set(DoubleSolenoid.Value.kForward);
        Robot.solenoidEnabled = true;
    }
    else if (Robot.solenoidEnabled) {
        testSolenoid.set(DoubleSolenoid.Value.kReverse);
        Robot.solenoidEnabled = false;
    }
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() { }

  @Override
  protected void interrupted() { }
}