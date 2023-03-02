package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.VisionConstants;
import frc.robot.commandgroups.TeleopHelperCmds.ClawControlGroup;
import frc.robot.commands.Arm.AutoArmIncline;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.VisionSubsystem;


public class PickUp extends SequentialCommandGroup {
  /** Creates a new PickCone. */
  public PickUp(Arm armMotors, Claw claw, int goal, VisionSubsystem vision) {

    if(goal == 2){vision.setPipeline(VisionConstants.conePipeline);}
    else if(goal == 1){vision.setPipeline(VisionConstants.cubePipeline);}

    addCommands(
        new AutoArmIncline(armMotors, ArmConstants.armLowestPositionIncline)
            .andThen(new ClawControlGroup(claw, goal))
            .andThen(new AutoArmIncline(armMotors, ArmConstants.armDefaultIncline)));
  }
}
