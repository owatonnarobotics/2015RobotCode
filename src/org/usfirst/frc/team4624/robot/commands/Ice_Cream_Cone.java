
package org.usfirst.frc.team4624.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/*
 * Drives forwards n' backwards
 */
public class Ice_Cream_Cone extends Command {
    
    private String text;
    private boolean quitThis = false;
    
    public Ice_Cream_Cone( String text ) {
    	this.text = text;
        // Use requires() here to declare subsystem dependencies
        //xboxController = Robot.oi.xboxController;
        // this.initialize(); // I don't know if we need this
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println( this.text );
        quitThis = true;
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return quitThis;
    }
    
    // Called once after isFinished returns true
    protected void end() {
        
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        
    }
}