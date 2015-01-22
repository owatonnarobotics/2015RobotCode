package org.usfirst.frc.team4624.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
    
/**
 * [class] XboxController
 * @author AJ Granowski
 * @version 2015
 * 
 * This class wraps around the Joystick class in order to make
 *     working with Xbox360 controllers less of a pain.
 * 
 * The values from this class can be used in two ways. One could
 *     either check each Button every cycle with .get(), or they 
 *     could call commands directly from the Buttons with .whenPressed()
 */
public class XboxController extends Joystick {
    
    /* Button Mappings */
    private static final int    A_BUTTON_ID                = 1;
    private static final int    B_BUTTON_ID                = 2;
    private static final int    X_BUTTON_ID                = 3;
    private static final int    Y_BUTTON_ID                = 4;
    private static final int    LB_BUTTON_ID               = 5;
    private static final int    RB_BUTTON_ID               = 6;
    private static final int    BACK_BUTTON_ID             = 7;
    private static final int    START_BUTTON_ID            = 8;
    private static final int    LEFT_THUMBSTIKC_BUTTON_ID  = 9;
    private static final int    RIGHT_THUMBSTICK_BUTTON_ID = 10;
    
    /* Axis Mappings */
    private static final int    LEFT_THUMBSTICK_X_AXIS_ID  = 0;
    private static final int    LEFT_THUMBSTICK_Y_AXIS_ID  = 1;
    private static final int    LEFT_TRIGGER_AXIS_ID       = 2;
    private static final int    RIGHT_TRIGGER_AXIS_ID      = 3;
    private static final int    RIGHT_THUMBSTICK_X_AXIS_ID = 4;
    private static final int    RIGHT_THUMBSTICK_Y_AXIS_ID = 5;
    
    /* Default Values */
    private static final double DEFAULT_THUMBSTICK_DEADZONE = 0.1;  // Jiggle room for the thumbsticks
    private static final double DEFAULT_TRIGGER_DEADZONE    = 0.0;  // Jiggle room for the triggers
    private static final double DEFAULT_TRIGGER_SENSITIVITY = 0.6;  // If the trigger is beyond this limit, say it has been pressed
    
    /* Instance Values */
    private final int           port;
    private final Joystick      controller;
    public final Thumbstick     leftStick;
    public final Thumbstick     rightStick;
    public final Trigger        lt;
    public final Trigger        rt;
    public final DirectionalPad dPad;
    public final Button         a;
    public final Button         b;
    public final Button         x;
    public final Button         y;
    public final Button         lb;
    public final Button         rb;
    public final Button         back;
    public final Button         start;
    
    
    
    /**
     * Constructor
     * @param port
     * 
     * There are two ways to make an XboxController. With this constructor,
     *     you can specify which port you expect the controller to be on.
     */
    public XboxController( int port ) {
        super( port );  // Extends Joystick...
        
        /* Initialize */
        this.port       = port;
        this.controller = new Joystick( this.port );    // Joystick referenced by everything
        this.leftStick  = new Thumbstick( this.controller, HAND.LEFT );
        this.rightStick = new Thumbstick( this.controller, HAND.RIGHT );
        this.dPad       = new DirectionalPad( this.controller );
        this.lt         = new Trigger( this.controller, HAND.LEFT );
        this.rt         = new Trigger( this.controller, HAND.RIGHT );
        this.a          = new JoystickButton( this.controller, A_BUTTON_ID );
        this.b          = new JoystickButton( this.controller, B_BUTTON_ID );
        this.x          = new JoystickButton( this.controller, X_BUTTON_ID );
        this.y          = new JoystickButton( this.controller, Y_BUTTON_ID );
        this.lb         = new JoystickButton( this.controller, LB_BUTTON_ID );
        this.rb         = new JoystickButton( this.controller, RB_BUTTON_ID );
        this.back       = new JoystickButton( this.controller, BACK_BUTTON_ID );
        this.start      = new JoystickButton( this.controller, START_BUTTON_ID );
    }
    
    
    
