package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.DriverStationConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Arm.ArmDown;
import frc.robot.commands.Arm.ArmIn;
import frc.robot.commands.Arm.ArmOut;
import frc.robot.commands.Arm.ArmUp;
import frc.robot.commands.ClawControl;
import frc.robot.commands.Drivetrain.MecanumDriveCmd;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class RobotContainer {

  private final DrivetrainSubsystem driveSubsystem = new DrivetrainSubsystem();
  public final Arm armMotors = new Arm();
  public final Claw claw = new Claw();

  public final VisionSubsystem vision = new VisionSubsystem();

  public final Joystick controller =
      new Joystick(Constants.OperatorConstants.kDriverControllerPort);

  public final Joystick driverStation =
      new Joystick(DriverStationConstants.DriverStationController);

  public RobotContainer() {

    driveSubsystem.setDefaultCommand(
        new MecanumDriveCmd(
            driveSubsystem,
            () -> controller.getRawAxis(OperatorConstants.forwardAxis),
            () -> controller.getRawAxis(OperatorConstants.sideAxis),
            () -> controller.getRawAxis(OperatorConstants.rotationAxis)));

    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(driverStation, DriverStationConstants.leftThumb)
        .whileTrue(new ArmDown(armMotors));
    new JoystickButton(driverStation, DriverStationConstants.leftIndex)
        .whileTrue(new ArmIn(armMotors));
    new JoystickButton(driverStation, DriverStationConstants.leftMiddle)
        .whileTrue(new ClawControl(claw, -1));

    new JoystickButton(driverStation, DriverStationConstants.rightThumb)
        .whileTrue(new ArmUp(armMotors));
    new JoystickButton(driverStation, DriverStationConstants.rightIndex)
        .whileTrue(new ArmOut(armMotors));
    new JoystickButton(driverStation, DriverStationConstants.rightMiddle)
        .whileTrue(new ClawControl(claw, 1));
  }

  public Command getAutonomousCommand(int cycle) {
    // switch(cycle){
    //   case 1:
    //     return new DropAndStop(armMotors, claw);
    //   case 2:
    //     return new DropAndLeave();
    //   default:
    //     return new DropAndStop(armMotors, claw);
    // }

    return null;
  }
}
