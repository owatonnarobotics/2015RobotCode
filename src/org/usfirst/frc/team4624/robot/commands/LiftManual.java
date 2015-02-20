package org.usfirst.frc.team4624.robot.commands;



import org.usfirst.frc.team4624.robot.OI;
import org.usfirst.frc.team4624.robot.Robot;
import org.usfirst.frc.team4624.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;



public class LiftManual extends Command {
    
    
    
    /**
     * Takes input in from the triggers on the controllers to move the arms
     */
    public LiftManual() {
    
        requires(Robot.forklift);
    }
    
    @Override
    protected void end() {
    
    }
    
    @Override
    protected void execute() {
    
        final double leftTrigger = OI.xboxController.lt.getX();
        final double rightTrigger = OI.xboxController.rt.getX();
        
        final double direction = rightTrigger - leftTrigger;
        
        Robot.forklift.setRate((direction) * RobotMap.MANUAL_LIFT_SPEED);
    }
    
    @Override
    protected void initialize() {
    
        final boolean leftTriggerPress = OI.xboxController.lt.getX() != 0;
        final boolean rightTriggerPress = OI.xboxController.lt.getX() != 0;
        
        if (leftTriggerPress || rightTriggerPress) {
            Robot.forklift.setManualMode();
        }
    }
    
    @Override
    protected void interrupted() {
    
        end();
    }
    
    @Override
    protected boolean isFinished() {
    
        return true;
    }
}