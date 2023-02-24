package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;

public class DriveTrainSubsystem extends SubsystemBase {
  private WPI_TalonFX frontLeft = new WPI_TalonFX(DriveTrainConstants.frontLeftMotorPort);
  private WPI_TalonFX frontRight = new WPI_TalonFX(DriveTrainConstants.frontRightMotorPort);
  private WPI_TalonFX backLeft = new WPI_TalonFX(DriveTrainConstants.backLeftMotorPort);
  private WPI_TalonFX backRight = new WPI_TalonFX(DriveTrainConstants.backRightMotorPort);

  public DriveTrainSubsystem() {}

  @Override
  public void periodic() {}
}
