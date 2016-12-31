package org.usfirst.frc.team342.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

public class DebugOutput extends Command {
	private static final double DELAY_SEC = 0.5;

	private Debugable debugables[];

	/**
	 * Classes implementing this interface must have a debuging output method.
	 */
	public interface Debugable {

		/**
		 * This method should return useful information about the state of the
		 * physical robot. This method MUST NOT interfere with a subsystem, as
		 * the command that runs this method does not use the requires method.
		 */
		public String[] getDebug();
	}

	public DebugOutput(Debugable debugables[]) {
		this.debugables = debugables;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		/* Try not to spam drive station. */
		if (timeSinceInitialized() < DELAY_SEC) {
			cancel();
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		for (Debugable d : debugables) {
			for (String s : d.getDebug()) {
				FRCNetworkCommunicationsLibrary.HALSetErrorData(s);
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
