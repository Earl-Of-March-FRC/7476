package frc.robot.commandgroups.TeleopHelperCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ClawControl;
import frc.robot.subsystems.Claw;

public class ClawControlGroup extends SequentialCommandGroup {
  /** Creates a new ClawControlGroup. */
  public ClawControlGroup(Claw claw, int goal) {
    double change = goal - claw.currentMagnet;
    if (Math.abs(change) == 1) {
      addCommands(new ClawControl(claw, Math.signum(change) > 0 ? -1 : 1));
    } else if (Math.abs(change) == 2) {
      addCommands(
          new ClawControl(claw, Math.signum(change) > 0 ? -1 : 1),
          new ClawControl(claw, Math.signum(change) > 0 ? -1 : 1));
    }

    claw.currentMagnet = goal;
  }
}
