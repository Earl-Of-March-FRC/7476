package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Drivetrain.BalanceChargeStation;
import frc.robot.commands.Drivetrain.MecanumDriveCmd;
import frc.robot.subsystems.DrivetrainSubsystem;

public class BalanceStation extends SequentialCommandGroup {
  public BalanceStation(DrivetrainSubsystem drive) {
    addCommands(
        (new MecanumDriveCmd(drive, () -> 0.2, () -> 0.0, () -> 0.0)
                .until(() -> drive.getGyroPitch() > 10))
            .andThen(new BalanceChargeStation(drive)));
  }
}
