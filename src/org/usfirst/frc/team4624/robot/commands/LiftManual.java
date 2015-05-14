package org.usfirst.frc.team4624.robot.commands;



import org.usfirst.frc.team4624.robot.OI;
import org.usfirst.frc.team4624.robot.Robot;
import org.usfirst.frc.team4624.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;



public class LiftManual extends Command {
    
    
    
    public LiftManual() {
    
        requires(Robot.forklift);
    }
    
    @Override
    protected void end() {
    
    }
    
    @Override
    protected void execute() {
    
        Robot.forklift
                .setRate((OI.xboxController.rt.getX() - OI.xboxController.lt
                        .getX()) * RobotMap.MANUAL_LIFT_SPEED);
    }
    
    @Override
    protected void initialize() {
    
        if (OI.xboxController.lt.getX() != 0
                || OI.xboxController.rt.getX() != 0) {
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
