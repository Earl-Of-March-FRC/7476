// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DrivetrainConstants;

public class ScaleButtonCmd extends CommandBase {
  /** Creates a new ScaleButtonCmd. */
  public ScaleButtonCmd() {}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    DrivetrainConstants.moveScaleFactor = 0.45;
    DrivetrainConstants.turnScaleFactor = 0.25;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {DrivetrainConstants.moveScaleFactor = 0.7;}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
