// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.subsystems.Arm;

public class ArmInclineTop extends ProfiledPIDCommand {
  public ArmInclineTop(Arm armMotors, double angleSetpoint) {
    super(
        new ProfiledPIDController(
            // The PID gains
            0.55,
            0,
            0,
            // The motion profile constraints
            new TrapezoidProfile.Constraints(60, 90)),
        // This should return the measurement
        () -> armMotors.getInclineAngle(),
        // This should return the goal (can also be a constant)
        () -> angleSetpoint,
        // This uses the output
        (output, setpoint) -> armMotors.armIncline(output));
        addRequirements(armMotors);

    getController().setTolerance(1);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (getController().atGoal()) {
      System.out.println("Incline Top Ended");
    }
    return getController().atGoal();
  }
}
