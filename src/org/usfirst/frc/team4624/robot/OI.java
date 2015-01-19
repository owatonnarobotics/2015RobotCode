package org.usfirst.frc.team4624.robot;

import org.usfirst.frc.team4624.robot.input.XboxController;
import org.usfirst.frc.team4624.robot.input.XboxControllerByAJv2;

public class OI {
    //public XboxController xboxController = new XboxController(RobotMap.XBOX_CONTROLLER_PORT);
	public XboxControllerByAJv2 xboxController = new XboxControllerByAJv2( RobotMap.XBOX_CONTROLLER_PORT );
}
