// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.AutoCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Drivetrain.MecanumDriveCmd;
import frc.robot.subsystems.DrivetrainSubsystem;

public class LeaveClose extends SequentialCommandGroup {
  public LeaveClose(DrivetrainSubsystem driveTrain) {
    addCommands(
        new MecanumDriveCmd(driveTrain, () -> 0.0, () -> 0.25, () -> 0.0, () -> 3.0,() -> false)
            .withTimeout(2.7),
        new WaitCommand(1),
        new MecanumDriveCmd(driveTrain, () -> -0.0, () -> -0.25, () -> 0.0, () -> 3.0, () -> false)
            .withTimeout(2.7),
        new WaitCommand(1));
  }
}
