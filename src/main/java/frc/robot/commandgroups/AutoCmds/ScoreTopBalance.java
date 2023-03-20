package frc.robot.commandgroups.AutoCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants;
import frc.robot.commandgroups.BalanceStation;
import frc.robot.commandgroups.AutoArm.ArmExtensionAndInclineTop;
import frc.robot.commands.Drivetrain.VerticalDrivePID;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

public class ScoreTopBalance extends SequentialCommandGroup {
  /** Creates a new ScoreFloorAndBalance. */
  public ScoreTopBalance(Arm armMotors, Claw claw, DrivetrainSubsystem drivetrainSubsystem)
{
    addCommands(
        new ArmExtensionAndInclineTop(armMotors, ArmConstants.conePlaceTopIncline, ArmConstants.conePlaceTopExtend),
        new BalanceStation(drivetrainSubsystem)
    );
  }
}
