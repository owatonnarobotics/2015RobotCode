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
    
    // Goal used for manual mode
    private double rateGoal;
    
    // Goal used for level mode
    private int levelGoal;
    
    private double rateOfChange;
    private double lastDistance;
    private long lastTime;
    
    private Mode mode;
    
    public Forklift() {
        lift = new Jaguar(RobotMap.PWM_LIFT_PORT);
        encoder = new Encoder(RobotMap.LIFT_ENCODER_A, RobotMap.LIFT_ENCODER_B);
        
        lastDistance = 0.0;
        lastTime = System.currentTimeMillis();
        rateOfChange = 0.0;
        
        levelGoal = 0;
        rateGoal = 0;
        
        mode = Mode.MANUAL;
    }
 
    /** 
     * Manual Commands
    **/
    
    public void setManualMode(){
    	mode = Mode.MANUAL;
    	rateGoal = 0.0;
    }
    
    /** 
     * Level Commands
    **/
    
    public void setLevelMode(){
    	mode = Mode.LEVEL;
    	rateGoal = RobotMap.LEVEL_RATE;
    }
    
    public void increaseLevel(){
    	
    }
    
    public void decreaseLevel(){
    	
    }
    
    /** 
     * General Commands
    **/
    
    // Sets the raw value of the Jaguar motor
    public void setRaw(double raw){
        lift.set(raw);
    }
    
    // Sets the rate at which the Jaguar should be running
    public void setRate(double rate){
        rateGoal = rate;
    }
    
    private double getRateOfChange(){
        return rateOfChange;
    }
    
    private double getRotations(){
        return encoder.getDistance() / 250;
    }
    
    // Updates the system with the new rate of change
    public void update() {
    	rateOfChange = (encoder.getDistance() - lastDistance) / (System.currentTimeMillis() - lastTime);
    	// Updates to the new encoder and time, for another calculation of the ratio
        lastDistance = encoder.getDistance();
        lastTime = System.currentTimeMillis();
        
        if (mode == Mode.LEVEL) {
        	// Update level info
        }
        
        displayInformation();
    }
    
    private void displayInformation(){
        SmartDashboard.putNumber("Encoder Position", encoder.getDistance() / 250);
        SmartDashboard.putNumber("Rate of Change", (encoder.getDistance() - lastDistance) / (System.currentTimeMillis() - lastTime));
    }
    
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new LiftManual());
    }
}
