package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Claw;

public class ClawControl extends CommandBase {

  Claw claw;
  double speed;

  public ClawControl(Claw claw, double speed) {
    this.claw = claw;
    this.speed = speed;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    claw.setClawSpeed(speed);
  }

  @Override
  public void end(boolean interrupted) {
    claw.stopClaw();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
