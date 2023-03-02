// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.Arm;

public class PIDArmExtension extends CommandBase {

  private Arm armMotors;
  private PIDController controller;
  double setpoint;

  /** Creates a new AutoArmExtension. */
  public PIDArmExtension(Arm armMotors, double setpoint) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.setpoint = setpoint;
    this.armMotors = armMotors;
    addRequirements(armMotors);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    controller =
        new PIDController(
            ArmConstants.kPExtension, ArmConstants.kIExtension, ArmConstants.kDExtension);
    controller.setTolerance(ArmConstants.armExtensionTolerance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // the target extension below is place holder as it might not be constant
    double speed = controller.calculate(armMotors.getExtensionEncoder(), setpoint);
    armMotors.armExtension(speed);
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
