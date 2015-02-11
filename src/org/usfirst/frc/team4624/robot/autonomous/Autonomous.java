package org.usfirst.frc.team4624.robot.autonomous;

import org.usfirst.frc.team4624.robot.Robot;
import org.usfirst.frc.team4624.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup { //TODO Move this class into commands folder ???
    
    public Autonomous(int location, int goal) {

        /*
         * Center = 0
         * Left   = 1
         * Right  = 2
         *
         * Bin    = 0
         * Tote   = 1
         */
    	

        if(location == 0 && goal == 0) {
            //Do stuff
        }

        if(location == 1 && goal == 0) {
            //Do stuff
        }

        if(location == 2 && goal == 0) {
            //Do stuff
        }

        if(location == 0 && goal == 1) {
            //Do stuff
        }

        if(location == 1 && goal == 1) {
            //Do stuff
        }

        if(location == 2 && goal == 1) {
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