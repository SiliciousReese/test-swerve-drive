package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
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

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		drive.swerveDrive(joystick.getMagnitude(), joystick.getDirectionDegrees());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		drive.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
