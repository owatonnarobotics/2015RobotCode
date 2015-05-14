package org.usfirst.frc.team4624.robot.commands;



import org.usfirst.frc.team4624.robot.autonomous.AutoPause;
import org.usfirst.frc.team4624.robot.commands.LiftLevel.Level;
import edu.wpi.first.wpilibj.command.CommandGroup;



public class LiftFeeder extends CommandGroup {
    
    
    
    @Override
    protected void end() {
    
    }
    
    @Override
    protected void execute() {
    
        addSequential(new GrabArms());
        addParallel(new AutoPause(.25));
        
        //addSequential(new AutoDrive(.5, -.2));
        
        addSequential(new LiftLevel(Level.UP));
        addParallel(new AutoPause(.25));
        
        //addSequential(new AutoDrive(.5, .2));
        //addSequential(new AutoPause(.5));
    }
    
    @Override
    protected void initialize() {
    
    }
    
    @Override
    protected void interrupted() {
    
    }
    
    @Override
    protected boolean isFinished() {
    
        return true;
    }
    
}
