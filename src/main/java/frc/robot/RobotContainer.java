package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.DriverStationConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commandgroups.AutoCmds.Leave;
import frc.robot.commands.Arm.ArmControl;
import frc.robot.commands.ClawControl;
import frc.robot.commands.Drivetrain.MecanumDriveCmd;
import frc.robot.commands.Drivetrain.ScaleButtonCmd;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class RobotContainer {

  private final DrivetrainSubsystem driveSubsystem = new DrivetrainSubsystem();
  public final Arm armMotors = new Arm();
  public final Claw claw = new Claw();

  public final VisionSubsystem vision = new VisionSubsystem();

  public final Joystick controller = new Joystick(3);

  public final Joystick driverStation =
      new Joystick(DriverStationConstants.DriverStationController);

  public RobotContainer() {

    driveSubsystem.setDefaultCommand(
        new MecanumDriveCmd(
            driveSubsystem,
            () -> controller.getRawAxis(OperatorConstants.forwardAxis),
            () -> controller.getRawAxis(OperatorConstants.sideAxis),
            () -> controller.getRawAxis(OperatorConstants.rotationAxis)));

    armMotors.setDefaultCommand(
        new ArmControl(
            armMotors,
            () -> driverStation.getRawAxis(DriverStationConstants.ArmExtendAxis),
            () -> driverStation.getRawAxis(DriverStationConstants.ArmInclineAxis)));

    configureBindings();
  }

  private void configureBindings() {

    new JoystickButton(driverStation, DriverStationConstants.ClawOpenButton)
        .whileTrue(new ClawControl(claw, -1));
    new JoystickButton(driverStation, DriverStationConstants.ClawCloseButton)
        .whileTrue(new ClawControl(claw, 1));

    new JoystickButton(controller, 8).toggleOnTrue(new ScaleButtonCmd());

    // new JoystickButton(controller, 1).onTrue(new VerticalDrivePID(driveSubsystem, 60));
  }

  public Command getAutonomousCommand(int cycle) {
    // switch(cycle){
    //   case 1:
    //     driveSubsystem.setMecanum(-1.0,0.0,0.0);
    //     new WaitCommand(1.5);
    //     driveSubsystem.setMecanum(0,0,0);
    //   default:
    //     break;
    // }

    return new Leave(driveSubsystem);
    // among us
  }
}
