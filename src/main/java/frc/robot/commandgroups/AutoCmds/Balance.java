// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.AutoCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import frc.robot.commands.Drivetrain.VerticalDrivePID;
import frc.robot.subsystems.DriveTrainSubsystem;

public class Balance extends SequentialCommandGroup {
  public Balance(DriveTrainSubsystem driveTrain) {
    addCommands();
    // new MecanumDriveCmd(driveTrain, () -> 0.0, () -> 1.0, () -> 0.0, () -> 3.0, () -> false)
    //     .until(() -> driveTrain.getPitch() > 5), // 1.3
    // new VerticalDrivePID(driveTrain, 1),
    // new WaitCommand(1)
  }
}
