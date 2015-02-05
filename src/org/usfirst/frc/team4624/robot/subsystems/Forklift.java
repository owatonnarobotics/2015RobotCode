package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;
import org.usfirst.frc.team4624.robot.commands.LiftManual;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Forklift extends Subsystem {

    final CANJaguar liftMotor;
    
    private final int codesPerRev = 497;
    private double goal; // In Rotations
    
    /**
     * Initializes Forklift subsystem. Should only be called once.
     */
    public Forklift() {
        liftMotor = new CANJaguar(RobotMap.PORT_FORKLIFT);
        liftMotor.setPositionMode(CANJaguar.kQuadEncoder, codesPerRev,
                RobotMap.p, RobotMap.i, RobotMap.d);
        liftMotor.enableControl(0);
        goal = liftMotor.getPosition();
    }
    
    /**
     * Sets the target of the Forklift to its current position. Effectively stopping it.
     */
    public void stop() {
        goal = liftMotor.getPosition();
        liftMotor.set(goal);
    }
    
    /**
     * Snaps the Forklift to a tote level relative to its current position
     * @param changeLevel
     */
    public void changeLevel(int changeLevel) { // Convert to rotations here (changeLevel is in levels)
        // TODO Auto-generated method stub
    }
    
    /**
     * Changes the Forklift height relative to its current position
     * @param changeHeight
     */
    public void changeHeight(double changeHeight) { // Convert to rotations here (changeHeight is in distance)
        // TODO Auto-generated method stub
    }
    
    /**
     * Changes the target of the motor relative to what it is
     * @param changeGoal
     */
    private void changeGoal(double changeGoal) { // Takes in Rotations
        goal += changeGoal;
        goal = clamp(goal, 0, RobotMap.FORKLIFT_MAX_ROTATIONS);
        liftMotor.set(goal);
    }
    
    /**
     * Clamps a value between two other values
     * @param input
     * @param min
     * @param max
     * @return A clamped value between min and max
     */
    private double clamp(double input, double min, double max) {
        return Math.max(min, Math.min(max, input));
    }
    
    /**
     * Converts vertical distance to rotations required to reach that height
     * @param distance
     * @return Number of rotations from the initial value (0)
     */
    private double distanceToRotations(double distance) { //This is the important one
        return 0.0; //TODO Add Maths
    }
    
    /**
     * For use with commands
     * @return Returns true if the Forklift has reached its goal
     */
    public boolean isFinished() {
        return (Math.abs(liftMotor.getPosition() - goal) < RobotMap.MOTOR_ACCURACY);
    }
    
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new LiftManual());
    }
    
    /* Smart Dashboard */
    /**
     * For use with the Smart Dashboard
     * @return The current rotation count of the motor
     */
    public double getPosition() {
        return liftMotor.getPosition();
    }
    
    /**
     * For use with the Smart Dashboard
     * @return The current goal of the motor
     */
    public double getGoal() {
        return goal;
    }
    
    /**
     * For use with the Smart Dashboard.
     * Sets the goal to what is inputed
     * @param newGoal
     */
    public void setGoal(double newGoal) {
        goal = newGoal;
        liftMotor.set(goal);
    }
    
    /**
     * For use with the Smart Dashboard.
     * Sets the P I and D values of the Forklift motor
     */
    public void reinit() {
        liftMotor.setPositionMode(CANJaguar.kQuadEncoder, codesPerRev,
                RobotMap.p, RobotMap.i, RobotMap.d);
    }
}
