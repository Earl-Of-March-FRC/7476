// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.TeleopHelperCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Arm.PIDArmExtension;
import frc.robot.commands.Arm.TrapezoidExtension;
import frc.robot.subsystems.Arm;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TeleopArmExtension extends SequentialCommandGroup {

  /** Creates a new TeleopArmExtension. */
  public TeleopArmExtension(Arm armMotors, double setpoint) {
    double change = armMotors.getExtensionEncoder() - setpoint;
    addCommands(
        new TrapezoidExtension(armMotors, Math.signum(change) > 0 ? -1 : 1, setpoint / 2)
            .until(() -> armMotors.getExtensionEncoder() > setpoint / 2)
            .andThen(new PIDArmExtension(armMotors, setpoint)));
  }
}
