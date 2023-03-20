// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.AutoCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.ArmConstants;
import frc.robot.commandgroups.AutoArm.ArmExtensionAndInclineAuto;
import frc.robot.commands.Arm.ArmExtend;
import frc.robot.commands.Arm.ArmRetract;
import frc.robot.commands.Drivetrain.MecanumDriveCmd;
import frc.robot.commands.Drivetrain.VerticalDrivePID;
import frc.robot.commands.ClawControl;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;

public class ScoreTopLeave extends SequentialCommandGroup {
  public ScoreTopLeave(DrivetrainSubsystem driveTrain, Arm armMotors, Claw claw) {
    addCommands(
        // new ArmIncline(armMotors, () -> 0.8).until( () -> armMotors.getInclineAngle() >=50),
        // new ArmExtend(armMotors, () -> 0.8).until( () -> armMotors.getExtensionInches() >=75),
        new ArmExtensionAndInclineAuto(
            armMotors, ArmConstants.conePlaceTopIncline, ArmConstants.conePlaceTopExtend),
        new ClawControl(claw, -1).withTimeout(0.5).raceWith(new ArmExtend(armMotors, () -> -0.10)),
        // new ArmExtend(armMotors, () -> 0.8).until(() -> armMotors.getExtensionInches() >=-75),
        new ArmRetract(armMotors, 45),
        new WaitCommand(0.5),
        new Leave(driveTrain));
  }
}
