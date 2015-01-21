package org.usfirst.frc.team4624.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
    

// extend joystick?
public class XboxController {
    
    /*
     * RETURN VALUES
     */
    
    /*
     * Thumbstick
     * double	x()
     * double	y()
     * Button	pressed
     */
    public Thumbstick leftStick;
    public Thumbstick rightStick;
    
    /*
     * Trigger
     * double	x()
     * double	y()
     * boolean	get()
     */
    public Trigger lt;
    public Trigger rt;
    
    /*
     * DirectonalPad
     * int		angle()
     * boolean	get~
     */
    public DirectionalPad dPad;
    
    /*
     * Button
     * I dunno, check the docs
     */
    public Button a;
    public Button b;
    public Button x;
    public Button y;
    public Button lb;
    public Button rb;
    public Button back;
    public Button start;
    
    
    
    /*
     * CONSTANTS
     */
    // Buttons
    private static final int aButtonID                  = 1;
    private static final int bButtonID                  = 2;
    private static final int xButtonID                  = 3;
    private static final int yButtonID                  = 4;
    private static final int lbButtonID                 = 5;
    private static final int rbButtonID                 = 6;
    private static final int backButtonID               = 7;
    private static final int startButtonID              = 8;
    private static final int leftThumbstickButtonID     = 9;
    private static final int rightThumbstickButtonID    = 10;
    
    // Axis's
    private static final int leftXThumbstickID  = 0;
    private static final int leftYThumbstickID  = 1;
    private static final int leftTriggerAxisID  = 2;
    private static final int rightTriggerAxisID = 3;
    private static final int rightXThumbstickID = 4;    // Correct
    private static final int rightYThumbstickID = 5;    // Correct
    
    // Modifiers
    private static final double thumbstickDeadZone  = 0.1;  // Jiggle room for the thumbsticks
    private static final double triggerDeadZone     = 0.0;  // Jiggle room for the triggers
    private static final double triggerSensitivity  = 0.6;  // If the trigger is beyond this limit, say it has been pressed
    
    
    /*
     * Constructor
     */
    public XboxController() {
        //super( 0 );   // extend joystick?
        createXboxControllerInstance( 0 );
    }
    
    public XboxController( int port ) {
        //super( port );    // extend joystick?
        createXboxControllerInstance( port );
    }
    
    /*
     * Primary constructor (for when you want to specify the port)
     */
    private final void createXboxControllerInstance( int port ) {
        
        Joystick controller = new Joystick( port );		// Joystick referenced by everything
        
        // ==DEBUGGING==
        System.out.println( "==XboxController instance==");
        System.out.println( "Total buttons: " + controller.getButtonCount() );
        System.out.println( "Total axis's: " + controller.getAxisCount() );
        System.out.println( "Total POVs: " + controller.getPOVCount() );
        System.out.println( "====END OF INFORMATION=====" );
        
        // Thumbsticks
        this.leftStick  = new Thumbstick( controller, Hand.LEFT );
        this.rightStick = new Thumbstick( controller, Hand.RIGHT );
        
        // DPads
        this.dPad       = new DirectionalPad( controller );	// Voted this year's "Most likely to fail so don't rely on me"
        
        // Triggers (Remember, they're not buttons [but we could emulate them])
        this.lt         = new Trigger( controller, Hand.LEFT );
        this.rt         = new Trigger( controller, Hand.RIGHT );
        
        // Buttons
        this.a          = new JoystickButton( controller, aButtonID );
        this.b          = new JoystickButton( controller, bButtonID );
        this.x          = new JoystickButton( controller, xButtonID );
        this.y          = new JoystickButton( controller, yButtonID );
        this.lb         = new JoystickButton( controller, lbButtonID );
        this.rb         = new JoystickButton( controller, rbButtonID );
        this.back       = new JoystickButton( controller, backButtonID );
        this.start      = new JoystickButton( controller, startButtonID );
    }
    
