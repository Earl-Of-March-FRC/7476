// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.LEDSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CancelArmAutomation extends InstantCommand {
  Arm arm;
  LEDSubsystem led;

  public Command armPlaceTopCommand;
  public Command armPlaceMidCommand;
  public Command armPlaceLowCommand;
  public Command armdefaultCommand;
  public Command armLoad;

  public CancelArmAutomation(
      Arm arm,
      LEDSubsystem led,
      Command armPlaceTop,
      Command armPlaceMid,
      Command armPlaceLow,
      Command armDefault,
      Command armLoad) {

    this.arm = arm;
    this.led = led;
    armPlaceTopCommand = armPlaceTop;
    armPlaceLowCommand = armPlaceLow;
    armPlaceMidCommand = armPlaceMid;
    armdefaultCommand = armDefault;
    this.armLoad = armLoad;

    addRequirements(arm, led);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    armPlaceTopCommand.cancel();
    armPlaceLowCommand.cancel();
    armPlaceMidCommand.cancel();
    armdefaultCommand.cancel();
    armLoad.cancel();

    led.setRainbow();
  }
}
