package org.usfirst.frc.team4624.robot.commands;

import org.usfirst.frc.team4624.robot.OI;
import org.usfirst.frc.team4624.robot.Robot;
import org.usfirst.frc.team4624.robot.input.XboxController;
import org.usfirst.frc.team4624.robot.subsystems.Planetary;

import edu.wpi.first.wpilibj.command.Command;

public class MovePlanetary extends Command {
    
    XboxController xboxController;
    
    public MovePlanetary() {
        requires(Robot.planetary);
        xboxController = OI.xboxController;
    }
    
    @Override
    protected void initialize() {
        //Robot.planetary.stop();
        System.out.println("MovePlanetary Initialized");
        Robot.planetary.setTarget( 4 );
        System.out.println("Rotating 4 times...");
    }
    
    @Override
    protected void execute() {
        Robot.planetary.update();
    }
    
    @Override
    protected boolean isFinished() {
        return Robot.planetary.isFinished();
    }
    
    @Override
    protected void end() {
        
    }
    
    @Override
    protected void interrupted() {
    }
}
