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
    
        requires(Robot.powertrain);
    }
    
    @Override
    protected void end() {
    
        Robot.powertrain.stop();
    }
    
    @Override
    protected void execute() {
    
        Robot.powertrain.setAsTankdrive(OI.xboxController);
    }
    
    @Override
    protected void initialize() {
    
        Robot.powertrain.stop();
    }
    
    @Override
    protected void interrupted() {
    
        end();
    }
    
    @Override
    protected boolean isFinished() {
    
        return Robot.powertrain.isFinished();
    }
}