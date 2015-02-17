package org.usfirst.frc.team4624.robot.autonomous;

import org.usfirst.frc.team4624.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup { //TODO Move this class into commands folder ???
    
    public Autonomous(int location, int goal) {

        /*
         * 
         * Center = 0
         * Left   = 1
         * Right  = 0
         *
         * Bin    = 0
         * Tote   = 1
         */
        final double speed;
        
        if (location == 0){
            speed = .4;
        }
        
        else {
            speed = .25;
        }
        
        if (goal == 0) { //Bin
            addSequential(new ReleaseArms());
            pause(.25);
            addSequential(new AutoLift(2));
            pause(.25);
            addSequential(new AutoDrive(.25, .1));
            pause(.25);
            addSequential(new GrabArms());
            pause(.25);
            addSequential(new AutoLift(3));
            pause(.25);
            addSequential(new AutoDrive(5.0, -speed));
            pause(5);
            addSequential(new AutoLift(0));
            pause(.75);
            addSequential(new ReleaseArms());
            pause(.25);
            addSequential(new AutoDrive(.25, -speed));
            pause(.25);
        }
        
        else if (goal == 1){  //Tote
            addSequential(new AutoDrive(.25, .1));
            pause(.25);
            addSequential(new AutoLift(1));
            pause(.25);
            addSequential(new AutoDrive(5.0, -speed));
            pause(5);
            addSequential(new AutoLift(0));
            pause(.25);
            addSequential(new AutoDrive(.25, -speed));
            pause(.25);
        }
        
        else { //Nothing
            addSequential(new AutoDrive(5.0, -speed));
        }
    }
    
    private void pause(double time){
        addSequential(new AutoPause(time));
    }
}