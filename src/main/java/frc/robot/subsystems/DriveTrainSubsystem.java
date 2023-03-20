package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class DrivetrainSubsystem extends SubsystemBase {

  private WPI_TalonFX frontLeft = new WPI_TalonFX(DrivetrainConstants.frontLeftTalonPort);
  private WPI_TalonFX frontRight = new WPI_TalonFX(DrivetrainConstants.frontRightTalonPort);
  private WPI_TalonFX backLeft = new WPI_TalonFX(DrivetrainConstants.backLeftTalonPort);
  private WPI_TalonFX backRight = new WPI_TalonFX(DrivetrainConstants.backRightTalonPort);

  public MecanumDrive mecDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

  private AHRS ahrs = new AHRS(Port.kUSB1);

  public DrivetrainSubsystem() {
    frontLeft.setNeutralMode(NeutralMode.Brake);
    frontRight.setNeutralMode(NeutralMode.Brake);
    backLeft.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);

    frontLeft.setInverted(true);
    backLeft.setInverted(true);
    ahrs.calibrate();
    ahrs.reset();

    frontLeft.setSelectedSensorPosition(0);
    frontRight.setSelectedSensorPosition(0);
    backLeft.setSelectedSensorPosition(0);
    backRight.setSelectedSensorPosition(0);
  }

  public double getHeading() {
    return ahrs.getYaw();
  }

  public double getGyroAngle() {
    return ahrs.getAngle();
  }

  public void resetGyro() {
    ahrs.reset();
  }

  public void calibrateGyro(){
    ahrs.calibrate();
  }
  public double getGyroRoll() {
    return ahrs.getRoll();
  }

  public void resetEncoders() {
    frontLeft.setSelectedSensorPosition(0);
    frontRight.setSelectedSensorPosition(0);
    backLeft.setSelectedSensorPosition(0);
    backRight.setSelectedSensorPosition(0);
  }

  public double getGyroPitch() {
    return ahrs.getPitch();
  }

  public double distanceToTicks(double distanceInches) {
    return (distanceInches / 6 * Math.PI) * 2048;
  }

  public void setMecanumPermanent(double y, double x, double rx) {
    frontLeft.set(y + x + rx);
    backLeft.set(y - x + rx);
    frontRight.set(y - x - rx);
    backRight.set(y + x - rx);
  }

  public void setMecanum(double y, double x, double rx) {
    mecDrive.driveCartesian(y, x, rx, ahrs.getRotation2d());
    
    // , ahrs.getRotation2d().rotateBy(Rotation2d.fromDegrees(-90))
    // frontLeft.set(y + x + rx);
    // backLeft.set(y - x + rx);
    // frontRight.set(y - x - rx);
    // backRight.set(y + x - rx);

    SmartDashboard.putNumber("x", x);
    SmartDashboard.putNumber("y", y);
    SmartDashboard.putNumber("rx", rx);
  }

  public double getDistance() {
    return (frontLeft.getSelectedSensorPosition() * 3.55 * Math.PI / 2048) / 10.71;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Front Left Encoder Distance", getDistance());
    SmartDashboard.putNumber("Gyro Get Angle", ahrs.getAngle());
  }

  @Override
  public void simulationPeriodic() {}
}
