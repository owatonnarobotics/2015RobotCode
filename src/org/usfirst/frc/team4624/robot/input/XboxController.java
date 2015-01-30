package org.usfirst.frc.team4624.robot.input;   // Change to whatever package you would like

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * [class] XboxController
 * @author AJ Granowski & 4624 Owatonna Robotics
 * @version 2015
 * 
 * This class wraps around the Joystick class in order to make
 *     working with Xbox360 controllers less of a pain.
 * 
 * The values from this class can be used in two ways. One could
 *     either check each Button every cycle with .get(), or they
 *     could call commands directly from the Buttons with .whenPressed()
 * 
 * USAGE:
 *     // Initialization
 *     myXboxController = new XboxController( <port the controller is on> );
 *     myXboxController.leftStick.setThumbstickDeadZone( .2 );  // Optional. See code below for defaults.
 *     
 *     // Using buttons
 *     myXboxController.a.whenPressed( new MyCommand() );
 *     myXboxController.lb.toggleWhenPressed( new MyCommand() );
 *     myXboxController.rightStick.whenPressed( new MyCommand() );
 *     
 *     // Getting values directly
 *     if( myXboxController.leftStick.getY() > .4 ) ...
 *     
 *     // Support of legacy methods (NOTE: These values are straight from the Joystick class. No deadzone stuff or anything)
 *     if( xboxController.getX() > .4 ) ...
 */
public final class XboxController extends Joystick {    // Because this class is final, it cannot be extended.
    
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
    private static final double DEFAULT_TRIGGER_DEADZONE    = 0.01; // Jiggle room for the triggers
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
     * Constructor #1
     * @param port
     * 
     * There are two ways to make an XboxController. With this constructor,
     *     you can specify which port you expect the controller to be on.
     */
    public XboxController( final int port ) {
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
     * Constructor #2
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
        DPAD( final int value ) {
            this.value = value;
        }
        
        public static DPAD getEnum( int value ) {
            DPAD[] all = DPAD.values();
            
            for( int i = 0; i < all.length; i++ ) {
                if ( all[i].value == value ) {
                    return all[i] ;
                }
            }
            throw new UnsupportedOperationException( "Integer supplied (" + value + ") is not a possible value of this enum." );
        }
    }
    
    
    
