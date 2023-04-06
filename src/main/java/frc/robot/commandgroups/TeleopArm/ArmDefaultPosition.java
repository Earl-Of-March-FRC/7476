// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.TeleopArm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Arm.ArmInclineTop;
import frc.robot.commands.Arm.ArmRetract;
import frc.robot.commands.LEDGold;
import frc.robot.commands.LEDRainbow;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.LEDSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ArmDefaultPosition extends SequentialCommandGroup {
  /** Creates a new ArmDefaultPosition. */
  public ArmDefaultPosition(Arm arm, LEDSubsystem led) {
    addCommands(
        new LEDGold(led), new ArmInclineTop(arm, 41), new ArmRetract(arm, 40), new LEDRainbow(led));
  }
}
