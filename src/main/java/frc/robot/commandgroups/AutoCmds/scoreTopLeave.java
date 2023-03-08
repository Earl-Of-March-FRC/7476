// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.AutoCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Arm.ArmExtend;
import frc.robot.commands.Arm.ArmIncline;
import frc.robot.commands.Arm.ClawOpenAuto;
import frc.robot.commands.Drivetrain.MecanumDriveCmd;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;

public class scoreTopLeave extends SequentialCommandGroup {
  public scoreTopLeave(DrivetrainSubsystem driveTrain, Arm armMotors, Claw Claw) {
    addCommands(
        // new ArmIncline(armMotors, () -> 0.8).withTimeout(3.9),
        new ArmExtend(armMotors, () -> 0.8).until( () -> armMotors.getExtensionInches() >=75 ),
        new ClawOpenAuto(Claw),
        new ArmExtend(armMotors, () -> 0.8).until(() -> -armMotors.getExtensionInches() >=75),
        new WaitCommand(0.5),
        new MecanumDriveCmd(driveTrain, () -> -0.35, () -> 0.0, () -> 0.0).withTimeout(2.7));
  }
}
