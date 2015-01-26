
package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;	// ENUMS for the ports
import org.usfirst.frc.team4624.robot.commands.DriveCommand;
import org.usfirst.frc.team4624.robot.input.XboxController;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Powertrain extends Subsystem {
    
    /* Instance Values */
    Jaguar left;
    Jaguar right;
    
    
    
    /**
     * Constructor
     * 
     * This initializes the powertrain
     */
    public Powertrain() {
        
        /* Initialize */
        this.init();
    }
    
    
    
    double inputFunction( double input ) {
        double x        = Math.abs( input );
        double thing    = -Math.sqrt( 1 - Math.pow( x, 2 ) ) + 1;
        if ( input > 0 ) {
            return thing;
        } else {
            return -thing;
        }
    }
    
    
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand( new DriveCommand() );
    }
    
    
    public void init() {
        left    = new Jaguar( RobotMap.PORT_MOTOR_LEFT );
        right   = new Jaguar( RobotMap.PORT_MOTOR_RIGHT );
        
        left.setSafetyEnabled( true );
        right.setSafetyEnabled( true );
        left.setExpiration( 0.5 );
        right.setExpiration( 0.5 );
        this.stop();
    }
    
    public void setRaw( double l, double r ) {  // Avoid using
        left.set( l );
        right.set( r );
    }
    
    public void set( double l, double r ) {
        this.setRaw( l, -r );	// To go straight, we inverted one of the motors (Clockwise && Counter-Clockwise = Straight)
    }
    
    public void setFromThumbstick( XboxController.Thumbstick stick ) {
        
        double x = stick.getX();
        double y = stick.getY();
        
        double left		= x + y;
        double right	= -x + y;
        
        left = left > 1 ? 1 : left;
        left = left < -1 ? -1 : left;
        right = right > 1 ? 1 : right;
        right = right < -1 ? -1 : right;
        
        left = inputFunction( left );
        right = inputFunction( right );
        
        this.set( left, right );
    }
    
    public void stop() {
        left.set( 0 );
        right.set( 0 );
    }
}