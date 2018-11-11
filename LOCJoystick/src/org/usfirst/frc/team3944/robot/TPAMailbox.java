package org.usfirst.frc.team3944.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

public class TPAMailbox {

	private TPAJoystick joystick;
	private DigitalInput gearSwitch;
	private Servo leftServo, rightServo;
	private Timer t1,t2;
	private double servoSpeed1 = 0.0;
	private double servoSpeed2 = 0.5;
	private double timeBeforeClose = 20.0;
	
	public TPAMailbox(TPAJoystick joystick, DigitalInput gearSwitch, Servo left, Servo right){
		this.joystick = joystick;
		this.gearSwitch = gearSwitch;
		t1 = new Timer();
		t2 = new Timer();
		leftServo = left;
		rightServo = right;
		t1.reset();
		t1.stop();
		t2.reset();
		t2.stop();
		//make sure servo's are closed
	}
	//not used.
	public void loadGearManual(){
		if(joystick.getRawButton(5) == true){
			//not flipped condition
			if(gearSwitch.get() == true){
				//open servos
				leftServo.set(.5);
				rightServo.set(0);
			}
			//flipped condition
			if(gearSwitch.get() == false){
				leftServo.set(0);
				rightServo.set(.5);
			}
		}
		if(joystick.getRawButton(5) == false){
			leftServo.set(0);
			rightServo.set(.5);
		}
	}
	//not used.
	public void placeGearManual(){
		if(joystick.getRawButton(3) == true){
			//not flipped condition
			if(gearSwitch.get() == true){
				//close servos
				leftServo.set(.5);
				rightServo.set(0);
			}
			//flipped condition
			if(gearSwitch.get() == false){
				//open servos
				leftServo.set(0);
				rightServo.set(.5);
			}
		}
//		if(joystick.getRawButton(3) == false){
//			leftServo.set(0);
//			rightServo.set(.5);
//		}
		if(joystick.getRawButton(4) == true){
			leftServo.set(.5);
			rightServo.set(0);
		}
	}
	//can't remember what this was :) think this was the original method??<--used.
	public void placeGearManual1(){
		if(joystick.getRawButton(TPARobotMap.placeGearButton) == true){
			//not flipped condition
			if(gearSwitch.get() == false){
				//close servos
				closeServos(leftServo,rightServo);
			}
			//flipped condition
			if(gearSwitch.get() == true){
				//open servos
				t1.start();
				openServos(leftServo,rightServo);
			}
			if(t1.get() == timeBeforeClose){
				closeServos(leftServo,rightServo);
				t1.stop();
				t1.reset();
		}
		}
		
//		if(joystick.getRawButton(3) == false){
//			if(gearSwitch.get() == false){
//				leftServo.set(0);
//				rightServo.set(.5);
//			}
//		}
	}
	public void closeServos(Servo left, Servo right){
		left.set(servoSpeed1);
		right.set(servoSpeed2);
	}
	public void openServos(Servo left, Servo  right){
		left.set(servoSpeed2);
		right.set(servoSpeed1);
	}

}
