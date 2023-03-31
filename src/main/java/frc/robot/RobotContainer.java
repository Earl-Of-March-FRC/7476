package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.DriverStationConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commandgroups.AutoCmds.Balance;
import frc.robot.commandgroups.AutoCmds.ScoreTopLeaveClose;
import frc.robot.commandgroups.TeleopArm.ArmDefaultPosition;
import frc.robot.commandgroups.TeleopArm.ArmLoadPieces;
import frc.robot.commandgroups.TeleopArm.ArmPlaceLow;
import frc.robot.commandgroups.TeleopArm.ArmPlaceMid;
import frc.robot.commandgroups.TeleopArm.ArmPlaceTop;
import frc.robot.commands.Arm.ArmControl;
import frc.robot.commands.CancelAutomation;
import frc.robot.commands.ClawControl;
import frc.robot.commands.Drivetrain.MecanumDriveCmd;
import frc.robot.commands.GyroReset;
// import frc.robot.commandgroups.AutoCmds.ScoreFloorAndBalance;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class RobotContainer {

  final DrivetrainSubsystem driveSubsystem = new DrivetrainSubsystem();
  public final Arm armMotors = new Arm();
  public final Claw claw = new Claw();

  public final VisionSubsystem vision = new VisionSubsystem();
  public final LEDSubsystem led = new LEDSubsystem();

  public final Joystick controller = new Joystick(OperatorConstants.kDriverControllerPort);

  public final Joystick driverStation =
      new Joystick(DriverStationConstants.DriverStationController);

  public Command armPlaceTopCommand = new ArmPlaceTop(armMotors, led);
  public Command armPlaceMidCommand = new ArmPlaceMid(armMotors, led);
  public Command armPlaceLowCommand = new ArmPlaceLow(armMotors, led);
  public Command armdefaultCommand = new ArmDefaultPosition(armMotors, led);
  public Command armLoadCmd = new ArmLoadPieces(armMotors, led);

  public RobotContainer() {

    driveSubsystem.setDefaultCommand(
        new MecanumDriveCmd(
            driveSubsystem,
            () -> controller.getRawAxis(OperatorConstants.sideAxis),
            () -> controller.getRawAxis(OperatorConstants.forwardAxis),
            () -> controller.getRawAxis(OperatorConstants.rotationAxis),
            () -> controller.getRawAxis(OperatorConstants.scaleAxis)));

    armMotors.setDefaultCommand(
        new ArmControl(
            armMotors,
            () -> driverStation.getRawAxis(DriverStationConstants.ArmExtendAxis),
            () -> driverStation.getRawAxis(DriverStationConstants.ArmInclineAxis)));

    configureBindings();
  }

  private void configureBindings() {

    // Open Claw
    new JoystickButton(driverStation, DriverStationConstants.ClawOpenButton)
        .whileTrue(new ClawControl(claw, -1));

    // Close Claw
    new JoystickButton(driverStation, DriverStationConstants.ClawCloseButton)
        .whileTrue(new ClawControl(claw, 1));

    // top position
    new JoystickButton(driverStation, 4).onTrue(armPlaceTopCommand);

    // middle position
    new JoystickButton(driverStation, 2).onTrue(armPlaceMidCommand);

    // Low position
    new JoystickButton(driverStation, 1).onTrue(armPlaceLowCommand);

    // default position
    new JoystickButton(driverStation, 7).onTrue(armdefaultCommand);

    // reset gyro
    new JoystickButton(controller, 7).onTrue(new GyroReset(driveSubsystem));

    // cancel automation
    new JoystickButton(driverStation, 8)
        .onTrue(
            new CancelAutomation(
                armMotors,
                led,
                armPlaceTopCommand,
                armPlaceMidCommand,
                armPlaceLowCommand,
                armdefaultCommand,
                armLoadCmd));

    // Loading position arm
    new JoystickButton(driverStation, 3).onTrue(armLoadCmd);

    // Balance testing
    new JoystickButton(controller, 2).onTrue(new Balance(driveSubsystem));
  }

  public Command getAutonomousCommand() {
    // switch(cycle){
    //   case 1:
    //     new Leave(driveSubsystem);
    //   case 2:
    //     new ScoreFloorLeave(armMotors, driveSubsystem, claw);
    //   default:
    //     break;
    // }
    // return null;
    return new ScoreTopLeaveClose(driveSubsystem, armMotors, claw);
  }
}
