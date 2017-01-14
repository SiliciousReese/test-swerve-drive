package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Control the robot movement with the joystick using swerve drive.
 */
public class SwerveDriveWithJoystick extends Command {

	/* The number of the joystick in the drive station. */
	private static final int JOY_NUM = 0;
	
	private static final double DEADZONE = 0.2;

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
            double angle = joystick.getDirectionRadians() / (Math.PI);

	    if (joystick.getMagnitude() >= DEADZONE) {
		drive.swerveDrive(joystick.getMagnitude() / 5.0, angle);
		SmartDashboard.putNumber("Set-Angle", angle);
	    } else {
		drive.stop();
		SmartDashboard.putNumber("Set-Angle", angle);
	    }
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
