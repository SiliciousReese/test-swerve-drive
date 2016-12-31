package org.usfirst.frc.team342.robot.subsystems;

//import org.usfirst.frc.team342.robot.Debugable;
import org.usfirst.frc.team342.robot.RobotMap;
import org.usfirst.frc.team342.robot.commands.DebugOutput;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDeviceStatus;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSystem extends Subsystem implements DebugOutput.Debugable {

	/** The motors to control the speed for driving. */
	private final CANTalon driveMotors[];

	/** The motors to control the angle of the wheels. */
	private final CANTalon positionMotors[];

	public DriveSystem() {

		/*
		 * The order for both of these arrays should be front_right (FR),
		 * front_left (FL), rear_right (RR), then rear_left.
		 */

		driveMotors = new CANTalon[4];
		driveMotors[0] = new CANTalon(RobotMap.FR_DRIVE_TALON);
		driveMotors[1] = new CANTalon(RobotMap.FL_DRIVE_TALON);
		driveMotors[2] = new CANTalon(RobotMap.RR_DRIVE_TALON);
		driveMotors[3] = new CANTalon(RobotMap.RL_DRIVE_TALON);

		positionMotors = new CANTalon[4];
		positionMotors[0] = new CANTalon(RobotMap.FR_ANGLE_TALON);
		positionMotors[1] = new CANTalon(RobotMap.FL_ANGLE_TALON);
		positionMotors[2] = new CANTalon(RobotMap.RR_ANGLE_TALON);
		positionMotors[3] = new CANTalon(RobotMap.RL_ANGLE_TALON);

		for (int i = 0; i < driveMotors.length; i++) {
			/*
			 * Makes accurate movement simpler if the codes per revolution is
			 * accurate.
			 */
			// positionMotors[i].configEncoderCodesPerRev(4096);

			// The robot is assumed to be started with the arm at the 0 position
			// positionMotors[i].setEncPosition(0);

			// set the peak and nominal outputs, 12V means full
			positionMotors[i].configNominalOutputVoltage(+0f, -0f);
			positionMotors[i].configPeakOutputVoltage(+3f, -3f);

			// set the allowable closed-loop error, Closed-Loop output will be
			// neutral within this range.
			positionMotors[i].setAllowableClosedLoopErr(5);

			// set closed loop gains in slot0
			positionMotors[i].setProfile(0);
			// We do not use feed forward
			positionMotors[i].setF(0.0);
			// Proportional controls how fast the arm moves
			positionMotors[i].setP(0.05);
			// We do not use integral
			positionMotors[i].setI(0.0);
			// Derivative prevents it from going to far
			positionMotors[i].setD(0.00);

			positionMotors[i].setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
			positionMotors[i].changeControlMode(TalonControlMode.Position);

		}

		// TODO Use talon class for tying motors together, currently the same
		// value is passed to all motors.
	}

	/** Power is magnitude of joystick. Angle in radians. */
	public void swerveDrive(double power, double angle) {
		for (int i = 0; i < driveMotors.length; i++) {
			/* Guarantee mode is correct for position motors. */
			positionMotors[i].changeControlMode(TalonControlMode.Position);

			driveMotors[i].enable();
			positionMotors[i].enable();

			/*
			 * The set() method works differently depending on the mode the
			 * motor is in. For more information see CANTalon documentation and
			 * the CTRE software reference manual.
			 */
			driveMotors[i].set(power);
			positionMotors[i].set(angle);
		}
	}
	
	public void stop() {
		for (CANTalon driveMotor: driveMotors) {
			driveMotor.set(0);
		}
		
		for (CANTalon positionMotor: positionMotors) {
			positionMotor.disable();
		}
	}

	/**
	 * Currently, there are five elements in the array and they are all from the
	 * sensor on the front right position motor. element 0 is the pulse width
	 * position, 1 is the rise to fall time in microseconds, 2 is the period in
	 * microseconds, 3 is the pulse width velocity, and 4 is the status of the
	 * sensor (true if the sensor is present).
	 */
	public String[] getDebug() {
		String[] output = new String[5];

		Integer pulseWidthPos = new Integer(positionMotors[0].getPulseWidthPosition());
		output[0] = "Talon Mag encoder FR Pulse width position: " + pulseWidthPos.toString() + "\n";

		Integer pulseWidthUs = new Integer(positionMotors[0].getPulseWidthRiseToFallUs());
		output[1] = "Talon Mag encoder FR Pulse width microseconds: " + pulseWidthUs.toString() + "\n";

		Integer periodUs = new Integer(positionMotors[0].getPulseWidthRiseToRiseUs());
		output[2] = "Talon Mag encoder FR period: " + periodUs.toString() + "\n";

		Integer pulseWidthVel = new Integer(positionMotors[0].getPulseWidthVelocity());
		output[3] = "Talon Mag encoder FR pulse width velocity: " + pulseWidthVel.toString() + "\n";

		FeedbackDeviceStatus sensorStatus = positionMotors[0].isSensorPresent(FeedbackDevice.CtreMagEncoder_Absolute);
		Boolean sensorPluggedIn = new Boolean(FeedbackDeviceStatus.FeedbackStatusPresent == sensorStatus);
		output[4] = "Talon Mag encoder FR is sensor plugged in: " + sensorPluggedIn.toString() + "\n";

		return output;
	}

	public void initDefaultCommand() {

	}
}
