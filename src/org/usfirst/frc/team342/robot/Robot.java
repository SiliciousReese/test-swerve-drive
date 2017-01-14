package org.usfirst.frc.team342.robot;

import org.usfirst.frc.team342.robot.commands.DebugOutput;
import org.usfirst.frc.team342.robot.commands.DebugOutput.Debugable;
import org.usfirst.frc.team342.robot.commands.SwerveDriveWithJoystick;
import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	private DebugOutput.Debugable debugableSystems[];

	/**
	 * Robot.java is the only class that runs the drivessytem constructor, all
	 * other classes that need access to the drive system must have it given to
	 * them by this class.
	 */
	private DriveSystem drive;
	private SwerveDriveWithJoystick swerveDriveCommand;
	
	private DebugOutput debug;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		drive = new DriveSystem();

		/*
		 * All systems that debugging info should come from go into this array.
		 */
		debugableSystems = new DebugOutput.Debugable[1];
		debugableSystems[0] = drive;

		OI.setupButtons(debugableSystems);

		swerveDriveCommand = new SwerveDriveWithJoystick(drive);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	/** Reinitializes the teleop drive command every time teleop is started. */
	public void teleopInit() {
		swerveDriveCommand = new SwerveDriveWithJoystick(drive);
		Debugable[] debugables = {drive};
		debug = new DebugOutput(debugables);
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		swerveDriveCommand.start();
		debug.start();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
