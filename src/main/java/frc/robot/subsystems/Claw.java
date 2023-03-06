// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class Claw extends SubsystemBase {
  // public final DigitalInput hallEffect = new DigitalInput(0);
  private final WPI_VictorSPX claw = new WPI_VictorSPX(ArmConstants.claw);
  public int currentMagnet = 0;

  public Claw() {
    claw.setNeutralMode(NeutralMode.Brake);
  }

  public void setClawSpeed(double speed) {
    claw.set(speed);
  }

  public void stopClaw() {
    claw.set(0);
  }

  // public boolean getHallEffectValue() {
  //   return hallEffect.get();
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
