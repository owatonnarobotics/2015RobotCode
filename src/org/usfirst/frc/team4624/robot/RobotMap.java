package org.usfirst.frc.team4624.robot;

public class RobotMap {
    
    /* PWM Ports */
    public static final int PORT_MOTOR_LEFT     = 1;
    public static final int PORT_MOTOR_RIGHT    = 0;
    
    /* RoboRio Ports */
    public static final int PORT_ENCODER_JAGUAR = 16;
    public static final int PORT_ENCODER_A      = 0; // Find correct port ???
    public static final int PORT_ENCODER_B      = 0; // Find correct port ???
    public static final int PORT_COMPRESSOR     = 1;
    public static final int PCM_CAN_ID          = 1;
    public static final int PCM_SOLENOID_PORT   = 0;
    public static final int PORT_FORKLIFT       = 14;
    
    /* Global */
    public static final double FORKLIFT_MAX_ROTATIONS = 5;
    public static final double MOTOR_ACCURACY         = .05;  //Figure out max
    public static final double MANUAL_LIFT_SPEED      = .01; // Value between 0 and 1
    
    /* PID Values */
    /* http://en.wikipedia.org/wiki/PID_controller#Ziegler.E2.80.93Nichols_method */
    public static int    u = 1100;
    public static double p = .6 * u;
    public static double i = 2 * p / u;
    public static double d = p * u / 8;
}

