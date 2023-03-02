package frc.robot.commandgroups.AutoCmds.ScoreCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants;
import frc.robot.commandgroups.BalanceStation;
import frc.robot.commandgroups.TeleopHelperCmds.ClawControlGroup;
import frc.robot.commands.Arm.AutoArmIncline;
import frc.robot.commands.Drivetrain.VerticalDrivePID;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

public class ScoreFloorAndBalance extends SequentialCommandGroup {
  /** Creates a new ScoreFloorAndBalance. */
  public ScoreFloorAndBalance(Arm armMotors, Claw claw, DrivetrainSubsystem drivetrainSubsystem) {
    addCommands(
        new AutoArmIncline(armMotors, ArmConstants.armLowestPositionIncline),
        new ClawControlGroup(claw, 0),
        new AutoArmIncline(armMotors, ArmConstants.armLowestPositionIncline),
        new VerticalDrivePID(drivetrainSubsystem, -156),
        new BalanceStation(drivetrainSubsystem));
  }
}
