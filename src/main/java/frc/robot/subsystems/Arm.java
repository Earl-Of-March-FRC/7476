package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {

  private WPI_TalonSRX extension1 = new WPI_TalonSRX(ArmConstants.extension1);
  // private WPI_TalonSRX extension2 = new WPI_TalonSRX(ArmConstants.extension2);

  private WPI_TalonSRX incline1 = new WPI_TalonSRX(ArmConstants.incline1);
  private WPI_TalonSRX incline2 = new WPI_TalonSRX(ArmConstants.incline2);

  // private MotorControllerGroup extension = new MotorControllerGroup(extension1, extension2);

  private Encoder extensionEncoder = new Encoder(0, 1); // sample pin numbers

  private Encoder angleEncoder =
      new Encoder(
          Constants.ArmConstants.angleEncoderChannelA, Constants.ArmConstants.angleEncoderChannelB);

  public Arm() {
    angleEncoder.setDistancePerPulse(Constants.ArmConstants.anglePerTick);
  }

  public double getAngleEncoderDistance() {
    return angleEncoder.getDistance();
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

  public void resetExtensionEncoder() {
    extensionEncoder.reset();
  }

  public double getExtensionEncoder() {
    return extensionEncoder.getRaw();
  }

  public void armExtension(double speed) {
    extension1.set(speed);
  }

  // public void armIn(double speed){
  // extension1.set(0.1);
  // }

  public void armExtensionBrake() {
    extension1.setNeutralMode(NeutralMode.Brake);
    incline1.setNeutralMode(NeutralMode.Brake);
    incline2.setNeutralMode(NeutralMode.Brake);
  }

  public void armIncline(double speed) {
    incline1.set(speed);
  }

  // public void armDown(double speed){
  // incline1.set(speed/3);
  // }

  public void armStop() {
    extension1.set(0);
  }

  public void armStop2() {
    incline1.set(0);
  }

  @Override
  public void periodic() {}
}
