package org.usfirst.frc.team4624.robot;

import org.usfirst.frc.team4624.robot.commands.MovePlanetary;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
    public XboxController xboxController = new XboxController(RobotMap.XBOX_CONTROLLER_PORT);
    
    private Button planetaryToggle;
    
    public OI() {
    	planetaryToggle = new JoystickButton(xboxController, RobotMap.XBOX_BUTTON_PLANETARY_TOGGLE);
    	
    	planetaryToggle.whenPressed(new MovePlanetary());
    }
}
