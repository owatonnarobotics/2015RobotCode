package org.usfirst.frc.team4624.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class XboxControllerByAJv2 {
	
	// Return values
	public Thumbstick leftStick;
	public Thumbstick rightStick;
	
	//public DirectionalPad dPad;
	
	public Button a;
	public Button b;
	public Button x;
	public Button y;
	public Button lb;
	public Button rb;
	public Button lt;
	public Button rt;
	public Button back;
	public Button start;
	//public Button home;
	
	private static final int aButtonID					= 1;
	private static final int bButtonID					= 2;
	private static final int xButtonID					= 3;
	private static final int yButtonID					= 4;
	
	private static final int lbButtonID					= 5;
	private static final int rbButtonID					= 6;
	private static final int backButtonID				= 7;
	private static final int startButtonID				= 8;
	private static final int leftThumbstickButtonID		= 9;
	private static final int rightThumbstickButtonID	= 9;
	private static final int rtButtonID					= 11;
	private static final int ltButtonID					= 12;
	
	private static final int leftXThumbstickID	= 1;
	private static final int leftYThumbstickID	= 2;
	//private static final int kTrigger_val = 3;
	private static final int rightXThumbstickID	= 4;
	private static final int rightYThumbstickID	= 5;
	//private static final int kDLeftRight_val = 6;
	
	
	
	
	
	
	
	/*
	 * Implement later
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
	
	
	
	
	
	
	
	public XboxControllerByAJv2( int port ) {
		
		Joystick controller	= new Joystick( port );
		
		this.leftStick	= new Thumbstick( controller, Hand.LEFT );
		this.rightStick	= new Thumbstick( controller, Hand.RIGHT );
		
		// this.dPad	= new dPad( controller );	// Doesn't exist right now
		
		this.a		= new JoystickButton( controller, aButtonID );
		this.b		= new JoystickButton( controller, bButtonID );
		this.x		= new JoystickButton( controller, xButtonID );
		this.y		= new JoystickButton( controller, yButtonID );
		this.lb		= new JoystickButton( controller, lbButtonID );
		this.rb		= new JoystickButton( controller, rbButtonID );
		this.lt		= new JoystickButton( controller, ltButtonID );
		this.rt		= new JoystickButton( controller, rtButtonID );
		this.back	= new JoystickButton( controller, backButtonID );
		this.start	= new JoystickButton( controller, startButtonID );
		//this.home	= new JoystickButton( controller, homeButtonID );
	}
	
	
	
	enum Hand {
		LEFT, RIGHT
	}
	
	/*
	 * Thumbstick. Every Xbox Controller has 2
	 */
	public class Thumbstick {
		
		private final Joystick parent;
		private final Hand hand;
		private final int xAxisID;
		private final int yAxisID;
		
		/*
		 * Constructor
		 */
		Thumbstick( Joystick parent, Hand hand ) {
			
			this.parent	= parent;
			this.hand	= hand;
			
			if( hand == Hand.LEFT ) {
				this.xAxisID = leftXThumbstickID;
				this.yAxisID = leftYThumbstickID;
			} else {
				this.xAxisID = rightXThumbstickID;
				this.yAxisID = rightYThumbstickID;
			}
		}
		
		public double x() {
			return parent.getRawAxis( xAxisID );
		}
		
		public double y() {
			return parent.getRawAxis( yAxisID );
		}
	}
	
	/*
	 * TODO
	 */
	public class DirectionalPad {
		
	}
	
}