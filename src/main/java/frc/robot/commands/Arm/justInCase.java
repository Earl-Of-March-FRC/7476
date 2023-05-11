// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands.Arm;

// import edu.wpi.first.math.controller.ProfiledPIDController;
// import edu.wpi.first.math.trajectory.TrapezoidProfile;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.Arm;
// import frc.robot.subsystems.LEDSubsystem;

// public class justInCase extends CommandBase {

//   ProfiledPIDController controller;

//   Arm arm;
//   double angleSetpoint;

//   public justInCase(Arm arm, double angleSetpoint) {
//     this.arm = arm;
//     this.angleSetpoint = angleSetpoint;
//     addRequirements(arm);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     controller = new ProfiledPIDController(
//       // The PID gains
//       0.55,
//       0,
//       0,
//       // The motion profile constraints
//       new TrapezoidProfile.Constraints(60, 90));

//       controller.setGoal(angleSetpoint);
//       controller.setTolerance(1);

//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     arm.armIncline(controller.calculate(arm.getInclineAngle(), angleSetpoint));
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {}

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return controller.atGoal();
//   }
// }
