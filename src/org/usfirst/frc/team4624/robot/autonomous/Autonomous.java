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
        
        final int driveTime = 5000;
        final int extraDriveTime = 1000;
        final int backupTime = 1000;
        
        addSequential(new GrabArms());
        addSequential(new LiftLevel(LiftLevel.Level.UP));
        
        if(goal == 1) {
            addSequential(new AutoRotate(true));
        }
        else {
            addSequential(new AutoRotate(false));
        }

        if(location == 1) {
            addSequential(new AutoDrive(driveTime, true));
        }
        else {
            addSequential(new AutoDrive(driveTime + extraDriveTime, true));
        }
        
        addSequential(new LiftLevel(LiftLevel.Level.DOWN));
        addSequential(new ReleaseArms());
        addSequential(new AutoDrive(backupTime, false));
        
    }
}