    /**
     * [class] Thumbstick
     * 
     * This class is used to represent the thumbsticks on the
     *     Xbox360 controller.
     */
    public static class Thumbstick extends Button {
        
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
        Thumbstick( final Joystick parent, final HAND hand ) {
            
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
        public HAND getHand() {
            return hand;
        }
        
        public double getX() {
            // Don't adjust the sensitivity here
            return createDeadZone( parent.getRawAxis( xAxisID ), deadZone );     // Positive = Right
        }
        
        public double getY() {
            // Don't adjust the sensitivity here
            return createDeadZone( -parent.getRawAxis( yAxisID ), deadZone );    // Positive = Up
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
    public static class Trigger extends Button {
        
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
        Trigger( final Joystick joystick, final HAND hand ) {
            
            /* Initialize */
            this.parent         = joystick;
            this.hand           = hand;
            this.deadZone       = DEFAULT_TRIGGER_DEADZONE;
            this.sensitivity    = DEFAULT_TRIGGER_SENSITIVITY;
        }
        
        
        
        /* Extended Methods */
        @Override
        public boolean get() {
            return getX() > sensitivity;
        }
        
        
        
        /* Get */
        public HAND getHand() {
            return this.hand;
        }
        
        public double getX() {
            if ( hand == HAND.LEFT ) {
                return createDeadZone( parent.getRawAxis( LEFT_TRIGGER_AXIS_ID ), deadZone );
            } else {
                return createDeadZone( parent.getRawAxis( RIGHT_TRIGGER_AXIS_ID ), deadZone );
            }
        }
        
        public double getY() {
            return getX();	// Triggers have one dimensional movement. Use getX() instead
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
    public static class DirectionalPad extends Button {
        
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
        DirectionalPad( final Joystick parent ) {
            
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
        public static class DPadButton extends Button {
            
            /* Instance Values */
            private final DPAD              direction;
            private final DirectionalPad    parent;
            
            
            
            /**
             * Constructor
             * @param parent
             * @param dPad
             */
            DPadButton( final DirectionalPad parent, final DPAD dPadDirection ) {
                
                /* Initialize */
                this.direction  = dPadDirection;
                this.parent     = parent;
            }
            
            
            
            /* Extended Methods */
            @Override
            public boolean get() {
                return parent.getAngle() == direction.value;
            }
        }
        
        
        
        /* Extended Methods */
        @Override
        public boolean get() {
            return getAngle() != -1;
        }
        
        
        
        /* Get */
        public int getAngle() {
            return parent.getPOV();
        }
        
        public DPAD getDirection() {
            return DPAD.getEnum( getAngle() );
        }
    }
    
    
    
    /**
     * createDeadZone
     * @param input
     * @param deadZoneSize
     * @return adjusted_input
     * 
     * turns this
     * |--1--2--3--4--5--|
     * into this
     * ------|-1-2-3-4-5-|
     */
    private static double createDeadZone( double input, double deadZoneSize ) {
        double deadZoneSizeClamp = deadZoneSize;
        double negative;
        double adjusted;
        
        if ( deadZoneSizeClamp < 0 || deadZoneSizeClamp >= 1 ) {
            deadZoneSizeClamp = 0;  // Prevent any weird errors
        }
        
        negative    = input < 0 ? -1 : 1;
        
        adjusted    = Math.abs( input ) - deadZoneSizeClamp; // Subtract the deadzone from the magnitude
        adjusted    = adjusted < 0 ? 0 : adjusted;  // if the new input is negative, make it zero
        adjusted    = adjusted / ( 1 - deadZoneSizeClamp );  // Adjust the adjustment so it can max at 1
        
        return negative * adjusted;
    }
    
    
    
    /* Get */
    public int getPort() {
        return port;
    }
    
    public Joystick getJoystick() {
        return controller;
    }
}

/*

Magnitude
private double getAngle(double rawX, double rawY) {
    return  Math.toDegrees(Math.atan2(rawY, rawX));
}

private double compressAngle(double angle) { // Maybe make this a part of getScaleFactor
    
}

private double getScaleFactor(double angle) {
    while(angle < 0) {
        angle += 360;
    }
    if(angle > 90) {
        angle = angle % 90;
    }
    return Math.toDegrees(Math.cos(angle)); //This works for any values <= 45
}

private double getScaleFactorX(double angle, double scaleFactor) {
    return  scaleFactor * Math.toDegrees(Math.cos(angle));
}
private double getScaleFactorY(double angle, double scaleFactor) {
    return scaleFactor * Math.toDegrees(Math.sin(angle));
}

private double getTrueX(double scaleFactorX, double rawX) {
    return rawX * scaleFactorX;
}

private double getTrueY(double scaleFactorY, double rawY) {
    return rawY * scaleFactorY;
}

private double getMagnitude(double trueX, double trueY) {
    return Math.sqrt(Math.pow(trueX, 2) + Math.pow(trueY, 2));
}

public (something) getVector(double rawX, double rawY) {
    double angle        = getAngle(rawX, rawY);
    double smallAngle   = compressAngle(angle);
    double scaleFactor  = getScaleFactor(smallAngle);
    double scaleFactorX = getScaleFactorX(angle, scaleFactor);
    double scaleFactorY = getScaleFactorY(angle, scaleFactor);
    double trueX        = getTrueX(scaleFactorX, rawX);
    double trueY        = getTrueY(scaleFactorY, rawY);
    double magnitude    = getMagnitude(trueX, trueY);
    return (something);
}

*/