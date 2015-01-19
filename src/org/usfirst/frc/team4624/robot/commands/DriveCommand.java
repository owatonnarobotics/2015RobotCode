
package org.usfirst.frc.team4624.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4624.robot.Robot;

/**
 *
 */
public class DriveCommand extends Command {
	
	public DriveCommand() {
		// Use requires() here to declare subsystem dependencies
		requires( Robot.powertrain );
		// this.initialize(); // I don't know if we need this
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.powertrain.stop();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.powertrain.setFromController( Robot.oi.xboxController.leftStick.y(),
		                                    Robot.oi.xboxController.rightStick.y() );
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