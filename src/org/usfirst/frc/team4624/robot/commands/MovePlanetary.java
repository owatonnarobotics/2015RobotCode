package org.usfirst.frc.team4624.robot.commands;

import org.usfirst.frc.team4624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MovePlanetary extends Command {
    
    public MovePlanetary() {
        requires(Robot.planetary);
    }
    
    @Override
    protected void initialize() {
        
    }
    
    @Override
    protected void execute() {
        
    }
    
    @Override
    protected boolean isFinished() {
        return true;
    }
    
    @Override
    protected void end() {
        Robot.planetary.toggle();
    }
    
    @Override
    protected void interrupted() {
        
    }
}