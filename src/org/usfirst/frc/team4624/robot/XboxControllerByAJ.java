package org.usfirst.frc.team4624.robot;

//import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DriverStation;

public class XboxControllerByAJ {
	
	int controllerPort;
	DriverStation driverStation;
	Joystick controller;
	public Thumbstick leftThumbstick;
	public Thumbstick rightThumbstick;
	
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
	
	/*
	 * Constructor
	 */
	public XboxControllerByAJ() {
		int i = findXboxController();
		controllerPort = i;
		driverStation = DriverStation.getInstance();
		
		System.out.println( "[DEBUG] XboxControllerByAJ: Xbox controller found at port " + i + " (1-4)" );
		
		controller = new Joystick( controllerPort );
		this.leftThumbstick = new Thumbstick( controller, "left" );
		this.rightThumbstick = new Thumbstick( controller, "left" );
	}
	
	public class Thumbstick {
		
		Joystick controller;
		String hand;
		
		public Thumbstick( Joystick controller, String hand ) {
			this.controller = controller;
			this.hand = hand;
		}
		
		public double x() {
			if( hand.equals("left") ) {
				return controller.getRawAxis( 1 );
			}
			if( hand.equals("right") ) {
				return controller.getRawAxis( 4 );
			}
			return 0;
		}
		
		public double y() {
			if( hand.equals("left") ) {
				return controller.getRawAxis( 2 );
			}
			if( hand.equals("right") ) {
				return controller.getRawAxis( 5 );
			}
			return 0;
		}
		
		public boolean click() {
			return false;
		}
	}
	
	
}