// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.AutoCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Arm.ArmExtend;
import frc.robot.commands.Arm.ArmExtendTop;
import frc.robot.commands.Arm.ArmInclineTop;
import frc.robot.commands.Arm.ArmRetract;
import frc.robot.commands.ClawControl;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;

public class ScoreTopBalance extends SequentialCommandGroup {
  public ScoreTopBalance(
      DrivetrainSubsystem drive,
      Arm arm,
      Claw claw,
      double angleSetpoint,
      double extensionSetpoint) {
    addCommands(
        new ArmInclineTop(arm, angleSetpoint),
        new WaitCommand(0.5),
        new ArmExtendTop(arm, extensionSetpoint),
        new WaitCommand(0.5).raceWith(new ArmExtend(arm, () -> -0.20)),
        new ArmExtend(arm, () -> -0.2).raceWith(new ClawControl(claw, -1)).withTimeout(0.3),
        new ArmRetract(arm, 38),
        new Balance(drive));
  }
}
