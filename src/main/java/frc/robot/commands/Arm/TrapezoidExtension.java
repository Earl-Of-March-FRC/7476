// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.TrapezoidProfileCommand;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.Arm;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TrapezoidExtension extends TrapezoidProfileCommand {
  static SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0, 0);

  // figure out what will happen if the position is negative because velocity is still positive
  public TrapezoidExtension(Arm armMotors, int rotation, double position) {
    super(
        // The motion profile to be executed
        new TrapezoidProfile(
            // The motion profile constraints
            new TrapezoidProfile.Constraints(
                ArmConstants.extensionMaxVelocity, ArmConstants.extensionMaxAcceleration),
            // Goal state
            new TrapezoidProfile.State(position, 3),
            // Initial state
            new TrapezoidProfile.State(armMotors.getExtensionEncoder(), 0)),
        state -> {
          double appliedVoltage = feedforward.calculate(state.velocity);
          armMotors.setVoltageExtension(appliedVoltage * rotation);
        });
  }
}
