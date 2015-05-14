package org.usfirst.frc.team4624.robot.commands;



import org.usfirst.frc.team4624.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;



public class SwitchLevelMode extends Command {
    
    
    
    public SwitchLevelMode() {
    
        requires(Robot.forklift);
    }
    
    @Override
    protected void end() {
    
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void execute() {
    
        Robot.forklift.switchLevelArray();
        
    }
    
    @Override
    protected void initialize() {
    
    }
    
    @Override
    protected void interrupted() {
    
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected boolean isFinished() {
    
        // TODO Auto-generated method stub
        return true;
    }
    
}
