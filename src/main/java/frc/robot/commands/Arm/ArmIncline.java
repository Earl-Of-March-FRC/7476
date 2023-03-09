package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import java.util.function.DoubleSupplier;

public class ArmIncline extends CommandBase {
  Arm armMotors;
  DoubleSupplier speedSupplier;
  double speed;

  public ArmIncline(Arm armMotors, DoubleSupplier speedSupplier) {
    this.armMotors = armMotors;
    this.speedSupplier = speedSupplier;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    speed = speedSupplier.getAsDouble();

    if (speed > 0) {
      armMotors.armIncline(speed * -0.65);
    } else {
      armMotors.armIncline(speed * -1);
    }

    // if (armMotors.getInclineAngle() <= 28) {
    //   new ArmExtend(armMotors, () -> 0.14)
    //       .until(
    //           () -> armMotors.getExtensionInches() <= 78 / Math.cos(armMotors.getInclineAngle()));
    // }
  }

  @Override
  public void end(boolean interrupted) {
    armMotors.inclineStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
