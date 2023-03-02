// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.AutoCmds.ScoreCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants;
import frc.robot.commandgroups.BalanceStation;
import frc.robot.commandgroups.PickUp;
import frc.robot.commandgroups.PlaceConeTop.ConePlaceTop;
import frc.robot.commands.Drivetrain.GyroTurnAnglePID;
import frc.robot.commands.Drivetrain.HorizontalDrivePID;
import frc.robot.commands.Drivetrain.VerticalDrivePID;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Everything extends SequentialCommandGroup {
  /** Creates a new Everything. */
  public Everything(
      Arm armMotors, Claw claw, DrivetrainSubsystem drivetrainSubsystem, VisionSubsystem vision) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        new ConePlaceTop(claw, armMotors),
        new VerticalDrivePID(drivetrainSubsystem, -5),
        new GyroTurnAnglePID(drivetrainSubsystem, 180),
        new VerticalDrivePID(drivetrainSubsystem, 210),
        new PickUp(armMotors, claw, ArmConstants.clawCloseCone, vision),
        new VerticalDrivePID(drivetrainSubsystem, -12),
        new HorizontalDrivePID(drivetrainSubsystem, 0),
        new BalanceStation(drivetrainSubsystem));
  }
}