    /**
     * Constructor
     * 
     * This is the other constructor. I would recommend using this one instead
     *     as it is unlikely that anything else but the XboxController will be
     *     connected.
     */
    public XboxController() {
        super( 0 ); // Extends Joystick...
        
        /* Initialize */
        this.port       = 0;
        this.controller = new Joystick( this.port );    // Joystick referenced by everything
        this.leftStick  = new Thumbstick( this.controller, HAND.LEFT );
        this.rightStick = new Thumbstick( this.controller, HAND.RIGHT );
        this.dPad       = new DirectionalPad( this.controller );
        this.lt         = new Trigger( this.controller, HAND.LEFT );
        this.rt         = new Trigger( this.controller, HAND.RIGHT );
        this.a          = new JoystickButton( this.controller, A_BUTTON_ID );
        this.b          = new JoystickButton( this.controller, B_BUTTON_ID );
        this.x          = new JoystickButton( this.controller, X_BUTTON_ID );
        this.y          = new JoystickButton( this.controller, Y_BUTTON_ID );
        this.lb         = new JoystickButton( this.controller, LB_BUTTON_ID );
        this.rb         = new JoystickButton( this.controller, RB_BUTTON_ID );
        this.back       = new JoystickButton( this.controller, BACK_BUTTON_ID );
        this.start      = new JoystickButton( this.controller, START_BUTTON_ID );
    }
    
    
    
    /**
     * [enum] HAND
     * 
     * Rather than use an integer (which might not be what we expect)
     *    we use an enum which has a set amount of possibilities.
     */
    static enum HAND {
        LEFT, RIGHT
    }
    
    
    
    /**
     * [enum] DPAD
     * 
     * This is the relation of direction and number for .getPOV() used
     *     in the DirectionalPad class.
     */
    static enum DPAD {
        UP          (0),
        UP_RIGHT    (45),
        RIGHT       (90),
        DOWN_RIGHT  (135),
        DOWN        (180),
        DOWN_LEFT   (225),
        LEFT        (270),
        UP_LEFT     (315);
        
        
        
        /* Instance Value */
        private int value;
        
        
        
        /**
         * Constructor
         * @param value
         */
        DPAD( int value ) {
            this.value = value;
        }
    }
    
    
    
    /**
     * [class] Thumbstick
     * 
     * This class is used to represent the thumbsticks on the
     *     Xbox360 controller.
     */
    public final class Thumbstick extends Button {
        
        /* Instance Values */
        private final Joystick  parent;
        private final HAND      hand;
        private final int       xAxisID;
        private final int       yAxisID;
        private final int       pressedID;
        private double          deadZone;
        
        
        
        /**
         * Constructor
         * @param parent
         * @param hand
         */
        Thumbstick( Joystick parent, HAND hand ) {
            
            /* Initialize */
            this.parent     = parent;
            this.hand       = hand;
            this.deadZone   = DEFAULT_THUMBSTICK_DEADZONE;
            
            if ( hand == HAND.LEFT ) {
                this.xAxisID    = LEFT_THUMBSTICK_X_AXIS_ID;
                this.yAxisID    = LEFT_THUMBSTICK_Y_AXIS_ID;
                this.pressedID	= LEFT_THUMBSTIKC_BUTTON_ID;
            } else {                                            // If right hand
                this.xAxisID    = RIGHT_THUMBSTICK_X_AXIS_ID;
                this.yAxisID    = RIGHT_THUMBSTICK_Y_AXIS_ID;
                this.pressedID	= RIGHT_THUMBSTICK_BUTTON_ID;
            }
        }
        
        
        
        /* Extended methods */
        @Override
        public boolean get() {
        	return parent.getRawButton( pressedID );
        }
        
        
        
        /* Get */
        public final HAND getHand() {
            return this.hand;
        }
        
        public double getX() {
            // Don't adjust the sensitivity here
            return deadZone( parent.getRawAxis( xAxisID ), this.deadZone );     // Positive = Right
        }
        
        public double getY() {
            // Don't adjust the sensitivity here
            return deadZone( -parent.getRawAxis( yAxisID ), this.deadZone );    // Positive = Up
        }
        
        
        
        /* Set */
        public void setThumbstickDeadZone( double number ) {
            this.deadZone = number;
        }
    }
    
    
    
    /**
     * [class] Trigger
     * 
     * This class is used to represent one of the two
     *     Triggers on an Xbox360 controller.
     */
    public final class Trigger extends Button {
        
        /* Instance Values */
        private final Joystick  parent;
        private final HAND      hand;
        private double          deadZone;
        private double          sensitivity;
        
        
        
