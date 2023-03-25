// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LEDConstants;

public class LEDSubsystem extends SubsystemBase {

  private Spark LEDController = new Spark(LEDConstants.LEDTalonPort);
  
  public void setLEDHeartbeatSlow() {
    LEDController.set(0.03);
  }
  
  /** Creates a new LEDSubsystem. */
  public LEDSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    setLEDHeartbeatSlow();
    
  }
}
