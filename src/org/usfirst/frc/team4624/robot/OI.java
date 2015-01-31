package org.usfirst.frc.team4624.robot;

import org.usfirst.frc.team4624.robot.commands.GrabArms;
import org.usfirst.frc.team4624.robot.commands.MovePlanetary;
import org.usfirst.frc.team4624.robot.commands.ReleaseArms;
import org.usfirst.frc.team4624.robot.input.XboxController;

public class OI {
    public static XboxController xboxController = new XboxController();
    
    
    
    public OI() {
        xboxController.b.whenPressed( new MovePlanetary() );
        xboxController.rt.whenPressed(new ReleaseArms());
        xboxController.rt.whenReleased(new GrabArms());
    }
}
