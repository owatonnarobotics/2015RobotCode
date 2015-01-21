package org.usfirst.frc.team4624.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
    
/*
 * [CLASS]
 * XboxController
 * A wrapper class for XboxControllers in the 2015 FRC competition
 * 
 * Thumbstick       leftStick
 * Thumbstick       rightSTick
 * Trigger          lt
 * Trigger          rt
 * DirectonalPad    dPad
 * Button           a
 * BUtton           b
 * Button           x
 * Button           y
 * Button           lb
 * Button           rb
 * Button           back
 * BUtton           start
 */
public class XboxController extends Joystick {
    
    // Modifiers
    private static final double thumbstickDeadZone  = 0.1;  // Jiggle room for the thumbsticks
    private static final double triggerDeadZone     = 0.0;  // Jiggle room for the triggers
    private static final double triggerSensitivity  = 0.6;  // If the trigger is beyond this limit, say it has been pressed
    
    /*
     * ====RETURN VALUES====
     */
    
    /*
     * Thumbstick
     * 
     * double	x()
     * double	y()
     * Button	pressed
     */
    public Thumbstick leftStick;
    public Thumbstick rightStick;
    
    /*
     * Trigger
     * 
     * double	x()
     * double	y()
     * boolean	get()
     */
    public Trigger lt;
    public Trigger rt;
    
    /*
     * DirectonalPad
     * 
     * int		    angle()
     * DPadButton   up
     * DPadButton   upRight
     * DPadButton   right
     * DPadButton   downRight
     * DPadButton   down
     * DPadButton   downLeft
     * DPadButton   left
     * DPadButton   upLeft
     */
    public DirectionalPad dPad;
    
    /*
     * Button
     * 
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
     * ====CONSTANTS====
     */
    // These are the mappings for the 2015 WPILib
    
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
    
    
    
    /*
     * Constructor
     */
    public XboxController() {
        super( 0 );
        createXboxControllerInstance( 0 );
    }
    
    public XboxController( int port ) {
        super( port );
        createXboxControllerInstance( port );
    }
    
