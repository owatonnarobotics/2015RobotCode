
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
        Jaguar leftMotor = new Jaguar(RobotMap.PORT_MOTOR_LEFT);
        Jaguar rightMotor = new Jaguar(RobotMap.PORT_MOTOR_RIGHT);
        
        motors = new RobotDrive(leftMotor, rightMotor);
        //motors.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        motors.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        
        stop();
    }
    
    double inputFunction(final double input) { //TODO Rename this function and paramaters
        final double abs    =  Math.abs(input);
        final double output = -Math.sqrt(1 - Math.pow(abs, 2)) + 1;
        if (input > 0) {
            return output;
        } else {
            return -output;
        }
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveCommand());
    }
    
    public void setAsTankdrive(XboxController.Thumbstick stick) {
        final double boostScale = .5;   // Smaller makes non boost slower. Boost is always full speed.
        
        double x = inputFunction(stick.getRawX()) * (stick.get() ? 1 : boostScale);
        double y = inputFunction(stick.getRawY()) * (stick.get() ? 1 : boostScale);
        
        
        motors.arcadeDrive(x, y);
    }
    
    public void stop() {
        motors.stopMotor();
    }
    
    public boolean isFinished() {
        return false;
    }
}
