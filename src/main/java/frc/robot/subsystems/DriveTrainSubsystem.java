package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.Constants.ScaleFactorConstants;

public class DriveTrainSubsystem extends SubsystemBase {

  private double startTime;
  private double driftPerSecondAHRS;
  private double driftPerSecondAHRS2;
  private double driftPerSecondGyro;
  private double driftPerSecondGyro2;
  private double point2Timestamp;

  private WPI_TalonFX frontLeft = new WPI_TalonFX(DrivetrainConstants.frontLeftTalonPort);
  private WPI_TalonFX frontRight = new WPI_TalonFX(DrivetrainConstants.frontRightTalonPort);
  private WPI_TalonFX backLeft = new WPI_TalonFX(DrivetrainConstants.backLeftTalonPort);
  private WPI_TalonFX backRight = new WPI_TalonFX(DrivetrainConstants.backRightTalonPort);

  public MecanumDrive mecDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

  private AHRS ahrs = new AHRS(Port.kUSB);
  // private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  // private Rotation2d rotationPose = new Rotation2d(0); // -hans

  // kinematics test

  public DriveTrainSubsystem() {
    frontLeft.setNeutralMode(NeutralMode.Brake);
    frontRight.setNeutralMode(NeutralMode.Brake);
    backLeft.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);

    frontLeft.setInverted(true);
    backLeft.setInverted(true);
    // ahrs.calibrate();
    // ahrs.reset();

    frontLeft.setSelectedSensorPosition(0);
    frontRight.setSelectedSensorPosition(0);
    backLeft.setSelectedSensorPosition(0);
    backRight.setSelectedSensorPosition(0);

    resetGyro();
    calibrate();
  }

  // public double getAHRSAngle() {
  //   // double runTime = Timer.getFPGATimestamp() - startTime;
  //   // double drift = runTime * driftPerSecondAHRS; - drift
  //   return ahrs.getAngle();
  // }

  public double getGyroAngle() {
    // double runTime = Timer.getFPGATimestamp() - startTime;
    // double drift = runTime * driftPerSecondGyro; - drift
    return ahrs.getAngle();
  }

  // public double getGyroRate() {
  //   return ahrs.getRate();
  // }

  public void resetGyro() {
    // ahrs.reset();
    ahrs.reset();
    // this.startTime = Timer.getFPGATimestamp();
  }

  public void calibrate() {
    this.startTime = Timer.getFPGATimestamp();
    double startAHRSAngle = ahrs.getAngle();
    // double startGyroAngle = gyro.getAngle();
    try {
      Thread.sleep(2500);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // this.driftPerSecondGyro2 =
    //     (gyro.getAngle() - startGyroAngle) / (Timer.getFPGATimestamp() - startTime);
    this.driftPerSecondAHRS2 =
        (ahrs.getAngle() - startAHRSAngle) / (Timer.getFPGATimestamp() - startTime);
    this.point2Timestamp = Timer.getFPGATimestamp();

    try {
      Thread.sleep(2500);
    } catch (Exception e) {
      e.printStackTrace();
    }

    this.driftPerSecondAHRS =
        (ahrs.getAngle() - startAHRSAngle) / (Timer.getFPGATimestamp() - startTime);

    // this.driftPerSecondGyro =
    //     (gyro.getAngle() - startGyroAngle) / (Timer.getFPGATimestamp()  - this.point2Timestamp);

    this.driftPerSecondGyro = (this.driftPerSecondAHRS + this.driftPerSecondAHRS2) / 2;
  }

  // public void gyroOnlyCalibrate() {
  //   gyro.calibrate();
  // }

  public void resetEncoders() {
    frontLeft.setSelectedSensorPosition(0);
    frontRight.setSelectedSensorPosition(0);
    backLeft.setSelectedSensorPosition(0);
    backRight.setSelectedSensorPosition(0);
  }

  // public double getPitch() {
  //   return ahrs.getPitch();
  // }

  public double distanceToTicks(double distanceInches) {
    return (distanceInches / 6 * Math.PI) * 2048;
  }

  public void setMecanumPermanent(double x, double y, double rx) {
    mecDrive.driveCartesian(x, y, rx);
  }

  public void setMecanum(double x, double y, double rx) {
    if (Math.abs(x) < ScaleFactorConstants.driveDeadzone) x = 0;
    // y =
    // -1
    // * (DrivetrainConstants.maxDriveSpeed * (-scaleFactor + 1) / 2)
    // * y;

    if (Math.abs(y) < ScaleFactorConstants.driveDeadzone) y = 0;

    // rx =
    // -1
    // * (DrivetrainConstants.maxTurnSpeed * (-scaleFactor + 1) / 2)
    // * rx;

    if (Math.abs(rx) < ScaleFactorConstants.rotateDeadzone) rx = 0;

    // if (!ahrs.isCalibrating()) {
    //   rotationPose = ahrs.getRotation2d(); //added by hans, this is so that we do not get the
    // values while calibrating
    // }
    mecDrive.driveCartesian(x, y, rx, ahrs.getRotation2d().times(-1));

    SmartDashboard.putNumber("x", x);
    SmartDashboard.putNumber("y", y);
    SmartDashboard.putNumber("rx", rx);
  }

  // mecDrive.driveCartesian(x, y, rx); Rotation2d.fromDegrees(getGyroAngle())

  // , ahrs.getRotation2d().rotateBy(Rotation2d.fromDegrees(-90))
  // frontLeft.set(y + x + rx);
  // backLeft.set(y - x + rx);
  // frontRight.set(y - x - rx);
  // backRight.set(y + x - rx);

  public double getDistance() {
    return (frontLeft.getSelectedSensorPosition() * 3.55 * Math.PI / 2048) / 10.71;
  }

  // public double getYaw() {
  //   return ahrs.getYaw();
  // }

  // public double getFusedHeading() {
  //   return ahrs.getFusedHeading(); // this is what hans added and should help with interference
  // if the other stuff doesn't work
  // }

  @Override
  public void periodic() {
    // if (!ahrs.isCalibrating()) {
    SmartDashboard.putNumber("Front Left Encoder Distance", getDistance());
    SmartDashboard.putNumber("Gyro Get Angle", ahrs.getAngle());
    SmartDashboard.putNumber("Gyro Yaw", (ahrs.getAngle() % 360));

    // SmartDashboard.putNumber("AHRS Get Angle", ahrs.getAngle());
    // SmartDashboard.putNumber("AHRS Get Pitch", getPitch());
    // SmartDashboard.putNumber("AHRS Get Fused", ahrs.getFusedHeading());
    // SmartDashboard.putNumber("rotationPose", rotationPose.getDegrees());

    // }
  }

  @Override
  public void simulationPeriodic() {}
}
