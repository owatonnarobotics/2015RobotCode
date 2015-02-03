
package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;	// ENUMS for the ports
import org.usfirst.frc.team4624.robot.commands.DriveCommand;
import org.usfirst.frc.team4624.robot.input.XboxController;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Powertrain extends Subsystem {
    
    /* Instance Values */
    RobotDrive motors;
    
    /**
     * Constructor
     * This initializes the powertrain
     */
    public Powertrain() {
        /* Initialize */
        motors = new RobotDrive(RobotMap.PORT_MOTOR_LEFT, RobotMap.PORT_MOTOR_RIGHT);
        stop();
    }
    
    double inputFunction(double input) { //TODO Rename this function and paramaters
        double x =  Math.abs(input);
        double y = -Math.sqrt(1 - Math.pow(x, 2)) + 1;
        if (input > 0) {
            return y;
        } else {
            return -y;
        }
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveCommand());
    }
    
    public void setRaw(double l, double r) {  // Avoid using this. Use set instead
        double left  = Math.max(-1, Math.min(1, l)); // Clamp
        double right = Math.max(-1, Math.min(1, r)); // Clamp
        motors.tankDrive(left, right);
    }
    
    public void set(double l, double r) {
        setRaw(l, -r); // To go straight, we inverted one of the motors (Clockwise && Counter-Clockwise = Straight)
    }
    
    public void setAsTankdrive(XboxController.Thumbstick stick) {
        // Formula taken from here: http://home.kendra.com/mauser/Joystick.html
        final double x     = -stick.getRawX();
        final double y     =  stick.getRawY();
        
        final double v     = (1 - Math.abs(x)) * y + y;
        final double w     = (1 - Math.abs(y)) * x + x;
        
        final double left  = (v-w) / 2;
        final double right = (v+w) / 2;
        
        set(inputFunction(left), inputFunction(right));
    }
    
    public void stop() {
        motors.stopMotor();
    }
}
