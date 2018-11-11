package org.usfirst.frc.team3944.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

public class TeleAssist implements PIDOutput{

	private PIDController pid, pid2;
	private static double pidOutput, pidOutput2;
	private AHRS ahrs;
	private Xbox x;
	public static boolean assistState;
	public TeleAssist(AHRS ahrs, Xbox x){
		this.ahrs = ahrs;
		pid = new PIDController(9.0,46.0,39.0,ahrs, this);
		pid.setInputRange(-180.0f, 180.0f);
		pid.setOutputRange(-1.0, 1.0);
		pid.setAbsoluteTolerance(1.0f);
		pid.setContinuous(true);
		pid2 = new PIDController(0.03,0.1,0.1,0.01,ahrs, this);
		pid2.setInputRange(-180.0f, 180.0f);
		pid2.setOutputRange(-1.0, 1.0);
		pid2.setAbsoluteTolerance(1.0f);
		pid2.setContinuous(true);
		//ahrs.reset();
		this.x = x;
		assistState = false;
	}
	
	public void checkTurn(){
		//forward: middle position
		if(x.getPOVDirection() == 1){
			assistState = true;
			if(ahrs.getAngle() > 359.5 || ahrs.getAngle() < 0.5){
				Robot.robotDrive.arcadeDrive(0.0,0.0);
				assistState = false;
			}else{
				turnDegrees(0.0f);
			}
		}
		//left: right position
		if(x.getPOVDirection() == 2){
			assistState = true;
			if(ahrs.getAngle() > 59.5 && ahrs.getAngle() < 60.5){
				Robot.robotDrive.arcadeDrive(0.0,0.0);
				assistState = false;
			}else{
				turnDegrees(60.0f);
			}
		}
		//right: left position
		if(x.getPOVDirection() == 4){
			assistState = true;
			if(ahrs.getAngle() > 299.5 && ahrs.getAngle() < 300.5){
				Robot.robotDrive.arcadeDrive(0.0,0.0);
				assistState = false;
			}else{
				turnDegrees(-60.0f);
			}
		}
	}
	
	public void driveStraight(Xbox xc){
		if(ahrs.getAngle() < 358.0 || ahrs.getAngle() > 2.0){
			pid2.setSetpoint(0.0f);
			double turnDegrees;
			pid2.enable();
			turnDegrees = pidOutput2;
			Robot.robotDrive.arcadeDrive(xc.getRawAxis(1) * -1, turnDegrees * -.75);
		}else{
			Robot.robotDrive.arcadeDrive(xc.getRawAxis(1) * -1, 0.0);
		}
	}
	public void turnDegrees(float angleOfRotation){
		pid.setSetpoint(angleOfRotation);
		double turnDegrees;
		pid.enable();
		turnDegrees = pidOutput;
		Robot.robotDrive.arcadeDrive(0.0, turnDegrees * -.75);
	}
	@Override
	public void pidWrite(double output) {
		pidOutput = output;
		pidOutput2 = output;
	}

}
