package org.usfirst.frc.team4624.robot.autonomous;



import org.usfirst.frc.team4624.robot.commands.GrabArms;
import org.usfirst.frc.team4624.robot.commands.ReleaseArms;
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
        
        // Set the speed depending if we are going over a platform or not
        if (location == 1) {
            speed = .375;
        } else {
            speed = .35;
        }
        
        // If our goal is the bin
        if (goal == 0) {
            
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
        } else if (goal == 1) { // If our goal is a tote
        
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
        } else { // If our goal is just to drive backwards
            addSequential(new AutoDrive(actionTime - smallTime, -speed));
        }
        
        addSequential(new AutoRotate(true));
        pause(1);
        
        addSequential(new AutoLift(0));
        addSequential(new GrabArms());
    }
    
    private void pause(double time) {
    
        addSequential(new AutoPause(time));
    }
}