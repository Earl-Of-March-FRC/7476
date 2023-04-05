// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionConstants;

public class VisionSubsystem extends SubsystemBase {
  public int currentPipeline = 0;

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  NetworkTableEntry tyEntry = table.getEntry("ty");
  NetworkTableEntry txEntry = table.getEntry("tx");
  NetworkTableEntry tvEntry = table.getEntry("tv");
  NetworkTableEntry pipelineEntry = table.getEntry("pipeline");

  public VisionSubsystem() {
    limeLEDOff();
  }

  public void setPipeline(int pipeline) {
    pipelineEntry.setNumber(pipeline);
    currentPipeline = pipeline;
  }

  public int getPipeline() {
    return currentPipeline;
  }

  public double getTY() {
    return tyEntry.getDouble(-1);
  }

  public double getTX() {
    return txEntry.getDouble(-1);
  }

  public double getTV() {
    return tvEntry.getDouble(-1);
  }

  public void limeLEDOff() {
    table.getEntry("ledMode").setNumber(1);
  }

  public void limeLEDOn() {
    table.getEntry("ledMode").setNumber(3);
  }

  public double getDistance() {
    double totalAngle = Math.toRadians((tyEntry.getDouble(-1)) + VisionConstants.cameraAngle);

    double targetDistance =
        ((currentPipeline == 1 ? VisionConstants.aprilTagHeight : VisionConstants.topTapeHeight)
                - VisionConstants.cameraHeight)
            / Math.tan(totalAngle);

    return targetDistance;
  }

  @Override
  public void periodic() {}
}
