package org.usfirst.frc.team4624.robot.commands;

import org.usfirst.frc.team4624.robot.Robot;
import org.usfirst.frc.team4624.robot.input.XboxController;
import org.usfirst.frc.team4624.robot.subsystems.Planetary;

import edu.wpi.first.wpilibj.command.Command;

public class MovePlanetary extends Command {
    
    Planetary planetary;
    XboxController xboxController;
    
    public MovePlanetary() {
        requires( Robot.planetary );
        xboxController = Robot.oi.xboxController;
    }
    
    @Override
    protected void initialize() {
        Robot.planetary.stop();
    }
    
    @Override
    protected void execute() {
        //Robot.planetary.toggle();
        Robot.planetary.setRaw( xboxController.rt.getX() );
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
    
    @Override
    protected void end() {
        Robot.planetary.stop();
    }
    
    @Override
    protected void interrupted() {
        
    }
}