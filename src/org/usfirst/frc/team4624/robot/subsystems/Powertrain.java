
package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.OI;
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
     * Initializes Powertrain subsystem. Should only be called once.
     */
    public Powertrain() {
        /* Initialize */
        Jaguar rearleftMotor    = new Jaguar(RobotMap.PORT_MOTOR_REAR_LEFT);
        Jaguar rearRightMotor   = new Jaguar(RobotMap.PORT_MOTOR_REAR_RIGHT);
        Jaguar frontLeftMotor   = new Jaguar(RobotMap.PORT_MOTOR_FRONT_LEFT);
        Jaguar frontRightMotor  = new Jaguar(RobotMap.PORT_MOTOR_FRONT_RIGHT);
        
        motors = new RobotDrive(frontLeftMotor, rearleftMotor, frontRightMotor, rearRightMotor);
        
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
    
    public void setAsTankdrive(XboxController controller) {
        final double boostScale = .5;   // Smaller makes non boost slower. Boost is always full speed.
        
        double x = inputFunction(controller.leftStick.getX()) * (controller.leftStick.get() ? 1 : boostScale);
        double y = inputFunction(controller.leftStick.getY()) * (controller.leftStick.get() ? 1 : boostScale);
        
        motors.mecanumDrive_Cartesian(  x,
                                        -y,    // We already corrected for the mistake that this method also corrects
                                        controller.rightStick.getX(),
                                        0);
    }
    
    public void stop() {
        motors.stopMotor();
    }
    
    public boolean isFinished() {
        return false;
    }
}
