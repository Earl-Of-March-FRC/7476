package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.Constants.ScaleFactorConstants;

public class DriveTrainSubsystem extends SubsystemBase {

  private double startTime;
  private double driftPerSecond;

  private WPI_TalonFX frontLeft = new WPI_TalonFX(DrivetrainConstants.frontLeftTalonPort);
  private WPI_TalonFX frontRight = new WPI_TalonFX(DrivetrainConstants.frontRightTalonPort);
  private WPI_TalonFX backLeft = new WPI_TalonFX(DrivetrainConstants.backLeftTalonPort);
  private WPI_TalonFX backRight = new WPI_TalonFX(DrivetrainConstants.backRightTalonPort);

  public MecanumDrive mecDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

  private AHRS ahrs = new AHRS(Port.kUSB);
  private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  public DriveTrainSubsystem() {
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

    resetGyro();
    calibrate();
  }

  public double getGyroAngle() {
    double runTime = Timer.getFPGATimestamp() - startTime;
    double drift = runTime * driftPerSecond;
    return ahrs.getAngle() - drift;
  }

  public double getGyroRate() {
    return ahrs.getRate();
  }

  public void resetGyro() {
    ahrs.reset();
    gyro.reset();
    this.startTime = Timer.getFPGATimestamp();
  }

  public void calibrate() {
    this.startTime = Timer.getFPGATimestamp();
    double startAngle = ahrs.getAngle();
    try {
      Thread.sleep(5000);
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.driftPerSecond = (ahrs.getAngle() - startAngle) / (Timer.getFPGATimestamp() - startTime);

    gyro.calibrate();
  }

  public void gyroOnlyCalibrate() {
    gyro.calibrate();
  }

  public void resetEncoders() {
    frontLeft.setSelectedSensorPosition(0);
    frontRight.setSelectedSensorPosition(0);
    backLeft.setSelectedSensorPosition(0);
    backRight.setSelectedSensorPosition(0);
  }

  public double getPitch() {
    return ahrs.getPitch();
  }

  public double distanceToTicks(double distanceInches) {
    return (distanceInches / 6 * Math.PI) * 2048;
  }

  public void setMecanumPermanent(double x, double y, double rx) {
    mecDrive.driveCartesian(x, y, rx);
  }

  public void setMecanum(double x, double y, double rx) {
    if (Math.abs(x) < ScaleFactorConstants.driveDeadzone) x = 0;
    // y =
    //     -1
    //         * (DrivetrainConstants.maxDriveSpeed * (-scaleFactor + 1) / 2)
    //         * y;

    if (Math.abs(y) < ScaleFactorConstants.driveDeadzone) y = 0;

    // rx =
    //     -1
    //         * (DrivetrainConstants.maxTurnSpeed * (-scaleFactor + 1) / 2)
    //         * rx;

    if (Math.abs(rx) < ScaleFactorConstants.rotateDeadzone) rx = 0;

    mecDrive.driveCartesian(x, y, rx, gyro.getRotation2d().times(-1));

    // mecDrive.driveCartesian(x, y, rx);

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

  public double getYaw() {
    return ahrs.getYaw();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Front Left Encoder Distance", getDistance());
    SmartDashboard.putNumber("Gyro Get Angle", gyro.getAngle());
    SmartDashboard.putNumber("Gyro Get Pitch", getPitch());
  }

  @Override
  public void simulationPeriodic() {}
}
