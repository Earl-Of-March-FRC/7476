package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commandgroups.AutoCmds.ScoreTopBalance;
import frc.robot.commandgroups.AutoCmds.ScoreTopLeaveFar;

public class Robot extends TimedRobot {

  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  // SendableChooser<Command> auto = new SendableChooser<Command>();

  @Override
  public void robotInit() {
    CameraServer.startAutomaticCapture();
    m_robotContainer = new RobotContainer();

    m_robotContainer.armMotors.armExtensionBrake();
    m_robotContainer.armMotors.resetEncoders();
    m_robotContainer.driveSubsystem.resetEncoders();

    // auto.setDefaultOption("ScoreTopLeaveFar", new ScoreTopLeaveFar(
    //   m_robotContainer.driveSubsystem,
    //   m_robotContainer.armMotors,
    //   m_robotContainer.claw, 35, 80));

    // auto.addOption("ScoreTopBalance", new ScoreTopBalance(
    //   m_robotContainer.driveSubsystem,
    //   m_robotContainer.armMotors,
    //   m_robotContainer.claw,
    //   35,
    //   80));

  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    m_robotContainer.armMotors.resetEncoders();
    m_robotContainer.driveSubsystem.resetEncoders();
    m_robotContainer.driveSubsystem.resetGyro();

    m_autonomousCommand =
        new ScoreTopBalance(
            m_robotContainer.driveSubsystem,
            m_robotContainer.armMotors,
            m_robotContainer.claw,
            35,
            76);

    // // schedule the autonomous co mmand (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {

    m_robotContainer.armMotors.armExtensionBrake();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    // m_robotContainer.armMotors.resetEncoders();
    // m_robotContainer.driveSubsystem.resetEncoders();
    // m_robotContainer.driveSubsystem.resetGyro();
    // m_robotContainer.driveSubsystem.calibrateGyro();

  }

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("TX", m_robotContainer.vision.getTX());
    SmartDashboard.putNumber("TY", m_robotContainer.vision.getTY());
    SmartDashboard.putNumber("Yaw", m_robotContainer.driveSubsystem.getYaw());
    SmartDashboard.putNumber("Pipe", m_robotContainer.vision.getPipeline());
  }

  @Override
  public void testInit() {
    // m_robotContainer.armMotors.resetEncoders();
    // m_robotContainer.driveSubsystem.resetEncoders();
    // m_robotContainer.driveSubsystem.resetGyro();
    // m_robotContainer.driveSubsystem.calibrateGyro();

    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
