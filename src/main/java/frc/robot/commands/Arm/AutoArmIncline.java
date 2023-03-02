// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.Arm;

public class AutoArmIncline extends CommandBase {
  private Arm armMotors;
  private PIDController controller;

  /** Creates a new AutoArmAngle. */
  public AutoArmIncline(Arm armMotors, double setpoint) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.armMotors = armMotors;
    addRequirements(armMotors);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    controller =
        new PIDController(ArmConstants.kPIncline, ArmConstants.kIIncline, ArmConstants.kDIncline);
    controller.setTolerance(ArmConstants.armInclineTolerance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // target incline constant is placeholder as value might not be constant
    double speed =
        controller.calculate(armMotors.getAngleEncoderDistance(), ArmConstants.armInclineTarget);
    armMotors.armIncline(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return controller.atSetpoint();
  }
}
