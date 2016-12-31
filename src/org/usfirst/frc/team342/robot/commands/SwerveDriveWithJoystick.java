package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Control the robot movement with the joystick using swerve drive.
 */
public class SwerveDriveWithJoystick extends Command {

	/* The number of the joystick in the drive station. */
	private static final int JOY_NUM = 0;

	private DriveSystem drive;

	private Joystick joystick;

	public SwerveDriveWithJoystick(DriveSystem drive) {
		this.drive = drive;
		joystick = new Joystick(JOY_NUM);
		requires(drive);
	}

	protected void initialize() {
	}

	protected void execute() {
		double angle = joystick.getDirectionRadians() / (2.0 * Math.PI);
		drive.swerveDrive(joystick.getMagnitude(), angle);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		drive.stop();
	}

	protected void interrupted() {
	}
}
