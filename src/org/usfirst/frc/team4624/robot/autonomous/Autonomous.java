package org.usfirst.frc.team4624.robot.autonomous;

import org.usfirst.frc.team4624.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup { //TODO Move this class into commands folder ???
    
    public Autonomous(int location, int goal) {

        /*
         * 
         * No Platform = 0
         *    Platform = 1
         *
         * Bin    = 0
         * Tote   = 1
         */
        final double speed;
        final double actionTime = 4.0;
        final double smallTime = .25;

        if (location == 1){
            speed = .4;
        }
        
        else {
            speed = .25;
        }
        
        if (goal == 0) { //Bin
            addSequential(new ReleaseArms());
            addSequential(new AutoLift(2));
            pause(.25);
            addSequential(new AutoDrive(smallTime, .1));
            pause(.25);
            addSequential(new GrabArms());
            pause(.25);
            addSequential(new AutoLift(3));
            pause(.25);
            addSequential(new AutoDrive(actionTime, -speed));
            pause(5);
            addSequential(new AutoLift(0));
            pause(.75);
            addSequential(new ReleaseArms());
            pause(.25);
            addSequential(new AutoDrive(smallTime, -speed));
            pause(.25);
        }
        
        else if (goal == 1){  //Tote
            addSequential(new AutoDrive(smallTime, .1));
            pause(.25);
            addSequential(new AutoLift(1));
            pause(.25);
            addSequential(new AutoDrive(actionTime, -speed));
            pause(5);
            addSequential(new AutoLift(0));
            pause(.25);
            addSequential(new AutoDrive(smallTime, -speed));
            pause(.25);
        }
        
        else { //Nothing
            addSequential(new AutoDrive(actionTime, -speed));
        }
    }
    
    private void pause(double time){
        addSequential(new AutoPause(time));
    }
}