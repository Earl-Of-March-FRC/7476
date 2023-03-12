// package frc.robot.commandgroups.AutoCmds;

// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import frc.robot.commands.Arm.ArmDownAuto;
// import frc.robot.commands.Arm.ClawOpenAuto;
// import frc.robot.commands.Drivetrain.VerticalDrivePID;
// import frc.robot.subsystems.Arm;
// import frc.robot.subsystems.Claw;
// import frc.robot.subsystems.DrivetrainSubsystem;
// // NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// // information, see:
// // https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

// public class ScoreFloorAndBalance extends SequentialCommandGroup {
//   /** Creates a new ScoreFloorAndBalance. */
//   public ScoreFloorAndBalance(Arm armMotors, Claw claw, DrivetrainSubsystem drivetrainSubsystem)
// {
//     addCommands(
//         new ArmDownAuto(armMotors),
//         new ClawOpenAuto(claw),
//         new VerticalDrivePID(drivetrainSubsystem, -156));
//     // new BalanceStation(drivetrainSubsystem));
//   }
// }
