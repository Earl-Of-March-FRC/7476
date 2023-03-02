package frc.robot.commandgroups.PlaceConeTop;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants;
import frc.robot.commandgroups.TeleopHelperCmds.ClawControlGroup;
import frc.robot.commands.Arm.AutoArmIncline;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ConePlaceTop extends SequentialCommandGroup {
  /** Creates a new ConePlaceTop. */
  public ConePlaceTop(Claw claw, Arm arm) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ConeArmTop(arm),
      new ClawControlGroup(claw, 0),
      new AutoArmIncline(arm, ArmConstants.armDefaultIncline)
    );
  }
}
