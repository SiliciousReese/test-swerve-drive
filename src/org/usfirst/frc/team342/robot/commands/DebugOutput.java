package org.usfirst.frc.team342.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;

public class DebugOutput extends Command {
	
	private static final double DELAY_SEC = 0.5;

	/**
	 * Classes implementing this interface must have a debugging output method.
	 */
	public interface Debugable {

		/**
		 * This method should return useful information about the state of the
		 * physical robot. This method MUST NOT interfere with a subsystem, as
		 * the command that runs this method does not use the requires method.
		 */
		public String[] getDebug();
	}

	private Debugable debugables[];

	public DebugOutput(Debugable debugables[]) {
		this.debugables = debugables;
	}

	protected void initialize() {
		/* Try not to spam the drive station. */
		if (timeSinceInitialized() < DELAY_SEC) {
			cancel();
		}
	}

	protected void execute() {
		for (Debugable d : debugables) {
			for (String s : d.getDebug()) {
				FRCNetworkCommunicationsLibrary.HALSetErrorData(s);
			}
		}
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
