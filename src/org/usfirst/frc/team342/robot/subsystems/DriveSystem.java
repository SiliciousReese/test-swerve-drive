package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import com.ni.vision.NIVision.CalibrationThumbnailType;

import edu.wpi.first.wpilibj.CANSpeedController.ControlMode;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSystem extends Subsystem {
    
	/** Drive wheels. */
	//private final CANTalon frontRightWheel;
	//private final CANTalon frontLeftWheel;
	//private final CANTalon backRightWheel;
	//private final CANTalon backLeftWheel;

	/** Drive anglers. */
	//private final CANTalon frontRightAngle;
	//private final CANTalon frontLeftAngle;
	//private final CANTalon backRightAngle;
	//private final CANTalon backLeftAngle;

	private final CANTalon driveMotors[];
	private final CANTalon positionMotors[];

	private static final DriveSystem instance = new DriveSystem();

		// Put methods for controlling this subsystem
		// here. Call these from Commands.
		
	private DriveSystem() {
	 
		/** Drive anglers. */
		
		driveMotors = new CANTalon[4];
		driveMotors[0] = new CANTalon(RobotMap.FRONT_RIGHT_DRIVE_TALON);
		driveMotors[1] = new CANTalon(RobotMap.FRONT_LEFT_DRIVE_TALON);
		driveMotors[2] = new CANTalon(RobotMap.BACK_RIGHT_DRIVE_TALON);
		driveMotors[3] = new CANTalon(RobotMap.BACK_LEFT_DRIVE_TALON);
		
		positionMotors = new CANTalon[4];
		positionMotors[0] = new CANTalon(RobotMap.FRONT_RIGHT_ANGLE_TALON);
		positionMotors[1] = new CANTalon(RobotMap.FRONT_LEFT_ANGLE_TALON);
		positionMotors[2] = new CANTalon(RobotMap.BACK_RIGHT_ANGLE_TALON);
		positionMotors[3] = new CANTalon(RobotMap.BACK_LEFT_ANGLE_TALON);
		
		for (int i = 0; i < driveMotors.length; i++) {
			// Makes accurate movement simpler if the codes per revolution is
			// accurate.
			positionMotors[i].configEncoderCodesPerRev(3000);

			// The robot is assumed to be started with the arm at the 0 position
			//positionMotors[i].setEncPosition(0);

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
			positionMotors[i].setP(0.1);
			// We do not use integral
			positionMotors[i].setI(0.0);
			// Derivative prevents it from going to far
			positionMotors[i].setD(0.00);

			positionMotors[i].setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
			positionMotors[i].changeControlMode(TalonControlMode.Speed);
		}
		
		// TODO Use talon class for tying motors together, currently the same value is passed to all motors.
	}
	
	public static DriveSystem getInstance() {
		return instance;
	}

	/** Power is magnitude of joystick. Angle in radians. */
	public void swerveDrive(double power, double angle) {
		for (int i = 0; i < driveMotors.length; i++) {
			driveMotors[i].enable();
			driveMotors[i].set(power);
			positionMotors[i].set(angle);
		
			/* TODO anglemotor vs positionmotor name consistency. */
			/* Works differently depending on the mode the motor is in, THE ANLGE
			 MOTORS SHOULD BE IN POSITION MODE. */
		}

	}

    public void initDefaultCommand() {

    }
}

