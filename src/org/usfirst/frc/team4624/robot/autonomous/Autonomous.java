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
        final double actionTime = 3;
        final double smallTime = 1.1;
        final double forwardSpeed = .2;

        if (location == 1){
            speed = .405;
        }
        
        else {
            speed = .35;
        }
        
        if (goal == 0) { //Bin
            addSequential(new ReleaseArms());
            addSequential(new AutoLift(2));
            pause(2.5);
            addSequential(new AutoDrive(smallTime, forwardSpeed));
            pause(.8);
            addSequential(new GrabArms());
            pause(.25);
            addSequential(new AutoLift(3));
            pause(.25);
            addSequential(new AutoDrive(actionTime, -speed));
            addSequential(new AutoLift(2));
            pause(.75);
            addSequential(new ReleaseArms());
            pause(.5);
            addSequential(new AutoLift(4));
            pause(2.5);
            //pause(.25);
            //addSequential(new AutoDrive(smallTime, -speed));
            //pause(.25);
        }
        
        else if (goal == 1){  //Tote
            addSequential(new ReleaseArms());
            addSequential(new AutoDrive(smallTime, forwardSpeed));
            pause(.6);
            addSequential(new GrabArms());
            addSequential(new AutoLift(1));
            pause(1.0);
            addSequential(new AutoDrive(actionTime, -speed));
            addSequential(new AutoLift(0));
            pause(1.2);
            addSequential(new ReleaseArms());
            addSequential(new AutoLift(3));
            pause(1.0);
        }
        
        else { //Nothing
            addSequential(new AutoDrive(actionTime - smallTime, -speed));
        }

        addSequential(new AutoRotate(true));
        pause(1);
        addSequential(new AutoLift(0));
        addSequential(new GrabArms());
    }
    
    private void pause(double time){
        addSequential(new AutoPause(time));
    }
}