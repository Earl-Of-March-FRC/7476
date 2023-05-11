package frc.robot.commands.Arm;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.subsystems.Arm;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ArmExtendTop extends ProfiledPIDCommand {
  /** Creates a new ArmMoveTop. */
  public ArmExtendTop(Arm armMotors, double extensionSetpoint) {
    super(
        // The ProfiledPIDController used by the command
        new ProfiledPIDController(
            // The PID gains
            0.35,
            0,
            0,
            // The motion profile constraints
            new TrapezoidProfile.Constraints(90, 100)),
        // This should return the measurement
        () -> armMotors.getExtensionInches(),
        // This should return the goal (can also be a constant)
        () -> extensionSetpoint,
        // This uses the output
        (output, setpoint) -> {
          MathUtil.clamp(output, -0.5, 0.5);
          armMotors.armExtension(output);
        });
    addRequirements(armMotors);
    getController().setTolerance(1.5);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (getController().atGoal()) {
      System.out.println("Extend Top Ended");
    }
    return getController().atGoal();
  }
}
