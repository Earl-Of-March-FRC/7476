// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.AutoCmds;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Leave extends SequentialCommandGroup {
  public Leave(DrivetrainSubsystem driveTrain) {
    addCommands(
        new InstantCommand(() -> driveTrain.setMecanumPermanent(-0.5, 0, 0))
            .andThen(new WaitCommand(2))
            .andThen(new InstantCommand(() -> driveTrain.setMecanumPermanent(0, 0, 0))));
  }
}
