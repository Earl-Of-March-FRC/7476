// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Constants.DriveTrainConstants;

public class DriveTrainSubsystem extends SubsystemBase {
  private WPI_TalonFX frontLeft = new WPI_TalonFX(DriveTrainConstants.frontLeftMotorPort);
  private WPI_TalonFX frontRight = new WPI_TalonFX(DriveTrainConstants.frontRightMotorPort);
  private WPI_TalonFX backLeft = new WPI_TalonFX(DriveTrainConstants.backLeftMotorPort);
  private WPI_TalonFX backRight = new WPI_TalonFX(DriveTrainConstants.backRightMotorPort);
  /** Creates a new ExampleSubsystem. */
  public DriveTrainSubsystem() {}

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
