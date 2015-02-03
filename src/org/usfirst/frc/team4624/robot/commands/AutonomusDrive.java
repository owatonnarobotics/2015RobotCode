package org.usfirst.frc.team4624.robot.commands;

import org.usfirst.frc.team4624.robot.Robot;
import org.usfirst.frc.team4624.robot.subsystems.Forklift;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomusDrive extends Command {
    
    public AutonomusDrive() {
        //requires(Robot.planetary);
    }
    @Override
    protected void initialize() {
        //Robot.planetary.stop();
    }
    
    @Override
    protected void execute() {
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
    
    @Override
    protected void end() {
    }
    
    @Override
    protected void interrupted() {
    }
}
