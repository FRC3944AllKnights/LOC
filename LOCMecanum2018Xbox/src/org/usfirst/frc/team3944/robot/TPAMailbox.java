package org.usfirst.frc.team3944.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

public class TPAMailbox {

	private Xbox xbox;
	private DigitalInput gearSwitch;
	private Servo leftServo, rightServo;
	private Timer t1,t2;
	private double servoSpeed1 = 0.5;//bot 1 0.0
	private double servoSpeed2 = 0.0;//bot 1 0.5
	private double timeBeforeClose = 20.0;
	
	public TPAMailbox(Xbox xbox, DigitalInput gearSwitch, Servo left, Servo right){
		this.xbox = xbox;
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
	//can't remember what this was :) think this was the original method??<--used.
	public void placeGearManual(){
		if(xbox.getLeftTrigger() == true){
			
			//not flipped condition bot 1 false
			if(gearSwitch.get() == false){
				//close servos
				closeServos(leftServo,rightServo);
			}
			//flipped condition bot 1 true
			if(gearSwitch.get() == true){
				//open servos
				t1.start();
				openServos(leftServo,rightServo);
			}
			
		}
		if(t1.get() > timeBeforeClose){
			closeServos(leftServo,rightServo);
			t1.stop();
			t1.reset();
		}
	}
	public void closeServos(Servo left, Servo right){
		left.set(.74);
		right.set(0);
	}
	public void openServos(Servo left, Servo  right){
		left.set(0);
		right.set(.74);
	}

}
