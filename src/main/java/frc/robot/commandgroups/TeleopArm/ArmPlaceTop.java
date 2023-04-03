// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups.TeleopArm;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
<<<<<<< HEAD
import frc.robot.commands.LEDGold;
import frc.robot.commands.LEDRainbow;
import frc.robot.commands.Arm.ArmExtend;
=======
>>>>>>> 72f988e6684c9724fec07effd382f7f934e4e1b1
import frc.robot.commands.Arm.ArmExtendTop;
import frc.robot.commands.Arm.ArmInclineTop;
import frc.robot.commands.LEDGold;
import frc.robot.commands.LEDRainbow;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.LEDSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ArmPlaceTop extends SequentialCommandGroup {
  /** Creates a new ArmPlaceTop. */
  public ArmPlaceTop(Arm arm, LEDSubsystem led) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        new LEDGold(led),
        new ArmInclineTop(arm, 38),
        new WaitCommand(0.5),
<<<<<<< HEAD
        new ArmExtendTop(arm, 76),
        new ArmInclineTop(arm, 32).raceWith(new ArmExtend(arm, () -> -0.2)),
        new LEDRainbow(led)
    );
=======
        new ArmExtendTop(arm, 78),
        new LEDRainbow(led));
>>>>>>> 72f988e6684c9724fec07effd382f7f934e4e1b1
  }
}
