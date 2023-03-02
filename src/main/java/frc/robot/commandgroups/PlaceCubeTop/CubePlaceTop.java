package frc.robot.commandgroups.PlaceCubeTop;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants;
import frc.robot.commandgroups.TeleopHelperCmds.ClawControlGroup;
import frc.robot.commands.Arm.AutoArmIncline;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CubePlaceTop extends SequentialCommandGroup {
  /** Creates a new ConePlaceTop. */
  public CubePlaceTop(Claw claw, Arm arm) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new CubeArmTop(arm),
      new ClawControlGroup(claw, 0),
      new AutoArmIncline(arm, ArmConstants.armDefaultIncline)
    );
  }
}
