package org.usfirst.frc.team4624.robot;



public class RobotMap {
    
    /* Digital IO Ports */
    /** Used with the forklift */
    public static final int      LIFT_ENCODER_A             = 0;
    
    /** Used with the forklift */
    public static final int      LIFT_ENCODER_B             = 1;
    
    
    
    /* PWM Ports */
    /** The PWM port the the lift motors */
    public static final int      PWM_LIFT_PORT              = 0;
    
    /** Mecanum PWM Port */
    public static final int      PWM_MOTOR_REAR_LEFT_PORT   = 1;
    
    /** Mecanum PWM Port */
    public static final int      PWM_MOTOR_REAR_RIGHT_PORT  = 2;
    
    /** Mecanum PWM Port */
    public static final int      PWM_MOTOR_FRONT_LEFT_PORT  = 3;
    
    /** Mecanum PWM Port */
    public static final int      PWM_MOTOR_FRONT_RIGHT_PORT = 4;
    
    
    
    /* CAN Bus addresses */
    /** The CAN address for the Pneumatics Control Module (PCM) */
    public static final int      PCM_CAN_ID                 = 1;
    
    
    
    /* Pneumatics */
    /** The port for the pneumatic arms on the PCM */
    public static final int      PCM_SOLENOID_PORT          = 0;
    
    /** The CAN address for the compressor */
    public static final int      PCM_COMPRESSOR_PORT        = 1;
    
    
    
    /* Global */
    /** The max rotations allowed by the forklift */
    public static final double   FORKLIFT_MAX_ROTATIONS     = 5.5;
    
    /** The speed at which the position changes for manual control */
    public static final double   MANUAL_LIFT_SPEED          = .5;                         // Value between 0 and 1
                                                                                           
    /** Array of all the heights for the Forklift */
    public static final double[] LIFT_HEIGHTS_GROUND        = {0, 1.381, 2.714, 3.867};
    
    /** Array of all the heights for the Forklift */
    public static final double[] LIFT_HEIGHTS_STEP          = {.311, 1.666, 2.893, 4.212};
    
    /** Array of all the heights for the Forklift
     * 0 is ground
     * .5  is carry tote
     * 1.1 is grab  bin
     * 1.6 is carry bin
     */
    public static final double[] LIFT_HEIGHTS_AUTO          = {0, .5, 1.1, 1.6};
    
    /** The margin of error for updating the rate of the motor */
    public static final double   RATE_MARGIN_OF_ERROR       = .01;
    
    /** The rate at which the motor changes the PWM level */
    public static final double   RATE_CHANGE                = .01;
    
    /** The rate of the forklift in level mode */
    public static final double   LEVEL_RATE                 = 0.5;
    
    public static final int      PORT_ENCODER_RESET         = 2;
    
    public static final double   FORKLIFT_SLOW_ZONE         = .75;
    
    public static final double   FORKLIFT_SLOW_ZONE_RATE    = -.1;
    
    public static final int      PORT_TOTE_DETECTOR         = 3;

    public static final double   ROTATE_SPEED               = .5;
    
}
