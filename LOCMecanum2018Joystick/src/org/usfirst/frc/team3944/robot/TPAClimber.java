package org.usfirst.frc.team3944.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class TPAClimber {
	
	TPAJoystick joystick;
	DigitalInput climberSwitch;
	WPI_TalonSRX climber;
	private double climberSpeed = 0.85;

	public TPAClimber(TPAJoystick joystick, DigitalInput climberSwitch, WPI_TalonSRX climber){
		this.joystick = joystick;
		this.climberSwitch = climberSwitch;
		this.climber = climber;
	}
	
	public void runClimber(){
		if(joystick.getRawButton(1) == true){
			//true for bot 1
			if(climberSwitch.get() == false){
				climber.set(climberSpeed);
			}
			//false for bot 1
			if(climberSwitch.get() == true){
				climber.set(0.0);
			}
		}
		if(joystick.getRawButton(1) == false || climberSwitch.get() == true){
			climber.set(0.0);
		}
	}

}
