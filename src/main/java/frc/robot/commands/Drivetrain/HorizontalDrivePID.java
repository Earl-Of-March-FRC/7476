// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class HorizontalDrivePID extends PIDCommand {

  VisionSubsystem visionSubsystem;

  public HorizontalDrivePID(
      DrivetrainSubsystem drivetrain, VisionSubsystem vision, double setpoint) {
    super(
        // The controller that the command will use
        new PIDController(0.03, 0.03, 0.001),
        // This should return the measurement
        () -> vision.getTY(),
        // This should return the setpoint (can also be a constant)
        () -> setpoint,
        // This uses the output
        output -> {
          drivetrain.setMecanum(0, -output, 0);
        });
    addRequirements(drivetrain);
    getController().setTolerance(0.7);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_controller.atSetpoint();
  }
}
