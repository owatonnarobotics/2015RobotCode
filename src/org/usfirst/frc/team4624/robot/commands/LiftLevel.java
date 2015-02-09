package org.usfirst.frc.team4624.robot.commands;

import org.usfirst.frc.team4624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftLevel extends Command {
    
	private Level direction;
    
    public enum Level{
    	UP, DOWN
    }
    
    public LiftLevel(Level direction) {
        this.direction = direction;
        
        requires(Robot.forklift);
    }
    
    @Override
    protected void initialize() {
    	Robot.forklift.setLevelMode();
    }

    @Override
    protected void execute() {

        if(direction == Level.UP) {
            Robot.forklift.increaseLevel();
        } else {
            Robot.forklift.decreaseLevel();
        }
    }

    @Override
    protected boolean isFinished() {
        return true; // Move levels can be cumulative
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}
