package org.usfirst.frc.team4624.robot.subsystems;



import org.usfirst.frc.team4624.robot.RobotMap;
import org.usfirst.frc.team4624.robot.Tools;
import org.usfirst.frc.team4624.robot.commands.LiftManual;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class Forklift extends Subsystem {
    
    public enum Mode {
        MANUAL, LEVEL
    }
    
    private Jaguar       lift;
    private Encoder      encoder;
    private DigitalInput encoderSwitch;
    
    private double       rateGoal;
    public  int          levelGoal;
    
    private double       rateOfChange;
    private double       lastDistance;
    private long         lastTime;
    
    private Mode         mode;
    private double[]     levels;
    
    private boolean      override;
    
    /**
     * Initialize the Forklift subsystem. Should only be called once.
     */
    public Forklift() {
    
        this.lift = new Jaguar(RobotMap.PWM_LIFT_PORT);
        this.encoder = new Encoder(RobotMap.LIFT_ENCODER_A, RobotMap.LIFT_ENCODER_B);
        this.encoderSwitch = new DigitalInput(RobotMap.PORT_ENCODER_RESET);
        
        this.lastDistance = 0.0;
        this.lastTime = System.currentTimeMillis();
        this.rateOfChange = 0.0;
        
        this.levelGoal = 0;
        this.rateGoal = 0;
        
        this.mode = Mode.MANUAL;
        this.levels = RobotMap.LIFT_HEIGHTS_GROUND;
        
        this.override = false;
    }
    
    /**
     * Manual Commands
     **/
    public void setManualMode() {
    
        mode = Mode.MANUAL;
    }
    
    /**
     * Level Commands
     **/
    public void setLevelMode() {
    
        mode = Mode.LEVEL;
        rateGoal = RobotMap.LEVEL_RATE;
    }
    
    /**
     * Increase the level of the forklift by 1
     */
    public void increaseLevel() { // Check for level going too high
    
        levelGoal += 1;
        levelGoal = Tools.clamp(levelGoal, 0, levels.length - 1);
    }
    
    /**
     * Decrease the level of the forklift by 1
     */
    public void decreaseLevel() {
    
        levelGoal -= 1;
        levelGoal = Tools.clamp(levelGoal, 0, levels.length - 1);
    }
    
    public void switchLevelArray() {
    
        if (levels.equals(RobotMap.LIFT_HEIGHTS_GROUND)) {
            levels = RobotMap.LIFT_HEIGHTS_FEEDER;
        } else {
            levels = RobotMap.LIFT_HEIGHTS_GROUND;
        }
    }
    
    public String getLevelArray() {
    
        if (levels.equals(RobotMap.LIFT_HEIGHTS_GROUND)) {
            return "Ground";
        }
        if (levels.equals(RobotMap.LIFT_HEIGHTS_FEEDER)) {
            return "Feeder";
        }
        return "Something's wrong here...";
    }
    
    public void toggleOverride() {
    
        override = !override;
    }
    
    /**
     * Sets the raw value of the Jaguar motor
     * 
     * @param raw
     */
    public void setRaw(double raw) {
    
        lift.set(Tools.clamp(raw, -1, 1));
    }
    
    /**
     * Gets the raw value of the Jaguar motor
     * 
     * @return
     */
    private double getRaw() {
    
        return lift.get();
    }
    
    /**
     * Sets the rate at which the Jaguar should be running, with min and max
     * height safety checks
     * 
     * @param rate
     */
    public void setRate(double rate) {
    
        rateGoal = rate;
        if (getRotations() == 0 && !override) {
            rateGoal = Tools.clamp(rateGoal, 0, 1);
        }
        if (getRotations() > RobotMap.FORKLIFT_MAX_ROTATIONS && rateGoal > 0) {
            rateGoal = 0;
        }
        if (getRotations() < RobotMap.FORKLIFT_SLOW_ZONE) {
            rateGoal = Tools.clamp(rateGoal, RobotMap.FORKLIFT_SLOW_ZONE_RATE, 1);
        }
        if (getRotations() > RobotMap.FORKLIFT_MAX_ROTATIONS - RobotMap.FORKLIFT_SLOW_ZONE) {
            rateGoal = Tools.clamp(rateGoal, -1, -RobotMap.FORKLIFT_SLOW_ZONE_RATE);
        }        
        if(!encoderSwitch.get()){
            rateGoal = Tools.clamp(rateGoal, 0, 1);
        }
    }
    
    /**
     * Get the rotations of the lift motor
     * 
     * @return Double of rotations
     */
    private double getRotations() {
    
        return correctDistance() / 250;
    }
    
    /**
     * Returns encoder's position, with errors corrected
     * 
     * @return encoder distance
     */
    public double correctDistance() {
    
        return encoder.getDistance();
    }
    
    /**
     * Updates the system with the new rate of change. Called every interval
     */
    public void update() {
    
        if (!encoderSwitch.get()) {
            encoder.reset();
        }
        updateRateOfChange();
        if (mode == Mode.LEVEL) {
            updateLevel();
        }
        updateRate();
        displayInformation();
    }
    
    /**
     * Sets the PWM based on the rate goal. Called every interval
     */
    private void updateRate() {
    
        double rateDifference = Math.abs(rateOfChange - rateGoal);
        SmartDashboard.putNumber("Rate Difference", rateDifference);
        // If the rate is not within the wanted rate
        if (rateDifference > RobotMap.RATE_MARGIN_OF_ERROR) {
            // setRaw(getRaw() + (rateGoal - rateOfChange) / 5);
            if (rateOfChange > rateGoal) {
                System.out.println("Updating: " + getRaw());
                setRaw(getRaw() + RobotMap.RATE_CHANGE);
                if(getRotations() == 0.0 && getRaw() < .2) {
                    setRaw(.2);
                }
            } else if (rateOfChange < rateGoal) {
                System.out.println("Updating Negative: " + getRaw());
                setRaw(getRaw() - RobotMap.RATE_CHANGE);
                if(getRotations() == 0.0 && getRaw() > -.2) {
                    setRaw(-.2);
                }
            }
        }
        if (getRotations() == 0 && rateGoal == 0) {
            setRaw(0);
        }
    }
    
    /**
     * Updates current and last time and position, and calculates rate of change
     */
    private void updateRateOfChange() {
    
        long currentTime = System.currentTimeMillis();
        long timeChanged = currentTime - lastTime;
        double encoderPosition = correctDistance();
        SmartDashboard.putNumber("Time between intervals", timeChanged);
        if (Math.abs(timeChanged) <= .05) {
            rateOfChange = 0;
        } else {
            rateOfChange = (encoderPosition - lastDistance) / (timeChanged);
        }
        lastDistance = encoderPosition;
        lastTime = currentTime;
    }
    
    /**
     * Sets the rate to get to the next level
     */
    private void updateLevel() {
    
        // If the height is close enough to the goal, disable the rate of the motor
        double levelGoalDifference = Math.abs(levels[levelGoal] - Math.abs(getRotations()));
        if (levelGoalDifference < RobotMap.RATE_MARGIN_OF_ERROR) {
            setRate(0);
        }
        // If the height is above the goal, set the rate to be negative
        else if (levels[levelGoal] < Math.abs(getRotations())) {
            setRate(Tools.clamp(-1 * RobotMap.LEVEL_RATE * levelGoalDifference, -1, 0));
        }
        // If the height is below the goal, set the rate to be positive
        else {
            setRate(Tools.clamp(RobotMap.LEVEL_RATE * levelGoalDifference, 0, 1));
        }
    }
    
    /**
     * Update the the smart dashboard
     */
    private void displayInformation() {
    
        SmartDashboard.putNumber("Encoder Position", correctDistance() / 250);
        //SmartDashboard.putNumber("Rate of Change", rateOfChange);
        SmartDashboard.putNumber("Level Goal", levelGoal);
        //SmartDashboard.putNumber("Rate Goal", rateGoal);
        SmartDashboard.putString("Goal Mode", mode.toString());
        SmartDashboard.putString("Level Mode", getLevelArray());
        SmartDashboard.putBoolean("Override", override);
    }
    
    @Override
    protected void initDefaultCommand() {
    
        setDefaultCommand(new LiftManual());
    }
    
    public void liftToHeight(int level) {
        
        mode = Mode.LEVEL;
        levels = RobotMap.LIFT_HEIGHTS_AUTO;
        levelGoal = level;
    }
}
