package org.usfirst.frc.team4624.robot.commands;

import org.usfirst.frc.team4624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftLevel extends Command {
    
    public int level;
    
    public LiftLevel(int changeLevel) {
        requires(Robot.forklift)
        if(changeLevel == 0) {
            Robot.forklift.stop();
        } else {
            Robot.forklift.changeLevel(changeLevel);
        }
    }
    
    @Override
    protected void initialize() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void execute() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected boolean isFinished() {
        return true;    // Move levels can be cumulative
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
