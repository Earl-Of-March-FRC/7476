// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.AutoCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Arm.ArmControl;
import frc.robot.commands.Arm.ClawOpenAuto;
import frc.robot.commands.Drivetrain.MecanumDriveCmd;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ScoreMidLeave extends SequentialCommandGroup {
  /** Creates a new ScoreMidLeave. */
  public ScoreMidLeave(Drivetrain driveTrain, Arm armMotors, Claw claw) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        new ArmControl(armMotors, () -> 0.8, () -> 0.8)
            .until(() -> armMotors.getExtensionInches() >= 41)
            .until(() -> armMotors.getInclineAngle() >= 50),
        new ClawOpenAuto(claw),
        new ArmControl(armMotors, () -> 0.8, () -> 0.8)
            .until(() -> armMotors.getExtensionInches() >= -41)
            .until(() -> armMotors.getInclineAngle() >= -50),
        new WaitCommand(0.5),
        new MecanumDriveCmd(driveTrain, () -> -0.35, () -> 0.0, () -> 0.0)
            .until(() -> driveTrain.getDistance() >= 13));
  }
}
