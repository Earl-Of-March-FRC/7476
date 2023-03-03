// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Arm;

public class ExtendStop extends InstantCommand {

  Arm arm;

  public ExtendStop(Arm arm) {
    this.arm = arm;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    arm.inclineStop();
  }
}
