package frc.robot.commands.Drivetrain;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class HorizontalDrivePID extends PIDCommand {

  public HorizontalDrivePID(DrivetrainSubsystem drive, double setpoint) {
    super(
        new PIDController(
            DrivetrainConstants.kPHorizontalDrive,
            DrivetrainConstants.kIHorizontalDrive,
            DrivetrainConstants.kDHorizontalDrive),
        () -> drive.getDistance(), // measurement
        () -> setpoint, // setpoint
        output -> {
          drive.setMecanum(0, output, 0); // calculates and applies required output
        });

    addRequirements(drive);
    getController().setTolerance(DrivetrainConstants.distanceTolerance);
  }

  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
