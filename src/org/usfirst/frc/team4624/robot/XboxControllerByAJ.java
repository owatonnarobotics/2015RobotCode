package org.usfirst.frc.team4624.robot;

import edu.wpi.first.wpilibj.Joystick;

public class XboxControllerByAJ {
	
	/*
	 * Find a device that behaves like an Xbox controller. It probably is one.
	 */
	private int findXboxController() {
		Joystick testStick;
		
		for ( int i = 1; i != 4; i++ ) {
			
			testStick = new Joystick(i);
			
			if( testStick.getAxisCount() == 4 ) {
				return i;
			}
		}
		return 0;
	}
	
	public XboxControllerByAJ() {
		
		int i \	= findXboxController();
		
		System.out.println("[DEBUG] XboxControllerByAJ: Xbox controller found at port " + i + " (1-4)");
	}
}
