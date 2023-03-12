// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class VerticalPID extends ProfiledPIDCommand {
  /** Creates a new VerticalPID. */
  public VerticalPID(DrivetrainSubsystem drive, double target) {
    super(
        // The ProfiledPIDController used by the command
        new ProfiledPIDController(
            // The PID gains
            0.01,
            0,
            0,
            // The motion profile constraints
            new TrapezoidProfile.Constraints(200, 150)),
        // This should return the measurement
        () -> drive.getDistance(),
        // This should return the goal (can also be a constant)
        () -> target,
        // This uses the output
        (output, setpoint) -> drive.setMecanum(output, 0, 0),
        drive);

    getController().setTolerance(5);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atGoal();
  }
}
