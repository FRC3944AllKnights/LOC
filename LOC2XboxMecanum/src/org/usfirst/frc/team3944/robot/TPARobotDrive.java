package org.usfirst.frc.team3944.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
 
// Main Class Body
public class TPARobotDrive extends RobotDrive {
	// Declare Throttle Variables used for arcadeDrive
	private double t_yAxis;
	private double t_zAxis;
	private boolean b;
	// This is an object declaration that sets a named location in memory. It is a joystick 
	// object of type TPAJoystick. Or a reference variable of type TPAJoystick. 
	// "final" tells the compiler that subclass cannot override.
	private final Xbox x ;
	private double m_magnitude;
	private double m_direction;
	private double m_rotation;
	private double stuff;
	// Instantiation of public static CANTalon Object
	public static CANTalon frontLeftMotor = new CANTalon(TPARobotMap.frontLeftCAN_ID);
	public static CANTalon backLeftMotor = new CANTalon(TPARobotMap.backLeftCAN_ID);
	public static CANTalon frontRightMotor = new CANTalon(TPARobotMap.frontRightCAN_ID);
	public static CANTalon backRightMotor = new CANTalon(TPARobotMap.backRightCAN_ID);
	
	// Constructor
	public TPARobotDrive(SpeedController frontLeftMotor, SpeedController backLeftMotor, SpeedController frontRightMotor, SpeedController backRightMotor, Xbox x) {
		super(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
	    // named the same so you need the this, otherwise you won't need the this.
		this.x=x;
		b = false;
	}

	    /* The value passed to getRawAxis is the axis number from the joystick
	    // getRawAxis takes axis number input and returns values between -1,1
		// Throttle is controlled by the slidebar, when pushed forward most position
		// slidebar returns  -1, so -1 + -1 / -2 = 1. Rear most position returns 1,
		// so 1 - 1 / -2 = 0. So -.75 + -1 / -2 = .87. So 0.87 would be multiplied by
		// the actual Y or Z value in arcade. 
	    // 0 = x-axis, 1 = y-axis, 2 = z-axis, 3 = slidebar
	    */
		public void mecanumDrive_throttle() {
			
			t_yAxis = x.getRawAxis(1) * -1; 
			t_zAxis = x.getRawAxis(4) * -.75; 
			
			
			//Inverts the motor direction to support arcade drive
			//setInvertedMotor(MotorType motor, boolean isInverted)
		
//			frontLeftMotor.changeControlMode(TalonControlMode.PercentVbus);
//			backLeftMotor.changeControlMode(TalonControlMode.PercentVbus);
//			frontRightMotor.changeControlMode(TalonControlMode.PercentVbus);
//			backRightMotor.changeControlMode(TalonControlMode.PercentVbus);
//			
			SmartDashboard.putString("x.getMagnitude(): ", ""+m_magnitude);
			SmartDashboard.putString("x.getDirectionDegrees(): ", ""+m_direction);
			SmartDashboard.putString("x.getTwist(): ", ""+m_rotation);
			arcadeDrive(t_yAxis, t_zAxis);
			
		}	
}