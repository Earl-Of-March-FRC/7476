package frc.robot.commandgroups.AutoCmds.ScoreCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants;
import frc.robot.commandgroups.PickUp;
import frc.robot.commandgroups.PlaceConeTop.ConePlaceTop;
import frc.robot.commands.Drivetrain.GyroTurnAnglePID;
import frc.robot.commands.Drivetrain.VerticalDrivePID;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class ScoreTopAndGrab extends SequentialCommandGroup {
  public ScoreTopAndGrab(
      Arm armMotors, Claw claw, DrivetrainSubsystem drivetrainSubsystem, VisionSubsystem vision) {
    addCommands(
        new ConePlaceTop(claw, armMotors),
        new VerticalDrivePID(drivetrainSubsystem, -159),
        new GyroTurnAnglePID(drivetrainSubsystem, 180),
        new VerticalDrivePID(drivetrainSubsystem, 23.875),
        new PickUp(armMotors, claw, ArmConstants.clawCloseCone, vision),
        new GyroTurnAnglePID(drivetrainSubsystem, 0),
        new VerticalDrivePID(drivetrainSubsystem, 150));
  }
}
