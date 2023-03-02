package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ClawControl;
import frc.robot.subsystems.Claw;
import java.util.function.BooleanSupplier;

public class DropAndStop extends SequentialCommandGroup {
  public DropAndStop(Claw claw, BooleanSupplier current) {
    addCommands(new ClawControl(claw, 2));
  }
}
