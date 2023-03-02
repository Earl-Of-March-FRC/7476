// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commandgroups;

// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import frc.robot.commands.Drivetrain.BalanceChargeStation;
// import frc.robot.commands.Drivetrain.MecanumDriveCmd;
// import frc.robot.subsystems.DrivetrainSubsystem;

// // NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// // information, see:
// // https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
// public class BalanceStation extends SequentialCommandGroup {
//   /** Creates a new BalanceStation. */
//   public BalanceStation(DrivetrainSubsystem drive) {
//     // Add your commands in the addCommands() call, e.g.
//     // addCommands(new FooCommand(), new BarCommand());
//     addCommands(
//         ((Command)
//                 new MecanumDriveCmd(drive, () -> 0.2, () -> 0.0, () -> 0.0).until(drive.getGyroPitch() > 10))
//             .andThen(new BalanceChargeStation(drive)));
//   }
// }
