
package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;	// ENUMS for the ports
import org.usfirst.frc.team4624.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;	// ...ehh?

/**
 *	
 */
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
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand( new DriveCommand() );
	}
	
	
	public void init() {
		left	= new Jaguar( RobotMap.LEFT_MOTOR_PORT );
		right	= new Jaguar( RobotMap.RIGHT_MOTOR_PORT );
		this.stop();
	}
	
	public void set(double d, double e) {
		left.set( d );
		right.set( e );
	}
	
	public void stop() {
		left.set( 0 );
		right.set( 0 );
	}
}