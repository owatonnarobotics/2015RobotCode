package org.usfirst.frc.team4624.robot;

import org.usfirst.frc.team4624.robot.commands.*;
import org.usfirst.frc.team4624.robot.commands.LiftLevel.Level;
import org.usfirst.frc.team4624.robot.input.*;

public class OI {
    
/** The Xbox360 controller that we use to control the robot */
    public static XboxController xboxController = new XboxController();

    public OI() {
        xboxController.leftStick.setYDeadZone(.2);  // Gib me Oreos�
        
        xboxController.rb.whenPressed(new ReleaseArms());
        xboxController.rb.whenReleased(new GrabArms());
        
        xboxController.rt.whenPressed(new LiftManual());
        xboxController.lt.whenPressed(new LiftManual());
        
        xboxController.a.whenPressed(new LiftLevel(Level.DOWN));
        xboxController.x.whenPressed(new LiftLevel(Level.UP));
        
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
