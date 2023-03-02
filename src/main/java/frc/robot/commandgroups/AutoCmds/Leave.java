package frc.robot.commandgroups.AutoCmds;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Drivetrain.VerticalDrivePID;
import frc.robot.subsystems.DrivetrainSubsystem;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

public class Leave extends SequentialCommandGroup {
  /** Creates a new Leave. */
  public Leave(DrivetrainSubsystem drivetrainSubsystem) {
    addCommands(new VerticalDrivePID(drivetrainSubsystem, 100));
  }
}
