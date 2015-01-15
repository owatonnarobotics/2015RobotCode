
package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;	// Import the ENUMS for the ports
import edu.wpi.first.wpilibj.command.Subsystem;	// ...ehh?

/**
 *	
 */
public class DriveTrain extends Subsystem {
	
	public DriveTrain() {
		this.init();
	}
	
	Jaguar left;
	Jaguar right;
	
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand( new Drive() );
	}
	
	
	public void init() {
		left	= new Jaguar( RobotMap.LEFT_MOTOR_PORT );
		right	= new Jaguar( RobotMap.RIGHT_MOTOR_PORT );
		this.stop();
	}
	
	public void set(float x, float y) {
		left.set(x);
		right.set(y);
	}
	
	public void stop() {
		left.set(0);
		right.set(0);
	}
}