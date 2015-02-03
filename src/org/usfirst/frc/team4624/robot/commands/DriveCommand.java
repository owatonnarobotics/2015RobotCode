
package org.usfirst.frc.team4624.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4624.robot.OI;
import org.usfirst.frc.team4624.robot.Robot;
import org.usfirst.frc.team4624.robot.input.XboxController;

/**
 * DriveCommand
 * 
 * This command is used for teleoperated controlling
 */
public class DriveCommand extends Command {
    
    /* Instance values */
    XboxController xboxController;
    
    
    
    /**
     * Constructor 
     * 
     * This initializes the DriveCommand
     */
    public DriveCommand() {
        requires( Robot.powertrain );   // Use requires() here to declare subsystem dependencies
        xboxController = OI.xboxController;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.powertrain.stop();
    }
    
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.powertrain.setAsTankdrive( xboxController.leftStick );
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