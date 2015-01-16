package org.usfirst.frc.team4624.robot;

//public class OI {
//	public Joystick leftStick	= new Joystick( 0 );
//	public Joystick rightStick	= new Joystick( 1 );
//}

public class OI {
    public XboxController xbox = new XboxController(RobotMap.XBOX_CONTROLLER_PORT); //Be careful not to plug in multiple USB Items...
}