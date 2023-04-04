// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LEDConstants;

public class LEDSubsystem extends SubsystemBase {

  private Spark LEDController = new Spark(LEDConstants.LEDTalonPort);
  // private PWM LED=new PWM(9);
  
  public void setPresetGold() {
    LEDController.set(0.33);
    // LED.setRaw(125);
     
  }

  public void setPresetGreen() {
    LEDController.set(0.89);
  }

  public void setRainbow() {
    LEDController.set(-0.99); // 0.89 is green
  }

  /** Creates a new LEDSubsystem. */
  public LEDSubsystem() {
    setRainbow();
  }

  @Override
  public void periodic() {}
}
