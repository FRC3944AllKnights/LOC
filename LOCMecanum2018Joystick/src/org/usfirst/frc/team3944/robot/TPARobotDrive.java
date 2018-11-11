package org.usfirst.frc.team3944.robot;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
 
// Main Class Body
public class TPARobotDrive extends MecanumDrive {
	public double m_magnitude;
	public double m_direction;
	public double m_rotation;
	// This is an object declaration that sets a named location in memory. It is a joystick 
	// object of type TPAJoystick. Or a reference variable of type TPAJoystick. 
	// "final" tells the compiler that subclass cannot override.
	private final TPAJoystick j ;
	private AHRS gyro;
	// Instantiation of public static CANTalon Object
	
	public static WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(TPARobotMap.frontLeftCAN_ID);
	public static WPI_TalonSRX backLeftMotor = new WPI_TalonSRX(TPARobotMap.backLeftCAN_ID);
	public static WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(TPARobotMap.frontRightCAN_ID);
	public static WPI_TalonSRX backRightMotor = new WPI_TalonSRX(TPARobotMap.backRightCAN_ID);
	
	// Constructor
	public TPARobotDrive(WPI_TalonSRX frontLeftMotor, WPI_TalonSRX backLeftMotor, WPI_TalonSRX frontRightMotor, WPI_TalonSRX backRightMotor, TPAJoystick j, AHRS gyro) {
		super(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
		this.j=j;
		this.gyro = gyro;
	}

	    /* The value passed to getRawAxis is the axis number from the joystick
	    // getRawAxis takes axis number input and returns values between -1,1
		// Throttle is controlled by the slidebar, when pushed forward most position
		// slidebar returns  -1, so -1 + -1 / -2 = 1. Rear most position returns 1,
		// so 1 - 1 / -2 = 0. So -.75 + -1 / -2 = .87. So 0.87 would be multiplied by
		// the actual Y or Z value in arcade. 
	    // 0 = x-axis, 1 = y-axis, 2 = z-axis, 3 = slidebar
	    */
		public void mecanumDrive_Polar() {
			m_magnitude = j.getMagnitude() * .8;
			m_direction = j.getDirectionDegrees();
			m_rotation = j.getTwist() * .8;
			
			drivePolar(m_magnitude, m_direction, m_rotation);
		}	
}