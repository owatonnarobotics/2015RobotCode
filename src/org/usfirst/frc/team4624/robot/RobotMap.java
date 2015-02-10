package org.usfirst.frc.team4624.robot;

public class RobotMap {
    
    public static final int PWM_LIFT_PORT = 0;
    
    public static final int LIFT_ENCODER_A = 0;
    public static final int LIFT_ENCODER_B = 1;
    
    /* During level mode the margin of error allowed to the goal */
    public static final double LIFT_MARGIN_OF_ERROR = 0.1;
    
    /* Array of all the heights for the Forklift */
    public static final double[] LIFT_HEIGHTS = {
        1.0, 2.0, 3.0, 4.0
    };
    
    /* The margin of error for updating the rate of the motor */
    public static final double RATE_MARGIN_OF_ERROR = 0.05;
    
    /* The rate at which the motor changes the PWM level */
    public static final double RATE_CHANGE = 0.01;
    
    /* The rate of the forklift in level mode */
    public static final double LEVEL_RATE = 0.5;
    
    /* PWM Ports */
/** The PWM port for the left motor in tankdrive mode. */
    public static final int PORT_MOTOR_LEFT             = 1;
    
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
    public static final double MANUAL_LIFT_SPEED        = .5; // Value between 0 and 1

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
}

