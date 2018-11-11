package org.usfirst.frc.team3944.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class TPAClimber {
	
	Xbox xbox;
	DigitalInput climberSwitch;
	CANTalon climber;
	private double climberSpeed = 0.85;

	public TPAClimber(Xbox xbox, DigitalInput climberSwitch, CANTalon climber){
		this.xbox = xbox;
		this.climberSwitch = climberSwitch;
		this.climber = climber;
	}
	
	public void runClimber(){
		if(xbox.getRightTrigger() == true){
			//true for bot 1
			if(climberSwitch.get() == false){
				climber.set(climberSpeed);
			}
			//false for bot 1
			if(climberSwitch.get() == true){
				climber.set(0.0);
			}
		}
		if(xbox.getRightTrigger() == false || climberSwitch.get() == true){
			climber.set(0.0);
		}
	}

}