        /**
         * Constructor
         * @param joystick
         * @param hand
         */
        public Trigger( Joystick joystick, HAND hand ) {
            
            /* Initialize */
            this.parent         = joystick;
            this.hand           = hand;
            this.deadZone       = DEFAULT_TRIGGER_DEADZONE;
            this.sensitivity    = DEFAULT_TRIGGER_SENSITIVITY;
        }
        
        
        
        /* Extended Methods */
        @Override
        public boolean get() {
            if ( hand == HAND.LEFT ) {
                return this.getX() > this.sensitivity;
            } else {                                    // If right hand...
                return this.getX() > this.sensitivity;
            }
        }
        
        
        
        /* Get */
        public HAND getHand() {
            return this.hand;
        }
        
        public double getX() {
            if ( hand == HAND.LEFT ) {
                return deadZone( parent.getRawAxis( LEFT_TRIGGER_AXIS_ID ), this.deadZone );
            } else {
                return deadZone( parent.getRawAxis( RIGHT_TRIGGER_AXIS_ID ), this.deadZone );
            }
        }
        
        public double getY() {
            return this.getX();	// Triggers have one dimensional movement. Use getX() instead
        }
        
        
        
        /* Set */
        public void setTriggerDeadZone( double number ) {
            this.deadZone = number;
        }
        
        public void setTriggerSensitivity( double number ) {
            this.sensitivity = number;
        }
    }
    
    
    
    /**
     * [class] DirectionalPad
     * 
     * This is a weird object which is essentially just 8 buttons.
     */
    public final class DirectionalPad extends Button {
        
        /* Instance Values */
        private Joystick    parent;
        public Button       up;
        public Button       upRight;
        public Button       right;
        public Button       downRight;
        public Button       down;
        public Button       downLeft;
        public Button       left;
        public Button       upLeft;
        
        
        
        /**
         * Constructor
         * @param parent
         */
        public DirectionalPad( Joystick parent ) {
            
            /* Initialize */
            this.parent	    = parent;
            this.up         = new DPadButton( this, DPAD.UP );
            this.upRight    = new DPadButton( this, DPAD.UP_RIGHT );
            this.right      = new DPadButton( this, DPAD.RIGHT );
            this.downRight  = new DPadButton( this, DPAD.DOWN_RIGHT );
            this.down       = new DPadButton( this, DPAD.DOWN );
            this.downLeft   = new DPadButton( this, DPAD.DOWN_LEFT );
            this.left       = new DPadButton( this, DPAD.LEFT );
            this.upLeft     = new DPadButton( this, DPAD.UP_LEFT );
        }
        
        
        
        /**
         * [class] DPadButton
         * 
         * This class is used to represent each of the 8 values a
         *     dPad has as a button.
         */
        public class DPadButton extends Button {
            
            /* Instance Values */
            private final DPAD              direction;
            private final DirectionalPad    parent;
            
            
            
            /**
             * Constructor
             * @param parent
             * @param dPad
             */
            public DPadButton( DirectionalPad parent, DPAD dPadDirection ) {
                
                /* Initialize */
                this.direction  = dPadDirection;
                this.parent     = parent;
            }
            
            
            
            /* Extended Methods */
            @Override
            public boolean get() {
                return parent.getAngle() == this.direction.value;
            }
        }
        
        
        
        /* Extended Methods */
        @Override
        public boolean get() {
            return this.getAngle() != -1;
        }
        
        
        
        /* Get */
        public int getAngle() {
            return this.parent.getPOV();
        }
        
        
    }
    
    
    
    /**
     * deadZone
     * @param input
     * @param deadZoneSize
     * @return adjusted_input
     * 
     * turns this
     * |--1--2--3--4--5--|
     * into this
     * ------|-1-2-3-4-5-|
     */
    private double deadZone( double input, double deadZoneSize ) {
        boolean isNegative  = input < 0;    // Duh
        double adjusted     = Math.abs( input ) - deadZoneSize; // Subtract the deadzone from the magnitude
        
        adjusted    = adjusted < 0 ? 0 : adjusted;  // if the new input is negative, make it zero
        
        adjusted    = adjusted / ( 1 - deadZoneSize );  // Adjust the adjustment so it can max at 1
        
        if( isNegative ) {
            return -1 * adjusted;
        } else {
            return adjusted;
        }
    }
    
    
    
    /* Get */
    public final int getPort() {
        return this.port;
    }
    
    public final Joystick getJoystick() {
        return this.controller;
    }
}