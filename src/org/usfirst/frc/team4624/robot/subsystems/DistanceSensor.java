//Not even sure if this class is useful. It may just be in CAN jaguar
package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DistanceSensor extends Subsystem {

	Encoder encoder;
	
	public DistanceSensor() {
		this.init();
	}
	
	public void init() {
		encoder = new Encoder(RobotMap.PORT_ENCODER_A, RobotMap.PORT_ENCODER_B, true, EncodingType.k4X); // Reverse encoding may not be true, but it's what the examples showed
	}
	
	public void startSensing() {
		
	}
	@Override
	protected void initDefaultCommand() {
	}

}
