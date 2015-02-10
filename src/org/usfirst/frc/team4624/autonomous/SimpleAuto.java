package org.usfirst.frc.team4624.autonomous;

import org.usfirst.frc.team4624.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SimpleAuto extends CommandGroup {
    
    public SimpleAuto(DRIVER_STATION location, AUTO_GOAL goal) {
        if(goal == AUTO_GOAL.BIN) {
            
            if(location == DRIVER_STATION.LEFT) {
                binTime(3000, 50);
            }
            
            if(location == DRIVER_STATION.CENTER) {
                binTime(4000, 50);
            }
            
            if(location == DRIVER_STATION.RIGHT) {
                binTime(4000, 50);
            }
        }
        if(goal == AUTO_GOAL.TOTE) {
            
            if(location == DRIVER_STATION.LEFT) {
                //You are on the left side of the field
            }
            
            if(location == DRIVER_STATION.CENTER) {
                //You are at the center of the field
            }
            
            if(location == DRIVER_STATION.RIGHT) {
                //You are on the right side of the field
            }
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