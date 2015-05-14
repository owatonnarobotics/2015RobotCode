package org.usfirst.frc.team4624.robot.subsystems;



import org.usfirst.frc.team4624.robot.RobotMap;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;



public class GyroSensor extends Subsystem {
    
    
    
    Gyro gyroSensor;
    
    
    
    public GyroSensor() {
    
        gyroSensor = new Gyro(RobotMap.GYRO);
        gyroSensor.initGyro();
    }
    
    /**
     * Get the current angle of the gyro
     * 
     * @return the angle in degrees?
     */
    public double getAngle() {
    
        return gyroSensor.getAngle();
    }
    
    @Override
    protected void initDefaultCommand() {
    
    }
    
    /**
     * Reset the gyro angle to zero
     */
    public void reset() {
    
        gyroSensor.reset();
    }
    
}
