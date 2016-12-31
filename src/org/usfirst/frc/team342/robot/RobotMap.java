package org.usfirst.frc.team342.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	/*
	 * FR, FL, RR, and RL are abreviations. The first letter is front or rear.
	 * The second letter is right or left.
	 */

	public static final int FR_DRIVE_TALON = 1;
	public static final int FL_DRIVE_TALON = 2;
	public static final int RR_DRIVE_TALON = 3;
	public static final int RL_DRIVE_TALON = 4;

	public static final int FR_ANGLE_TALON = 5;
	public static final int FL_ANGLE_TALON = 6;
	public static final int RR_ANGLE_TALON = 7;
	public static final int RL_ANGLE_TALON = 8;
}
