package org.usfirst.frc.team4624.robot.commands;



import org.usfirst.frc.team4624.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;



public class LiftLevel extends Command {
    
    
    
    public enum Level {
        UP, DOWN, STOP
    }
    
    
    
    private Level direction;
    
    
    
    public LiftLevel(Level direction) {
    
        this.direction = direction;
        
        requires(Robot.forklift);
    }
    
    @Override
    protected void end() {
    
    }
    
    @Override
    protected void execute() {
    
        if (direction == Level.UP) {
            Robot.forklift.increaseLevel();
        } else if (direction == Level.DOWN) {
            Robot.forklift.decreaseLevel();
        } else {
            Robot.forklift.setRate(0);
            Robot.forklift.setManualMode();
        }
    }
    
    @Override
    protected void initialize() {
    
        Robot.forklift.setLevelMode();
    }
    
    @Override
    protected void interrupted() {
    
    }
    
    @Override
    protected boolean isFinished() {
    
        return true; // Move levels can be cumulative
    }
    
}
