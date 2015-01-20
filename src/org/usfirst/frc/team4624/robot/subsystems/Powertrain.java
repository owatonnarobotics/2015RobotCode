
package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;	// ENUMS for the ports
import org.usfirst.frc.team4624.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;	// ...ehh?

public class Powertrain extends Subsystem {
	
	public Powertrain() {
		this.init();
	}
	
	Jaguar left;
	Jaguar right;
	
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand( new DriveCommand() );
	}
	
	
	public void init() {
		left  = new Jaguar( RobotMap.PORT_MOTOR_LEFT );
		right = new Jaguar( RobotMap.PORT_MOTOR_RIGHT );
		this.stop();
	}
	
	public void setRaw( double l, double r ) {
		left.set( l );
		right.set( r );
	}
	
	public void set( double l, double r ) {
		this.setRaw( -l, r );	// To go straight, we inverted one of the motors (Clockwise && Counter-Clockwise = Straight)
	}
	
	public void setFromController( double l, double r ) {
		double left  = Math.pow( l, 3 );
		double right = Math.pow( r, 3 );
		this.set( left, right );
	}
	
	public void stop() {
		left.set( 0 );
		right.set( 0 );
	}
}