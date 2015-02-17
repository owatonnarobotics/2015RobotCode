package org.usfirst.frc.team4624.robot.commands;

import org.usfirst.frc.team4624.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class ToggleOverride extends Command {

    public ToggleOverride() {
        requires(Robot.forklift);
    }
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.forklift.toggleOverride();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    
        // TODO Auto-generated method stub
        
    }
    
}
