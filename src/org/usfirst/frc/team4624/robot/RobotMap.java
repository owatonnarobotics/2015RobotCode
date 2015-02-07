package org.usfirst.frc.team4624.robot;

public class RobotMap {
    
    public static final int PWM_LIFT_PORT = 0;
    
    public static final int LIFT_ENCODER_A = 0;
    public static final int LIFT_ENCODER_B = 1;
    
    /* PWM Ports */
/** The PWM port for the left motor in tankdrive mode. */
    public static final int PORT_MOTOR_LEFT             = 1; //TODO Switch these ports?
    
/** The PWM port for the right motor in tankdrive mode. */
    public static final int PORT_MOTOR_RIGHT            = 0;
    
    
    
    /* CAN Bus addresses */
/** The CAN address for the compressor */
    public static final int PORT_COMPRESSOR             = 1;
    
/** The CAN address for the Pneumatics Control Module (PCM) */
    public static final int PCM_CAN_ID                  = 1;
    
    
    
    /* Pneumatics */
/** The port for the pneumatic arms on the PCM */
    public static final int PCM_SOLENOID_PORT           = 0;
    
    
    
    /* Global */
/** The max rotations allowed by the forklift */
    public static final double FORKLIFT_MAX_ROTATIONS   = 8;
    
/** The margin of error for reaching the forklift goal */
    public static final double MOTOR_ACCURACY           = .05;  //Figure out max
    
/** The speed at which the position changes for manual control */
    public static final double MANUAL_LIFT_SPEED        = .01; // Value between 0 and 1

    public static final int PORT_MOTOR_REAR_LEFT   = 1;
    public static final int PORT_MOTOR_REAR_RIGHT  = 2;
    public static final int PORT_MOTOR_FRONT_LEFT  = 3;
    public static final int PORT_MOTOR_FRONT_RIGHT = 4;
    
    
    
    /* PID Values */
    /* http://en.wikipedia.org/wiki/PID_controller#Ziegler.E2.80.93Nichols_method */
    public static int    u = 1100;
    public static double p = .6 * u;
    public static double i = 2 * p / u;
    public static double d = p * u / 8;
    
    /* Physical Measurements */
    public static double CORNER_TO_PIVOT = 30.75;
    public static double ARM_LENGTH = 38;
}

