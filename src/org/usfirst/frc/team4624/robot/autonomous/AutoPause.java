package org.usfirst.frc.team4624.robot.autonomous;



import edu.wpi.first.wpilibj.command.Command;



public class AutoPause extends Command {
    
    
    
    public AutoPause(double time) {
    
        this.setTimeout(time);
    }
    
    @Override
    protected void end() {
    
    }
    
    @Override
    protected void execute() {
    
    }
    
    @Override
    protected void initialize() {
    
    }
    
    @Override
    protected void interrupted() {
    
    }
    
    @Override
    protected boolean isFinished() {
    
        return this.isTimedOut();
    }
    
}
