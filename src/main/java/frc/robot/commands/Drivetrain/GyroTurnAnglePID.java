package frc.robot.commands.Drivetrain;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.GyroConstants;
import frc.robot.subsystems.DriveTrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class GyroTurnAnglePID extends PIDCommand {
  /** Creates a new GyroTurnAnglePIDA. */
  public GyroTurnAnglePID(DriveTrainSubsystem driveTrain, double setpoint) {
    super(
        // The controller that the command will use
        new PIDController(0.04, 0.04, GyroConstants.kDGyro),
        // This should return the measurement
        () -> (driveTrain.getGyroAngle() % 360),
        // This should return the setpoint (can also be a constant)
        () -> setpoint,
        // This uses the output
        output -> {
          driveTrain.setMecanumPermanent(0, 0, -output);
        });
    getController().setTolerance(GyroConstants.gyroTurnTolerance);
    addRequirements(driveTrain);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
