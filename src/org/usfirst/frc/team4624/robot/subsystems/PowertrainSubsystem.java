
package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;	// ENUMS for the ports
import org.usfirst.frc.team4624.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;	// ...ehh?

public class PowertrainSubsystem extends Subsystem {
	
	public PowertrainSubsystem() {
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
		left	= new Jaguar( RobotMap.MOTOR_PORT_LEFT );
		right	= new Jaguar( RobotMap.MOTOR_PORT_RIGHT );
		this.stop();
	}
	
	public void setRaw( double l, double r ) {
		left.set( l );
		right.set( r );
	}
	
	public void set( double l, double r ) {
		this.setRaw( -l, r );	// In order to go straight, we need to invert one of the motors (Clockwise && counterclockwise = straight)
	}
	
	public void setFromController( double l, double r ) {
		double left		= Math.pow( l, 3 );
		double right	= Math.pow( r, 3 );
		this.set( left, right );
	}
	
	public void stop() {
		left.set( 0 );
		right.set( 0 );
	}
}