    /*
     * Constructor function
     */
    protected final void createXboxControllerInstance( int port ) {
        
        Joystick controller = new Joystick( port );		// Joystick referenced by everything
        
        // ==DEBUGGING==
        System.out.println( "==XboxController instance==");
        System.out.println( "Total buttons: " + controller.getButtonCount() );
        System.out.println( "Total axis's: " + controller.getAxisCount() );
        System.out.println( "Total POVs: " + controller.getPOVCount() );
        System.out.println( "====END OF INFORMATION=====" );
        
        // Thumbsticks
        this.leftStick  = new Thumbstick( controller, HAND.LEFT );
        this.rightStick = new Thumbstick( controller, HAND.RIGHT );
        
        // DPads
        this.dPad       = new DirectionalPad( controller );	// Voted this year's "Most likely to fail so don't rely on me"
        
        // Triggers (Remember, they're not buttons [but we could emulate them])
        this.lt         = new Trigger( controller, HAND.LEFT );
        this.rt         = new Trigger( controller, HAND.RIGHT );
        
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
    private double deadZone( double input, double deadZone ) {
        boolean isNegative  = input < 0;    // Duh
        double adjusted     = Math.abs( input ) - deadZone; // Subtract the deadzone from the magnitude
        
        adjusted    = adjusted < 0 ? 0 : adjusted;  // if the new input is negative, make it zero
        
        adjusted    = adjusted / ( 1 - deadZone );  // Adjust the adjustment so it can max at 1
        
        if( isNegative ) {
        	return -1 * adjusted;
        } else {
        	return adjusted;
        }
    }
    
    
    
    /*
     * [ENUM]
     * HAND
     * Used in some classes because int's are bad mm'k?
     */
    enum HAND {
        LEFT, RIGHT
    }
    
    /*
     * [ENUM]
     * DPAD
     * Mappings for the directonal pad
     * 
     * int  value()
     */
    enum DPAD {
        UP (0),
        UP_RIGHT (1),
        RIGHT (2),
        DOWN_RIGHT (3),
        DOWN (4),
        DOWN_LEFT (5),
        LEFT (6),
        UP_LEFT (7);
        
        private int value;
        
        DPAD( int id ) {
            this.value = id;
        }
        
        int value( DPAD dPad ) {
            return this.value;
        }
    }
    
    
    
    /*
     * [CLASS]
     * Thumbstick
     * Every Xbox Controller has 2
     */
    public class Thumbstick extends Button {
        
        /*
         * RETURN VALUES
         */
        public final Button pressed;
        
        /*
         * LOCAL VARIABLES
         */
        private final Joystick parent;
        private final HAND hand;
        private final int xAxisID;
        private final int yAxisID;
        
        
        /*
         * Constructor
         */
        Thumbstick( Joystick parent, HAND hand ) {
            
            this.parent = parent;
            this.hand   = hand;
            
            if( hand == HAND.LEFT ) {
                this.xAxisID    = leftXThumbstickID;
                this.yAxisID    = leftYThumbstickID;
                this.pressed    = new JoystickButton( parent, leftThumbstickButtonID );
            } else {
                this.xAxisID    = rightXThumbstickID;
                this.yAxisID    = rightYThumbstickID;
                this.pressed    = new JoystickButton( parent, rightThumbstickButtonID );
            }
        }
        
        public HAND getHand() {
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
        
        public boolean get() {
            return  ( this.x() != 0 ) ||
                    ( this.y() != 0 ) ||
                    this.pressed.get();
        }
    }
    
    
    
    /*
     * [CLASS]
     * Trigger
     * Again, every controller has 2
     */
    public class Trigger extends Button {
        
        /*
         * LOCAL VARIABLES
         */
        private final Joystick parent;
        private final HAND hand;
        
        /*
         * Constructor
         */
        public Trigger( Joystick joystick, HAND hand ) {
            this.parent = joystick;
            this.hand = hand;
        }
        
        public double x() {
            if( hand == HAND.LEFT ) {
                return deadZone( parent.getRawAxis( leftTriggerAxisID ), triggerDeadZone );
            } else {
                return deadZone( parent.getRawAxis( rightTriggerAxisID ), triggerDeadZone );
            }
        }
        public double y() {
            return this.x();	// One dimensional movement. Use x() instead
        }
        
        public HAND getHand() {
            return this.hand;
        }
        
        public boolean get() {
            if( hand == HAND.LEFT ) {
                return this.x() > triggerSensitivity;
            } else {
                return this.x() < -triggerSensitivity;
            }
        }
        
    }
    
    
    
    public class DirectionalPad extends Button {
        
        /*
         * RETURN VALUES
         */
        public Button up;
        public Button upRight;
        public Button right;
        public Button downRight;
        public Button down;
        public Button downLeft;
        public Button left;
        public Button upLeft;
        
        /*
         * LOCAL VARIABLES
         */
        private Joystick parent;
        
        /*
         * Constructor
         */
        public DirectionalPad( Joystick parent ) {
            this.parent	= parent;
            this.up         = new DPadButton( this, DPAD.UP );
            this.upRight    = new DPadButton( this, DPAD.UP_RIGHT );
            this.right      = new DPadButton( this, DPAD.RIGHT );
            this.downRight  = new DPadButton( this, DPAD.DOWN_RIGHT );
            this.down       = new DPadButton( this, DPAD.DOWN );
            this.downLeft   = new DPadButton( this, DPAD.DOWN_LEFT );
            this.left       = new DPadButton( this, DPAD.LEFT );
            this.upLeft     = new DPadButton( this, DPAD.UP_LEFT );
        }
        
        
        public int angle() {
            return this.parent.getPOV();
        }
        public boolean get() {
            return this.angle() != -1;
        }
        
        
        /*
         * [CLASS]
         * DPadButton
         * Make each directon on the DPad a button
         */
        public class DPadButton extends Button {
            
            private final DPAD id;
            private final DirectionalPad parent;
            
            public DPadButton( DirectionalPad parent, DPAD dPad ) {
                this.id     = dPad;
                this.parent = parent;
            }
            
            public boolean get() {
                return parent.angle() == this.id.value;
            }
        }
    }
}