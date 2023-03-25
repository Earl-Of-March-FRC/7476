package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commandgroups.AutoCmds.ScoreTopLeaveFar;
import edu.wpi.first.cameraserver.CameraServer;


public class Robot extends TimedRobot {

  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  public static SendableChooser<Integer> auto_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    CameraServer.startAutomaticCapture();
    m_robotContainer = new RobotContainer();
    m_robotContainer.armMotors.armExtensionBrake();

    

    auto_chooser.setDefaultOption("Score Floor and Leave", 1);

    SmartDashboard.putData(auto_chooser);
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
    m_robotContainer.driveSubsystem.calibrateGyro();

    m_autonomousCommand =
        new ScoreTopLeaveFar(
            m_robotContainer.driveSubsystem, m_robotContainer.armMotors, m_robotContainer.claw, 35, 80);

    // // schedule the autonomous command (example)
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
  
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
