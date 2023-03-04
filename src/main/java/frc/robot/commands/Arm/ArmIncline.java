// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.Arm;

public class ArmIncline extends CommandBase {
  Arm armMotors;
  DoubleSupplier speedSupplier;
  double speed;
  public ArmIncline(Arm armMotors, DoubleSupplier speedSupplier) {
    this.armMotors = armMotors;
    this.speedSupplier = speedSupplier;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    speed = speedSupplier.getAsDouble();
    if(speed > 0){
      armMotors.armIncline(-speed*0.2);
    }else{
      armMotors.armIncline(-speed*0.3);
    }

    // if(armMotors.getInclineEncoder() / (25220 / ArmConstants.armMaxAngle) <= 28){
    //   new ArmExtend(armMotors, () -> -1);
    //   new WaitCommand(2).schedule();;
    //   ArmConstants.extendPause = true;
    // }else{
    //   ArmConstants.extendPause = false;
    // }
  }

  @Override
  public void end(boolean interrupted) {armMotors.inclineStop();}

  @Override
  public boolean isFinished() {
    return false;
  }
}
