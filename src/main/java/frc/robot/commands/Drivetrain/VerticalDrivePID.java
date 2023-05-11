// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands.Drivetrain;

// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.wpilibj2.command.PIDCommand;
// import frc.robot.subsystems.DriveTrainSubsystem;
// import frc.robot.subsystems.VisionSubsystem;

// // NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// // information, see:
// // https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
// public class VerticalDrivePID extends PIDCommand {

//   VisionSubsystem visionSubsystem;

//   public VerticalDrivePID(
//       DriveTrainSubsystem drivetrain, double setpoint) {
//     super(
//         // The controller that the command will use
//         new PIDController(0.02, 0.02, 0.001), // 0.015
//         // This should return the measurement
//         () -> drivetrain.getPitch(),
//         // This should return the setpoint (can also be a constant)
//         () -> setpoint,
//         // This uses the output
//         output -> {
//           drivetrain.setMecanum(-output, 0, 0);
//         });
//     addRequirements(drivetrain);
//     getController().setTolerance(1);
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return m_controller.atSetpoint();
//   }
// }
