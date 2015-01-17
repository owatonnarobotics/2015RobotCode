package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;

//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Planetary extends Subsystem{

	public Planetary() {
	}
	
	 Jaguar  planetary;
	private boolean spinning;
	 //Encoder encoder;
	
	public void init() {
		planetary = new Jaguar(RobotMap.PORT_ENCODER_JAGUAR);
		spinning  = false;
		//encoder   = new Encoder(RobotMap.PORT_ENCODER_A, RobotMap.PORT_ENCODER_B);
	}
	
	public void toggle() {
		spinning = !spinning;
		setMotor(spinning);
	}
	
	public void setMotor(boolean spinning) {
		if(spinning) {
			planetary.set(.5);
		}
		else {
			planetary.set(0);
		}
	}
	
	public void stop() {
		planetary.set(0);
	}
		
	@Override
	protected void initDefaultCommand() {
	}

}
