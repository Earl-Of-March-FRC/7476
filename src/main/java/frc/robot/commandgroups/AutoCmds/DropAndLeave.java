package frc.robot.commandgroups.AutoCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.commands.Arm.ArmDownAuto;
import frc.robot.commands.Arm.ClawOpenAuto;
import frc.robot.commands.Drivetrain.VerticalDrivePID;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DropAndLeave extends SequentialCommandGroup {
  public DropAndLeave(Claw claw, DrivetrainSubsystem driveTrain, Arm arm) {
    addCommands(
        new VerticalDrivePID(driveTrain, DrivetrainConstants.dropAndLeaveSetpoint));
  }
}