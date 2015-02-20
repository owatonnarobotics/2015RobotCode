package org.usfirst.frc.team4624.robot.autonomous;



import org.usfirst.frc.team4624.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;



public class AutoDrive extends Command {
    
    
    
    private double forward;
    private double right;
    
    
    
    /**
     * Cause the robot to move in autonomous mode
     * 
     * @param time How long the robot will move
     * @param forward The forward power (negative is backwards)
     */
    public AutoDrive(double time, double forward) {
    
        requires(Robot.powertrain);
        this.setTimeout(time);
        this.forward = forward;
        this.right = 0;
    }
    
    /**
     * Cause the robot to move in autonomous mode
     * 
     * @param time How long the robot will move
     * @param forward The forward power (negative is backwards)
     * @param right The right strafing power (negative is left)
     */
    public AutoDrive(double time, double forward, double right) {
    
        this(time, forward);
        this.right = right;
    }
    
    @Override
    protected void end() {
    
    }
    
    @Override
    protected void execute() {
    
        Robot.powertrain.move(right, forward, 0);
    }
    
    @Override
    protected void initialize() {
    
    }
    
    @Override
    protected void interrupted() {
    
    }
    
    @Override
    protected boolean isFinished() {
    
        return this.isTimedOut();
    }
}