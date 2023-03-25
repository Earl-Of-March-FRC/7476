// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import java.util.function.DoubleSupplier;

public class ArmExtend extends CommandBase {
  Arm armMotors;
  DoubleSupplier speedSupplier;
  double speed;

  public ArmExtend(Arm armMotors, DoubleSupplier speedSupplier) {
    this.armMotors = armMotors;
    this.speedSupplier = speedSupplier;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    speed = speedSupplier.getAsDouble();

    if (speed > 0) {
      armMotors.armExtension(-speed * 0.35);
    } else {
      armMotors.armExtension(-speed * 0.70);
    }
  }

  @Override
  public void end(boolean interrupted) {
    armMotors.extendStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
