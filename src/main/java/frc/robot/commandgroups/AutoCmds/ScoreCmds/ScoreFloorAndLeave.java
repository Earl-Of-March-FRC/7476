package frc.robot.commandgroups.AutoCmds.ScoreCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants;
import frc.robot.commandgroups.TeleopHelperCmds.ClawControlGroup;
import frc.robot.commands.Arm.AutoArmIncline;
import frc.robot.commands.Drivetrain.VerticalDrivePID;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;

public class ScoreFloorAndLeave extends SequentialCommandGroup {
  public ScoreFloorAndLeave(Claw claw, DrivetrainSubsystem driveTrain, Arm arm) {
    addCommands(
        new AutoArmIncline(arm, ArmConstants.armLowestPositionIncline),
        new ClawControlGroup(claw, 0),
        new AutoArmIncline(arm, ArmConstants.armDefaultIncline),
        new VerticalDrivePID(driveTrain, 224));
  }
}
