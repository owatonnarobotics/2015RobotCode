package org.usfirst.frc.team4624.robot.commands;

import org.usfirst.frc.team4624.robot.Robot;
import org.usfirst.frc.team4624.robot.subsystems.Planetary;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomusDrive extends Command {
    
    public AutonomusDrive() {
        //requires(Robot.planetary);
    }
    @Override
    protected void initialize() {
        //Robot.planetary.stop();
    }
    
    @Override
    protected void execute() {
        
    }
    
    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    protected void end() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub
        
    }
}
