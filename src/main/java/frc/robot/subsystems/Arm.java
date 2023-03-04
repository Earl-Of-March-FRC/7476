package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {

  private WPI_VictorSPX extension1 = new WPI_VictorSPX(ArmConstants.extension1);

  private WPI_TalonSRX incline1 = new WPI_TalonSRX(ArmConstants.incline1);
  private WPI_TalonSRX incline2 = new WPI_TalonSRX(ArmConstants.incline2);

  AHRS gyro = new AHRS(Port.kOnboard);

  public Arm() {
    incline2.setSelectedSensorPosition(25220);
    gyro.reset();
    gyro.calibrate();
  }

  public double getInclineEncoder() {
    return incline2.getSelectedSensorPosition();
  }

  public void setEncoderValueIncline(double ticks) {
    incline1.set(ControlMode.Position, ticks);
    incline2.set(ControlMode.Position, ticks);
  }

  public void resetInclineEncoders() {
    incline1.setSelectedSensorPosition(0);
    incline2.setSelectedSensorPosition(0);
  }

  public void setVoltageExtension(double voltage) {
    extension1.setVoltage(voltage);
  }

  public void armExtension(double speed) {
    extension1.set(-speed);
  }

  public void armExtensionBrake() {
    extension1.setNeutralMode(NeutralMode.Brake);
    incline1.setNeutralMode(NeutralMode.Brake);
    incline2.setNeutralMode(NeutralMode.Brake);
  }

  public void armIncline(double speed) {
    incline1.set(speed);
    incline2.set(-speed);
  }

  public void extendStop() {
    extension1.set(0);
  }

  public void inclineStop() {
    incline1.set(0);
    incline2.set(0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Incline 1", incline1.getSelectedSensorPosition());
    SmartDashboard.putNumber("Incline 2", incline2.getSelectedSensorPosition());
    

    SmartDashboard.putNumber("Encoder Based Angle", getInclineEncoder() / (25220 / ArmConstants.armMaxAngle));
  }
}
