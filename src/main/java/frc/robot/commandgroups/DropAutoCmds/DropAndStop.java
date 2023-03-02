package frc.robot.commandgroups.DropAutoCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ClawControl;
import frc.robot.subsystems.Claw;

public class DropAndStop extends SequentialCommandGroup {
  public DropAndStop(Claw claw) {
    addCommands(new ClawControl(claw, 0));
  }
}
