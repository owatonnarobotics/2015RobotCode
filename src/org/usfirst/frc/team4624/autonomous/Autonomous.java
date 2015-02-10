package org.usfirst.frc.team4624.autonomous;

import org.usfirst.frc.team4624.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {
    
    public Autonomous(String location, String goal) {
        if(location.equals("center")) {
            //Do stuff
        }
    }
    
    private void binTime(int firstMove, int secondMove) {
        addSequential(new GrabArms());
        
        addSequential(new LiftLevel(LiftLevel.Level.UP));
        addParallel(new AutoDriveBackwards(firstMove));    // Move backwards for 3000 milliseconds
        
        addSequential(new LiftLevel(LiftLevel.Level.DOWN));
        
        addSequential(new ReleaseArms());
        
        addParallel(new AutoDriveBackwards(secondMove));    // Move backwards for 50 milliseconds
    }
}