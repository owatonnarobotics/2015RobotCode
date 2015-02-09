package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;
import org.usfirst.frc.team4624.robot.commands.LiftManual;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Forklift extends Subsystem {
	
	public enum Mode{
		MANUAL, LEVEL
	}
    
    Jaguar lift;
    
    Encoder encoder;
    
    // Goal used for position mode
    private double goal; // In Rotations
    
    private double rateOfChange;
    private double lastDistance;
    private long lastTime;
    
    private Mode mode;
    
    public Forklift() {
        lift = new Jaguar(RobotMap.PWM_LIFT_PORT);
        encoder = new Encoder(RobotMap.LIFT_ENCODER_A, RobotMap.LIFT_ENCODER_B);
        
        goal = 0.0;
        
        lastDistance = 0.0;
        lastTime = System.currentTimeMillis();
        rateOfChange = 0.0;
        
    }
    
    // General Commands
    public void setRaw(double raw){
        lift.set(raw);
    }
    
    public void setRate(double rate){
        
    }
    
    private double getRateOfChange(){
        return rateOfChange;
    }
    
    private double getRotations(){
        return encoder.getDistance() / 250;
    }
    // Manual Commands
    
    // Level Commands
    
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new LiftManual());
    }
    
    /**
     * Update the system. Call this from a default command.
     */
    public void update() {
    	rateOfChange = (encoder.getDistance() - lastDistance) / (System.currentTimeMillis() - lastTime);
    	// Updates to the new encoder and time, for another calculation of the ratio
        lastDistance = encoder.getDistance();
        lastTime = System.currentTimeMillis();
        
        displayInformation();
    }
    
    private void displayInformation(){
        SmartDashboard.putNumber("Encoder Position", encoder.getDistance() / 250);
        SmartDashboard.putNumber("Rate of Change", (encoder.getDistance() - lastDistance) / (System.currentTimeMillis() - lastTime));
    }
}
