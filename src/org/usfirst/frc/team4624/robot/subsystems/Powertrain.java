
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
    GyroSensor gyro;
    
    /**
     * Initializes Powertrain subsystem. Should only be called once.
     */
    public Powertrain() {
        /* Initialize */
        Jaguar rearleftMotor    = new Jaguar(RobotMap.PWM_MOTOR_REAR_LEFT_PORT);
        Jaguar rearRightMotor   = new Jaguar(RobotMap.PWM_MOTOR_REAR_RIGHT_PORT);
        Jaguar frontLeftMotor   = new Jaguar(RobotMap.PWM_MOTOR_FRONT_LEFT_PORT);
        Jaguar frontRightMotor  = new Jaguar(RobotMap.PWM_MOTOR_FRONT_RIGHT_PORT);
        
        motors = new RobotDrive(frontLeftMotor, rearleftMotor, frontRightMotor, rearRightMotor);
        stop();
        
        gyro = new GyroSensor();
    }
    
    double inputFunction(final double input) {
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
        
        double x    = inputFunction(controller.leftStick.getX()) * (controller.leftStick.get() ? 1 : boostScale);
        double y    = inputFunction(controller.leftStick.getY()) * (controller.leftStick.get() ? 1 : boostScale);
        double turn = inputFunction(controller.rightStick.getX()) * RobotMap.ROTATE_SPEED;
        
        motors.mecanumDrive_Cartesian(  x,
                                        -y,    // We already corrected for the mistake that this method also corrects
                                        turn,
                                        0);
    }
    
    public void stop() {
        motors.stopMotor();
    }
    
    public boolean isFinished() {
        return false;
    }

    public void move(double x, double y, double rotation) {
        motors.mecanumDrive_Cartesian(x, y, rotation, 0);
    }
    
    
    /**
     * Calculates the difference between two angles. Max is 180.
     * @param angleA
     * @param angleB
     */
    private double angleDifference(double targetAngle, double currentAngle) {
        final double fullRotation   = 360;
        final double halfRotation   = fullRotation / 2;
        
        
        
        final double angleDifference = targetAngle - currentAngle;
        
        
        
        if (angleDifference > halfRotation) {
            return angleDifference - fullRotation;
        }
        if (angleDifference < -halfRotation) {
            return angleDifference + fullRotation;
        }
        
        return angleDifference;
    }
    
    /**
     * Rotate the robot to match the gyro angle
     * @param angle
     */
    private void setRawAngle(double angle) {
        final double constant = 360;
        double turnPower;
        
        turnPower = angleDifference(angle, gyro.getAngle()) / constant;
        
        turnPower = Math.pow( turnPower, 3);
        
        // Replace with clamp method
        if (turnPower > 1) {
            turnPower = 1;
        } else if (turnPower < -1) {
            turnPower = -1;
        }
        
        motors.mecanumDrive_Cartesian(0,0,turnPower,0);
    }
    
    boolean isTurning = false;
    double targetAngle = 0;
    
    /**
     * Turn the robot to this relative angle
     */
    public void setAngle(double angle) {
        angle %= angle;
        angle -= 180;
        targetAngle = angle;
        isTurning = true;
    }
    
    public void update() {
        if (isTurning) {
            // setRawAngle as function of T
            
            double angleDifference = angleDifference(targetAngle, gyro.getAngle() - 180);
            boolean reachedTarget = Math.abs(angleDifference) < 1;
            
            if (reachedTarget) {
                isTurning = false;
                gyro.reset();
            }
        }
    }
}
