// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.AutoCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Arm.ArmExtend;
import frc.robot.commands.Arm.ArmIncline;
import frc.robot.commands.ClawControl;
import frc.robot.commands.Drivetrain.MecanumDriveCmd;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ScoreFloorLeave extends SequentialCommandGroup {
  /** Creates a new ScoreFloorLeave. */
  public ScoreFloorLeave(Arm armMotors, DrivetrainSubsystem drivetrain, Claw claw) {
    addCommands(
        new ArmIncline(armMotors, () -> 2).withTimeout(1.25),
        new ArmExtend(armMotors, () -> -0.65).withTimeout(0.65),
        new ClawControl(claw, 1).withTimeout(1),
        new ArmIncline(armMotors, () -> -1.5).withTimeout(1),
        new ArmExtend(armMotors, () -> 0.65).withTimeout(1),
        new WaitCommand(0.5),
        new MecanumDriveCmd(drivetrain, () -> 0.5, () -> 0.0, () -> 0.0, () -> 1.0).withTimeout(3.25),
        new WaitCommand(1));
  }
}
