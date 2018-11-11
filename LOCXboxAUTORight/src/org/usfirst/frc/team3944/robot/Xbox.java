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
}
