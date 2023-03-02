package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commandgroups.PickUp;
import frc.robot.commandgroups.PlaceConeTop.ConePlaceTop;
import frc.robot.commandgroups.PlaceCubeTop.CubePlaceTop;
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
  public final JoystickButton clawOpen = new JoystickButton(controller, 2);
  public JoystickButton clawClose = new JoystickButton(controller, 1);

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
    new JoystickButton(controller, OperatorConstants.cubePickUpButton).whileTrue(new PickUp(armMotors, claw, ArmConstants.clawCloseCube, vision));
    new JoystickButton(controller, OperatorConstants.conePickUpButton).whileTrue(new PickUp(armMotors,claw,ArmConstants.clawCloseCone, vision));
    new JoystickButton(controller, OperatorConstants.cubePlaceTopButton).whileTrue(new CubePlaceTop(claw, armMotors));
    new JoystickButton(controller, OperatorConstants.conePlaceTopButton).whileTrue(new ConePlaceTop(claw, armMotors));
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
