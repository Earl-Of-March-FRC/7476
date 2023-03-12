package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ScaleFactorConstants;
import frc.robot.subsystems.DrivetrainSubsystem;
import java.util.function.Supplier;

public class MecanumDriveCmd extends CommandBase {

  private DrivetrainSubsystem driveSubsystem;
  private Supplier<Double> forwardFunction, sideFunction, rotateFunction;

  public MecanumDriveCmd(
      DrivetrainSubsystem driveSubsystem,
      Supplier<Double> ff,
      Supplier<Double> sf,
      Supplier<Double> rf) {

    this.driveSubsystem = driveSubsystem;
    this.forwardFunction = ff;
    this.sideFunction = sf;
    this.rotateFunction = rf;

    addRequirements(driveSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {

    double speedForward, speedSide, speedRotate;
    speedForward = ScaleFactorConstants.driveScaleFactor * forwardFunction.get();

    if (Math.abs(speedForward) < ScaleFactorConstants.driveDeadzone) speedForward = 0;
    speedSide = -1 * ScaleFactorConstants.driveScaleFactor * sideFunction.get();

    if (Math.abs(speedSide) < ScaleFactorConstants.driveDeadzone) speedSide = 0;
    speedRotate = -1 * ScaleFactorConstants.turnScaleFactor * rotateFunction.get();

    if (Math.abs(speedRotate) < ScaleFactorConstants.rotateDeadzone) speedRotate = 0;

    driveSubsystem.setMecanum(speedForward, speedSide, speedRotate);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
