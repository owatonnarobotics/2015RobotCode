package org.usfirst.frc.team4624.robot.autonomous;



import org.usfirst.frc.team4624.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup { //TODO Move this class into commands folder ???

    private int arms;
    
    public Autonomous(int location, int goal, int rotation, int type, int arms) {
    
        /*
         * 
         * No Platform = 0
         * Platform = 1
         * 
         * Bin = 0
         * Tote = 1
         * Drive = 2
         * Nothing = 3
         * 
         * Rotate = 0
         * No Rotate = 1
         * 
         * Standard = 0
         * 3 Tote = 1
         * 
         * Arms = 0
         * No Arms = 1
         */
        
        this.arms = arms;
        
        final double FOOT_ISH = .29; //give or take a lot... ish
        
        if (type == 0 || type == 1) {
            
            final double speed;
            final double actionTime = 2.6;
            final double smallTime = .7;
            final double forwardSpeed = .2;
            
            if (location == 1) {
                speed = .39;
            }
            
            else {
                speed = .35;
            }
            
            if (goal == 0) { //Bin
                releaseArms();
                addSequential(new AutoLift(2));
                pause(2.5);
                addSequential(new AutoDrive(smallTime, forwardSpeed));
                pause(.8);
                grabArms();
                pause(.25);
                addSequential(new AutoLift(3));
                pause(.25);
                addSequential(new AutoDrive(actionTime, -speed));
                addSequential(new AutoLift(2));
                pause(.75);
                releaseArms();
                pause(.5);
                addSequential(new AutoLift(4));
                pause(2.5);
            }
            
            else if (goal == 1) { //Tote
                releaseArms();
                addSequential(new AutoDrive(smallTime, forwardSpeed));
                pause(.8);
                grabArms();
                addSequential(new AutoLift(1));
                pause(1.0);
                addSequential(new AutoDrive(actionTime, -speed));
                addSequential(new AutoLift(0));
                pause(1.2);
                releaseArms();
                addSequential(new AutoLift(3));
                pause(1.0);
            }
            
            else if (goal == 2) { //Drive
                addSequential(new AutoDrive(actionTime - smallTime, -speed));
            }
            
            else { //Nothing, leave this here just in case
            }
            
            if (rotation == 0) {
                addSequential(new AutoRotate(true));
            } else if (rotation == 1) {
                addSequential(new AutoRotate(false));
            }
            
            pause(1);
            addSequential(new AutoLift(0));
            if ((!(rotation == 2)) && (arms != 1)) {
                addSequential(new GrabArms());
            }
        }
        
        // Should be 1, switched to two so it doesn't happen
        else if (type == 2) {
            addSequential(new AutoDriveAdvanced(.1, 0, FOOT_ISH / 12));
            pause(0.1);
            addSequential(new GrabArms());
            pause(0.1);
            addSequential(new AutoLift(2));
            pause(0.25);
            addSequential(new AutoDriveAdvanced(2, 0, 6.3 * FOOT_ISH));
            pause(2.0);
            addSequential(new ReleaseArms());
            addSequential(new AutoLift(0));
            pause(.2);
            addSequential(new GrabArms());
            pause(.1);
            addSequential(new AutoLift(2));
            pause(.1);
            addSequential(new AutoDriveAdvanced(2, 0, 6.3 * FOOT_ISH));
            pause(2.0);
            addSequential(new ReleaseArms());
            addSequential(new AutoLift(0));
            pause(.2);
            addSequential(new GrabArms());
            pause(.1);
            addSequential(new AutoLift(2));
            pause(.1);
            addSequential(new AutoDriveAdvanced(6, 9.75 * FOOT_ISH, 0));
            pause(6);
            addSequential(new AutoLift(0));
            pause(.1);
            addSequential(new ReleaseArms());
            pause(.1);
            addSequential(new AutoDriveAdvanced(.5, 0, .5 * FOOT_ISH));
            pause(.5);
        }
    }
    
    private void pause(double time) {
        addSequential(new AutoPause(time));
    }
    
    private void grabArms() {
        if (arms != 1) {
            addSequential(new GrabArms());
        }
    }
    
    private void releaseArms() {
        if (arms != 1) {
            addSequential(new ReleaseArms());
        }
    }
}