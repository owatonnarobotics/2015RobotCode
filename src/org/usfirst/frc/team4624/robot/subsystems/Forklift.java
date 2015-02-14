package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;
import org.usfirst.frc.team4624.robot.commands.LiftManual;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Forklift extends Subsystem {
	
    public enum Mode{
        MANUAL, LEVEL
    }
    
    private Jaguar  lift;
    private Encoder encoder;
    
    // Goal used for manual mode
    private double  rateGoal;
    
    // Goal used for level mode
    private int     levelGoal;
    
    private double  rateOfChange;
    private double  lastDistance;
    private long    lastTime;
    
    private Mode mode;
    
    /**
     * Initialize the Forklift subsystem. Sould only be called once.
     */
    public Forklift() {
        this.lift           = new Jaguar(RobotMap.PWM_LIFT_PORT);
        this.encoder        = new Encoder(RobotMap.LIFT_ENCODER_A, RobotMap.LIFT_ENCODER_B);
        
        this.lastDistance   = 0.0;
        this.lastTime       = System.currentTimeMillis();
        this.rateOfChange   = 0.0;
        
        this.levelGoal      = 0;
        this.rateGoal       = 0;
        
        this.mode           = Mode.MANUAL;
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
    	mode       = Mode.LEVEL;
    	rateGoal   = RobotMap.LEVEL_RATE;
    }
    
    /**
     * Increase the level of the forklift by 1
     */
    public void increaseLevel(){  //Check for level going too high
    	levelGoal += 1;
    	levelGoal = clamp(levelGoal, 0, RobotMap.LIFT_HEIGHTS.length - 1);
    }
    
    /**
     * Decrease the level of the forklift by 1
     */
    public void decreaseLevel(){
    	levelGoal -= 1;
    	levelGoal = clamp(levelGoal, 0, RobotMap.LIFT_HEIGHTS.length - 1);
    }
    
    /**
     * Clamp a value between two other values
     * @param value
     * @param min
     * @param max
     * @return The clamped value
     */
    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
    
    /**
     * Clamp a value between two other values
     * @param value
     * @param min
     * @param max
     * @return The clamped value
     */
    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
    
    /** 
     * General Commands
    **/
    
    /**
     * Sets the raw value of the Jaguar motor
     * @param raw
     */
    private void setRaw(double raw){
        lift.set(clamp(raw,-1,1));
    }
    
    /**
     * Gets the raw value of the Jaguar motor
     * @return
     */
    private double getRaw(){
    	return lift.get();
    }
    
    /**
     * Sets the rate at which the Jaguar should be running
     * @param rate
     */
    public void setRate(double rate){
        rateGoal = rate;
    }
    
    /**
     * Get the rotations of the lift motor
     * @return Double of rotations
     */
    private double getRotations(){
        return encoder.getDistance() / 250;
    }
    
    /**
     * Increases or decreases the PWM based on the rate goal.
     * Called every interval
     */
    private void updateRate(){
        
        double rateDifference = Math.abs(rateOfChange - rateGoal);
        
        // If the rate is not within the wanted rate
    	if (rateDifference > RobotMap.RATE_MARGIN_OF_ERROR) {
    		setRaw(getRaw() + (rateGoal - rateOfChange) / 5);
    	}
    }
    
    private void updateRateOfChange() {
        long currentTime = System.currentTimeMillis();
        long timeChanged = currentTime - lastTime;
        SmartDashboard.putNumber("Time between intervals", timeChanged);
        if(Math.abs(timeChanged) <= .05) {
            rateOfChange = 0;
        }
        else {
            rateOfChange = (encoder.getDistance() - lastDistance) / (timeChanged);
        }
        lastDistance = encoder.getDistance();
        lastTime = currentTime;
    }
    
    /**
     * Updates the system with the new rate of change.
     * Called every interval
     */
    public void update() {
    	updateRateOfChange();
        
        if (mode == Mode.LEVEL) {
        	// If the height is close enough to the goal, disable the rate of the motor
            //double levelGoal 
            double levelGoalDifference = Math.abs(RobotMap.LIFT_HEIGHTS[levelGoal] - getRotations());
            
        	if (levelGoalDifference < RobotMap.RATE_MARGIN_OF_ERROR){
        		setRate(0);
        	}
        	// If the height is above the goal, set the rate to be negative
        	else if (RobotMap.LIFT_HEIGHTS[levelGoal] < getRotations()) {
        		setRate(-1 * RobotMap.LEVEL_RATE);
        	}
        	// If the height is below the goal, set the rate to be positive
        	else {
        		setRate(RobotMap.LEVEL_RATE);
        	}
        }
        
        updateRate();
        
        displayInformation();
    }
    
    /**
     * Update the the smart dashboard with
     */
    private void displayInformation(){
        SmartDashboard.putNumber("Encoder Position", encoder.getDistance() / 250);
        SmartDashboard.putNumber("Rate of Change"  , rateOfChange);
        SmartDashboard.putNumber("Level Goal"      , levelGoal);
        SmartDashboard.putNumber("Rate Goal"       , rateGoal);
        SmartDashboard.putString("Goal Mode"       , mode.toString());
    }
    
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new LiftManual());
    }
}
