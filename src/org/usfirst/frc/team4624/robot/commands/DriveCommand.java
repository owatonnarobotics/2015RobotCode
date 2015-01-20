
package org.usfirst.frc.team4624.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4624.robot.Robot;
import org.usfirst.frc.team4624.robot.input.XboxController;

/**
 *
 */
public class DriveCommand extends Command {
	
	XboxController xboxController;
	String str = "";	// ==DEBUGGING==
	
	public DriveCommand() {
		// Use requires() here to declare subsystem dependencies
		requires( Robot.powertrain );
		xboxController = Robot.oi.xboxController;
		// this.initialize(); // I don't know if we need this
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.powertrain.stop();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.powertrain.setFromController(	xboxController.leftStick.y(),
											xboxController.rightStick.y() );
		
		// ==DEBUGGING==
		str += xboxController.a.get() ? "A Button" : "";
		str += xboxController.b.get() ? "B Button" : "";
		str += xboxController.back.get() ? "Back Button" : "";
		str += xboxController.dPad.angle() > 0 ? "DPad" : "";
		str += xboxController.lb.get() ? "LB Button" : "";
		str += xboxController.leftStick.y() > .5 ? "Leftstick Y" : "";
		str += xboxController.lt.get() ? "LT Trigger" : "";
		str += xboxController.rb.get() ? "RB Button" : "";
		str += xboxController.start.get() ? "START Button" : "";
		str += xboxController.x.get() ? "X Button" : "";
		str += xboxController.y.get() ? "Y Button" : "";
		
		if( str.length() > 0 ) {
			System.out.println( str );
		}
		// ==DEBUGGING==
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		
	}
}