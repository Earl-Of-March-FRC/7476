// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.PlaceCubeTop;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.GridConstants;
import frc.robot.commandgroups.TeleopHelperCmds.TeleopArmExtension;
import frc.robot.commands.Arm.AutoArmIncline;
import frc.robot.subsystems.Arm;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CubeArmTop extends ParallelCommandGroup {

  /** Creates a new ConePlaceTop. */
  public CubeArmTop(Arm arm) {
    addCommands(
      new AutoArmIncline(arm, GridConstants.cubePlaceTopIncline), 
      new TeleopArmExtension(arm, GridConstants.cubePlaceTopExtension));
  }
}
