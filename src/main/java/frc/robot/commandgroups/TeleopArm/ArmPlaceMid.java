// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.TeleopArm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Arm.ArmExtendTop;
import frc.robot.commands.Arm.ArmInclineTop;
import frc.robot.commands.Arm.ArmRetract;
import frc.robot.commands.LEDGold;
import frc.robot.commands.LEDRainbow;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.LEDSubsystem;

public class ArmPlaceMid extends SequentialCommandGroup {
  /** Creates a new ArmPlaceMid. */
  public ArmPlaceMid(Arm arm, LEDSubsystem led) {

    addCommands(new LEDGold(led), new ArmInclineTop(arm, 34), new WaitCommand(0.5));

    if (arm.getExtensionInches() > 65) {
      addCommands(new ArmRetract(arm, 64));
    } else if (arm.getExtensionInches() < 63) {
      addCommands(new ArmInclineTop(arm, 34), new WaitCommand(0.5), new ArmExtendTop(arm, 64));
    }
    addCommands(new LEDRainbow(led));
  }
}
