// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ArmIn extends CommandBase {
  Arm armMotors;

  /** Creates a new ArmIn. */
  public ArmIn(Arm armMotors) {
    this.armMotors = armMotors;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // armMotors.armIn();
    armMotors.armExtension(-0.2);//TODO: Lower speed
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    armMotors.extendStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
