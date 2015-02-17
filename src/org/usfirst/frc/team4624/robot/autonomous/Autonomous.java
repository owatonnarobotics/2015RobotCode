package org.usfirst.frc.team4624.robot.autonomous;

import org.usfirst.frc.team4624.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup { //TODO Move this class into commands folder ???
    
    public Autonomous(int location, int goal) {

        /*
         * 
         * Center = 0
         * Left   = 1
         * Right  = 2
         *
         * Bin    = 0
         * Tote   = 1
         */
        
        final double driveTime      = 3;
        final double extraDriveTime = 1;
        final double backupTime     = 1;
        final double speed;
        
        if (location == 0){
            speed = .35;
        } else {
            speed = .25;
        }
        
        if (goal == 0) { //Bin

            addSequential(new ReleaseArms());
            addSequential(new LiftLevel(LiftLevel.Level.UP));
            addSequential(new AutoPause(2));
            addSequential(new AutoDrive(.85, speed));
            addSequential(new AutoPause(.5));
            addSequential(new GrabArms());
            addSequential(new LiftLevel(LiftLevel.Level.UP));
    
            addSequential(new AutoPause(1));
            
            addSequential(new AutoDrive(5.0, -speed));
            addSequential(new LiftLevel(LiftLevel.Level.DOWN));
            addSequential(new AutoPause(.5));
            addSequential(new LiftLevel(LiftLevel.Level.DOWN));
            addSequential(new AutoPause(2));
            addSequential(new ReleaseArms());
        
        } if (goal == 1){  //Tote
            
            //addSequential(new AutoDrive(2.0, speed));
            //addSequential(new AutoPause(1));
            //addSequential(new LiftLevel(LiftLevel.Level.UP));
            addSequential(new LiftLevel(LiftLevel.Level.UP));
            addSequential(new AutoPause(1));
            addSequential(new AutoDrive(5.0, -speed));
     
            addSequential(new AutoPause(1));
                
            //addSequential(new LiftLevel(LiftLevel.Level.DOWN));
            addSequential(new LiftLevel(LiftLevel.Level.DOWN));
            addSequential(new AutoDrive(.5,-speed));
     
        } else { //Nothing
            
            addSequential(new AutoDrive(3.0, -speed));
        
        }
        /*if(goal == 1) {
            addSequential(new AutoRotate(true));
        }
        else {
            addSequential(new AutoRotate(false));
        }

        if(location == 1) {
            addSequential(new AutoDrive(driveTime, true));
        } else {
            addSequential(new AutoDrive(driveTime + extraDriveTime, true));
        }
        
        addSequential(new AutoPause(.5));
        addSequential(new LiftLevel(LiftLevel.Level.DOWN));
        addSequential(new AutoPause(1.5));
        addSequential(new ReleaseArms());
        addSequential(new AutoPause(.5));
        addSequential(new AutoDrive(backupTime, false));*/
    }
}