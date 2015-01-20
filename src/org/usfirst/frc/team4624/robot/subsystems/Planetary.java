package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Planetary extends Subsystem{

	public Planetary() {
		this.init();
	}
	
	 CANJaguar planetary;
	 
	 public boolean spinning;
	
	public void init() {
		spinning  = false;
		planetary = new CANJaguar(RobotMap.PORT_ENCODER_JAGUAR);
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
	
	public double getSpeed(){
		return planetary.getSpeed();
	}
		
	@Override
	protected void initDefaultCommand() {
	}

}
