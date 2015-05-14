package org.usfirst.frc.team4624.robot.commands;



import org.usfirst.frc.team4624.robot.OI;
import org.usfirst.frc.team4624.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;



/**
 * DriveCommand This command is used for teleoperated controlling
 */
public class DriveCommand extends Command {
    
    
    
    /**
     * Constructor This initializes the DriveCommand
     */
    public DriveCommand() {
    
        requires(Robot.powertrain); // Use requires() here to declare subsystem dependencies
    }
    
    // Called once after isFinished returns true
    @Override
    protected void end() {
    
        Robot.powertrain.stop();
    }
    
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    
        Robot.powertrain.setAsTankdrive(OI.xboxController);
    }
    
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    
        Robot.powertrain.stop();
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    
        end();
    }
    
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    
        return Robot.powertrain.isFinished();
    }
}
