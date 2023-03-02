package frc.robot.commandgroups.AutoCmds.ScoreCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants;
import frc.robot.commandgroups.PickUp;
import frc.robot.commandgroups.TeleopHelperCmds.ClawControlGroup;
import frc.robot.commands.Arm.AutoArmIncline;
import frc.robot.commands.Drivetrain.GyroTurnAnglePID;
import frc.robot.commands.Drivetrain.VerticalDrivePID;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class ScoreFloorAndGrab extends SequentialCommandGroup {
  public ScoreFloorAndGrab(
      Arm armMotors, Claw claw, DrivetrainSubsystem drivetrainSubsystem, VisionSubsystem vision) {
    addCommands(
        new AutoArmIncline(armMotors, ArmConstants.armLowestPositionIncline),
        new ClawControlGroup(claw, 0),
        new VerticalDrivePID(drivetrainSubsystem, -159),
        new GyroTurnAnglePID(drivetrainSubsystem, 180),
        new VerticalDrivePID(drivetrainSubsystem, 23.875),
        new PickUp(armMotors, claw, ArmConstants.clawCloseCone, vision),
        new GyroTurnAnglePID(drivetrainSubsystem, 0),
        new VerticalDrivePID(drivetrainSubsystem, 150));
  }
}
