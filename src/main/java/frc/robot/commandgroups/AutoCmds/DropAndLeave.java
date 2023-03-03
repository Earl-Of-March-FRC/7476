package frc.robot.commandgroups.AutoCmds;

import java.sql.Time;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.commands.ClawControl;
import frc.robot.commands.Arm.ArmDown;
import frc.robot.commands.Arm.ArmDownAuto;
import frc.robot.commands.Arm.ClawOpenAuto;
import frc.robot.commands.Arm.InclineStop;
import frc.robot.commands.Drivetrain.VerticalDrivePID;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DropAndLeave extends SequentialCommandGroup {
  public DropAndLeave(Claw claw, DrivetrainSubsystem driveTrain, Arm arm) {
    addCommands(
      new ArmDownAuto(arm),
      new ClawOpenAuto(claw),
      new VerticalDrivePID(driveTrain, DrivetrainConstants.dropAndLeaveSetpoint)
    );
  }
}
