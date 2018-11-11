package org.usfirst.frc.team3944.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	TPAJoystick j;
	TPARobotDrive robotDrive;
	CANTalon fl,bl,fr,br,climberMotor;
	TPAMailbox mailbox;
	DigitalInput gearSwitch,climberSwitch;
	Servo leftServo,rightServo;
	TPAClimber climber;
	//Thread visionThread;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		j = new TPAJoystick(TPARobotMap.JoystickPort);
		fl = new CANTalon(TPARobotMap.frontLeftCAN_ID);
		bl = new CANTalon(TPARobotMap.backLeftCAN_ID);
		fr = new CANTalon(TPARobotMap.frontRightCAN_ID);
		br = new CANTalon(TPARobotMap.backRightCAN_ID);
		robotDrive = new TPARobotDrive(fl,bl,fr,br,j);
		gearSwitch = new DigitalInput(TPARobotMap.gearSwitchPort);
		leftServo = new Servo(TPARobotMap.leftServoPort);
		rightServo = new Servo(TPARobotMap.rightServoPort);
		climberSwitch = new DigitalInput(TPARobotMap.climberSwitchPort);
		climberMotor = new CANTalon(TPARobotMap.climberCAN_ID);
		mailbox = new TPAMailbox(j,gearSwitch,leftServo,rightServo);
		climber = new TPAClimber(j,climberSwitch,climberMotor);
		/*visionThread = new Thread(() -> {
			// Get the Axis camera from CameraServer
			AxisCamera camera = CameraServer.getInstance().addAxisCamera("axis-camera.local");
			// Set the resolution
			camera.setResolution(640, 480);

			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 20, 20);

			while (!Thread.interrupted()) {
			}
		});	
		//visionThread.start();
		visionThread.start();*/
		
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
		
//		try {
//			visionThread.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		robotDrive.arcadeDrive_throttle();
		mailbox.placeGearManual1();
		climber.runClimber();
		SmartDashboard.putString("limitSwitch: ", ""+gearSwitch.get());
		SmartDashboard.putString("climberSwitch: ", ""+climberSwitch.get());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

