package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwerveDriveWithJoystick extends Command {

	private DriveSystem drive;
	private Joystick joystick;
	
    public SwerveDriveWithJoystick() {
    	drive = DriveSystem.getInstance();
    	joystick = new Joystick(0);
		requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double power = joystick.getMagnitude();
    	double angle = joystick.getDirectionDegrees();
    	
    	drive.swerveDrive(power, angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
