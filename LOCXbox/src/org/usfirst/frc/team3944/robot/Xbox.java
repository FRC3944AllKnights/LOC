package org.usfirst.frc.team3944.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public class Xbox extends Joystick{
	public Xbox(final int aPort) {
		super(aPort);
		if(getIsXbox() == true){
			//continue.
		}else{
			DriverStation.reportWarning("Actually plug in an Xbox controller, idiot. You can't fool a computer.", true);
		}
	}
	
	public boolean getLeftTrigger(){
		boolean triggerState;
		if(getRawAxis(2) >= .25){
			triggerState = true;
		}else{
			triggerState = false;
		}
		return triggerState;
	}
	
	public boolean getRightTrigger(){
		boolean triggerState;
		if(getRawAxis(3) >= .25){
			triggerState = true;
		}else{
			triggerState = false;
		}
		return triggerState;
	}
	
	public int getPOVDirection(){
		if(getPOV(0) == -1){
			TeleAssist.assistState = false;
			return 0;
		}
		if(getPOV(0) == 0){
			return 1;
		}
		if(getPOV(0) == 90){
			return 2;
		}
		if(getPOV(0) == 180){
			return 3;
		}
		if(getPOV(0) == 270){
			return 4;
		}
		return 0;
	}
}
