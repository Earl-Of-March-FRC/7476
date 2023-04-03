// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.subsystems.Arm;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ArmRetract extends ProfiledPIDCommand {
  public ArmRetract(Arm armMotors, double extensionSetpoint) {
    super(
        new ProfiledPIDController(
            // The PID gains
            0.18,
            0,
            0,
            // The motion profile constraints
            new TrapezoidProfile.Constraints(65, 60)),
        // This should return the measurement
        () -> armMotors.getExtensionInches(),
        // This should return the goal (can also be a constant)
        () -> extensionSetpoint,
        // This uses the output
        (output, setpoint) -> {
          armMotors.armExtension(output);
        });
    addRequirements(armMotors);
    getController().setTolerance(1.5);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (getController().atGoal()) {
      System.out.println("Retract Ended");
    }
    return getController().atGoal();
  }
}
