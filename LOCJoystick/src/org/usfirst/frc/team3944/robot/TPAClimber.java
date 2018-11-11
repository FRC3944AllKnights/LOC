package org.usfirst.frc.team3944.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;

public class TPAClimber {
	
	TPAJoystick joystick;
	DigitalInput climberSwitch;
	CANTalon climber;
	int count;
	private double climberSpeed = 0.85;

	public TPAClimber(TPAJoystick joystick, DigitalInput climberSwitch, CANTalon climber){
		this.joystick = joystick;
		this.climberSwitch = climberSwitch;
		this.climber = climber;
		count = 0;
	}
	
	public void runClimber(){
		if(joystick.getRawButton(TPARobotMap.climberButton) == true){
			if(climberSwitch.get() == true){
				climber.set(climberSpeed);
			}
			if(climberSwitch.get() == false){
				climber.set(0.0);
			}
		}
//		if(joystick.getRawButton(2) == false){
//			if(climberSwitch.get() == true){
//				climber.set(0);
//			}
//		}
//		if(climberSwitch.get() == false){
//			climber.set(0.0);
//			count++;
//		}
//		if(climberSwitch.get() == true && count >= 1){
//			climber.set(.85);
//		}
	}

}
