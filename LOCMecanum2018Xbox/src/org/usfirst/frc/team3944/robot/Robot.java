package org.usfirst.frc.team3944.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	private Xbox x;
	public static TPARobotDrive robotDrive;
	private WPI_TalonSRX fl,bl,fr,br,climberMotor;
	private TPAMailbox mailbox;
	private DigitalInput gearSwitch,climberSwitch;
	private Servo leftServo,rightServo;
	private TPAClimber climber;
	Servo s;
	private boolean a,b;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		s = new Servo(0);
		s.set(0.0);
		a = false;
		b = false;
		x = new Xbox(TPARobotMap.XboxPort);
		
		robotDrive = new TPARobotDrive(
								new SpeedControllerGroup(TPARobotDrive.frontLeftMotor, TPARobotDrive.backLeftMotor),
								new SpeedControllerGroup(TPARobotDrive.frontRightMotor, TPARobotDrive.backRightMotor),
								x);
		gearSwitch = new DigitalInput(TPARobotMap.gearSwitchPort);
		leftServo = new Servo(TPARobotMap.leftServoPort);
		rightServo = new Servo(TPARobotMap.rightServoPort);
		climberSwitch = new DigitalInput(TPARobotMap.climberSwitchPort);
		climberMotor = new WPI_TalonSRX(TPARobotMap.climberCAN_ID);
		mailbox = new TPAMailbox(x,gearSwitch,leftServo,rightServo);
		climber = new TPAClimber(x,climberSwitch,climberMotor);
		CameraServer.getInstance().startAutomaticCapture();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		robotDrive.arcadeDriveXbox();
		mailbox.placeGearManual();
		climber.runClimber();
		SmartDashboard.putString("limitSwitch: ", ""+gearSwitch.get());
		SmartDashboard.putString("climberSwitch: ", ""+climberSwitch.get());
		SmartDashboard.putString("leftServo: ", ""+leftServo.get());
		SmartDashboard.putString("rightServo: ", ""+rightServo.get());
		if(x.getRawButton(5) == true){
			a = false;
			b = true;
		}
		if(x.getRawButton(6) == true){
			a = true;
			b = false;
		}
		if(a == false && b == true){
			s.set(0);
		}
		if(a == true && b == false){
			s.set(1);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

