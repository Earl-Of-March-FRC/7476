package frc.robot.commandgroups.AutoCmds.DropCmds;

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

public class DropGrabDrop extends SequentialCommandGroup {
  public DropGrabDrop(Arm armMotors, Claw claw, DrivetrainSubsystem drive, VisionSubsystem vision) {
    addCommands(
        new AutoArmIncline(armMotors, ArmConstants.armDefaultIncline),
        new ClawControlGroup(claw, 0),
        new VerticalDrivePID(drive, -6),
        new GyroTurnAnglePID(drive, 180),
        new VerticalDrivePID(drive, 23.875),
        new PickUp(armMotors, claw, ArmConstants.clawCloseCone, vision),
        new GyroTurnAnglePID(drive, 0),
        new VerticalDrivePID(drive, 33.25),
        new ClawControlGroup(claw, 0));
  }
}
