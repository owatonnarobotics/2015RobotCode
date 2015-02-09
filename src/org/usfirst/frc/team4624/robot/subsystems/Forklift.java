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
    }
    
    /** 
     * Level Commands
    **/
    
    public void setLevelMode(){
    	mode = Mode.LEVEL;
    	rateGoal = RobotMap.LEVEL_RATE;
    }
    
    public void increaseLevel(){
    	levelGoal += 1;
    }
    
    public void decreaseLevel(){
    	levelGoal -= 1;
    }
    
    /** 
     * General Commands
    **/
    
    // Sets the raw value of the Jaguar motor
    private void setRaw(double raw){
        lift.set(raw);
    }
    
    private double getRaw(){
    	return lift.get();
    }
    
    // Sets the rate at which the Jaguar should be running
    public void setRate(double rate){
        rateGoal = rate;
    }
    
    private double getRotations(){
        return encoder.getDistance() / 250;
    }
    
    // Increases or decreases the PWM based on the rate goal
    private void updateRate(){
    	if (rateGoal == 0.0){
    		setRaw(0.0);
    	}
    	// If the rate is not within the wanted rate
    	else if (! (Math.abs(rateOfChange - rateGoal) < RobotMap.RATE_MARGIN_OF_ERROR)){
    		// If the rate is not large enough, increase the raw value
    		if (rateOfChange < rateGoal){
    			setRaw(getRaw() + RobotMap.RATE_CHANGE);
    		}
    		// If the rate is too large, decrease the raw value
    		if (rateOfChange > rateGoal){
    			setRaw(getRaw() - RobotMap.RATE_CHANGE);
    		}
    	}
    	
    }
    
    // Updates the system with the new rate of change
    public void update() {
    	rateOfChange = (encoder.getDistance() - lastDistance) / (System.currentTimeMillis() - lastTime);
    	// Updates to the new encoder and time, for another calculation of the ratio
        lastDistance = encoder.getDistance();
        lastTime = System.currentTimeMillis();
        
        if (mode == Mode.LEVEL) {
        	// If the height is close enough to the goal, disable the rate of the motor
        	if (Math.abs(RobotMap.LIFT_HEIGHTS[levelGoal] - getRotations()) < RobotMap.LIFT_MARGIN_OF_ERROR){
        		setRate(0);
        	}
        	// If the height is below the goal, set the rate to be negative
        	else if (RobotMap.LIFT_HEIGHTS[levelGoal] < getRotations()) {
        		setRate(-1 * RobotMap.LEVEL_RATE);
        	}
        	// If the height is above the goal, set the rate to be positive
        	else {
        		setRate(RobotMap.LEVEL_RATE);
        	}
        }
        
        updateRate();
        
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
