package org.usfirst.frc.team4624.robot;

import org.usfirst.frc.team4624.robot.commands.GrabArms;
import org.usfirst.frc.team4624.robot.commands.LiftLevel;
import org.usfirst.frc.team4624.robot.commands.LiftManual;
import org.usfirst.frc.team4624.robot.commands.ReleaseArms;
import org.usfirst.frc.team4624.robot.input.XboxController;

public class OI {

    public static XboxController xboxController = new XboxController();

    public OI() {
        xboxController.rb.whenPressed(new ReleaseArms());
        xboxController.rb.whenReleased(new GrabArms());
        
        xboxController.a.whenPressed(new LiftLevel(-1));
        xboxController.x.whenPressed(new LiftLevel(1));
        xboxController.lb.whenPressed(new LiftLevel(0));
        
        /*
         * a = go down a level
         * x = go up a level
         * rb = release arms
         * rt = go up
         * lt = go down
         * lb = stop moving
         * 
         * thumbsticks = ruin everything
         */
    }
}
