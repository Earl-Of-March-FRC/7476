// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commandgroups.TeleopArm.ArmDefaultPosition;
import frc.robot.commandgroups.TeleopArm.ArmPlaceLow;
import frc.robot.commandgroups.TeleopArm.ArmPlaceMid;
import frc.robot.commandgroups.TeleopArm.ArmPlaceTop;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.LEDSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CancelAutomation extends InstantCommand {
  Arm arm;
  LEDSubsystem led;

  public Command armPlaceTopCommand;
  public Command armPlaceMidCommand;
  public Command armPlaceLowCommand;
  public Command armdefaultCommand;

  public CancelAutomation(Arm arm, LEDSubsystem led, Command armPlaceTop, Command armPlaceMid, Command armPlaceLow, Command armDefault) {
    this.arm = arm;
    this.led = led;
    armPlaceTopCommand = armPlaceTop;
    armPlaceLowCommand = armPlaceLow;
    armPlaceMidCommand = armPlaceMid;
    armdefaultCommand = armDefault;

    addRequirements(arm, led);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    armPlaceTopCommand.cancel();
    armPlaceLowCommand.cancel();
    armPlaceMidCommand.cancel();
    armdefaultCommand.cancel();

    led.setRainbow();
  }
}
