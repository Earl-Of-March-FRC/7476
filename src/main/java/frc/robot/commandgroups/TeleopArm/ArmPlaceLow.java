// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.TeleopArm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Arm.ArmExtendTop;
import frc.robot.commands.Arm.ArmInclineTop;
import frc.robot.commands.Arm.ArmRetract;
import frc.robot.subsystems.Arm;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ArmPlaceLow extends SequentialCommandGroup {
  /** Creates a new ArmPlaceTop. */
  public ArmPlaceLow(Arm arm) {

    if(arm.getExtensionInches() > 41){
      addCommands(
        new ArmRetract(arm, 40)
      );
    }
    else if(arm.getExtensionInches() < 39){
      addCommands(
        new ArmExtendTop(arm, 40)
      );
    }

    addCommands(
      new ArmInclineTop(arm, 10));
    
  }
}