    /*
     * deadZone( double, double )
     * turns this
     * |--1--2--3--4--5--|
     * into this
     * ------|-1-2-3-4-5-|
     */
    private double deadZone( double input, double deadZone) {
        boolean isNegative  = input < 0;	// Duh
        double adjusted     = Math.abs( input ) - deadZone;	// Subtract the deadzone from the magnitude
        
        adjusted    = adjusted < 0 ? 0 : adjusted;	// if the new input is negative, make it zero
        
        adjusted    = adjusted / ( 1 - deadZone );	// Adjust the adjustment so it can max at 1
        
        if( isNegative ) {
        	return -1 * adjusted;
        } else {
        	return adjusted;
        }
    }
    
    /*
     * Hand
     * Used in some classes because int's are bad mm'k?
     */
    enum Hand {
        LEFT, RIGHT
    }
    
    /*
     * Thumbstick
     * Every Xbox Controller has 2
     */
    public class Thumbstick {
        
        /*
         * RETURN VALUES
         */
        public final Button pressed;
        
        /*
         * LOCAL VARIABLES
         */
        private final Joystick parent;
        private final Hand hand;
        private final int xAxisID;
        private final int yAxisID;
        
        
        /*
         * Constructor
         */
        Thumbstick( Joystick parent, Hand hand ) {
            
            this.parent = parent;
            this.hand   = hand;
            
            if( hand == Hand.LEFT ) {
                this.xAxisID    = leftXThumbstickID;
                this.yAxisID    = leftYThumbstickID;
                this.pressed    = new JoystickButton( parent, leftThumbstickButtonID );
            } else {
                this.xAxisID    = rightXThumbstickID;
                this.yAxisID    = rightYThumbstickID;
                this.pressed    = new JoystickButton( parent, rightThumbstickButtonID );
            }
        }
        
        public Hand getHand() {
            return this.hand;
        }
        
        
        public double x() {
            // Don't adjust the sensitivity here
            return deadZone( parent.getRawAxis( xAxisID ), thumbstickDeadZone );
        }
        public double y() {
            // Don't adjust the sensitivity here
            return deadZone( parent.getRawAxis( yAxisID ), thumbstickDeadZone );
        }
        
        // TODO
        // Make this a button somehow...
        public boolean get() {
            return ( this.x() != 0 ) || ( this.y() != 0 );
        }
    }
    
    /*
     * Trigger
     * Again, every controller has 2
     */
    public class Trigger {
        
        /*
         * LOCAL VARIABLES
         */
        private final Joystick parent;
        private final Hand hand;
        
        /*
         * Constructor
         */
        public Trigger( Joystick joystick, Hand hand ) {
            this.parent = joystick;
            this.hand = hand;
        }
        
        public double x() {
            if( hand == Hand.LEFT ) {
                return deadZone( parent.getRawAxis( leftTriggerAxisID ), triggerDeadZone );
            } else {
                return deadZone( parent.getRawAxis( rightTriggerAxisID ), triggerDeadZone );
            }
        }
        public double y() {
            return this.x();	// One dimensional movement. Use x() instead
        }
        
        public Hand getHand() {
            return this.hand;
        }
        
        // TODO
        // Make this a button somehow...
        public boolean get() {
            if( hand == Hand.LEFT ) {
                return this.x() > triggerSensitivity;
            } else {
                return this.x() < -triggerSensitivity;
            }
        }
        
    }
    
    public class DirectionalPad {
        
        /*
         * LOCAL VARIABLES
         */
        private Joystick parent;
        
        /*
         * Constructor
         */
        public DirectionalPad( Joystick parent ) {
            this.parent	= parent;
        }
        
        
        public int angle() {
            return parent.getPOV();
        }
        
        // TODO
        // Make these buttons somehow...
        public boolean get() {
            return this.angle() != 0;
        }
        public boolean getUp() {
            return this.angle() == 0;
        }
        public boolean getUpRight() {
            return this.angle() == 1;
        }
        public boolean getRight() {
            return this.angle() == 2;
        }
        public boolean getDownRight() {
            return this.angle() == 3;
        }
        public boolean getDown() {
            return this.angle() == 4;
        }
        public boolean getDownLeft() {
            return this.angle() == 5;
        }
        public boolean getLeft() {
            return this.angle() == 6;
        }
        public boolean getUpLeft() {
            return this.angle() == 7;
        }
    }
}