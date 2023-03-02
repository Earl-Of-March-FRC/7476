package frc.robot.commandgroups.AutoCmds.DropCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Claw.ClawControl;
import frc.robot.subsystems.Claw;

public class DropAndStop extends SequentialCommandGroup {
  public DropAndStop(Claw claw) {
    addCommands(new ClawControl(claw, 0));
  }
}
