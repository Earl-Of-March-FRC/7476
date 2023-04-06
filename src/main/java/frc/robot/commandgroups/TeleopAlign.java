// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Drivetrain.GyroTurnAnglePID;
import frc.robot.commands.Drivetrain.HorizontalDrivePID;
import frc.robot.commands.LEDGreen;
import frc.robot.commands.LEDRainbow;
import frc.robot.commands.LEDTurnOff;
import frc.robot.commands.Limelight.SetPipeline;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TeleopAlign extends SequentialCommandGroup {
  /** Creates a new TeleopAlign. */
  public TeleopAlign(
      VisionSubsystem vision, DriveTrainSubsystem drive, LEDSubsystem led, int pipeline) {
    addCommands(new SetPipeline(vision, pipeline));
    if(vision.getTY() == 0){
      for(int i = 0; i <= 25; i++){
        addCommands(new LEDTurnOff(led).withTimeout(0.3), new LEDRainbow(led).withTimeout(0.3), new LEDRainbow(led).withTimeout(0.15), new LEDTurnOff(led).withTimeout(0.15), new LEDRainbow(led).withTimeout(0.15));   
      }
    }
    else{
      addCommands(new GyroTurnAnglePID(drive, 0));

    if (pipeline == 0) {
      addCommands(
          new HorizontalDrivePID(drive, vision, -11.5),
          new GyroTurnAnglePID(drive, 0),
          new HorizontalDrivePID(drive, vision, -11.5));
    } else if (pipeline == 1) {
      addCommands(
          new HorizontalDrivePID(drive, vision, -10),
          new GyroTurnAnglePID(drive, 0),
          new HorizontalDrivePID(drive, vision, -10));
    }

    addCommands(new LEDRainbow(led));
    }
  }

  
}
