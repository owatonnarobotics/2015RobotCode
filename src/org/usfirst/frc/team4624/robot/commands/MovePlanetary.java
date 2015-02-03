package org.usfirst.frc.team4624.robot.commands;

import org.usfirst.frc.team4624.robot.OI;
import org.usfirst.frc.team4624.robot.Robot;
import org.usfirst.frc.team4624.robot.input.XboxController;
import org.usfirst.frc.team4624.robot.subsystems.Forklift;

import edu.wpi.first.wpilibj.command.Command;

public class MovePlanetary extends Command {
    
    XboxController xboxController;
    
    public MovePlanetary() {
        requires(Robot.forklift);
        xboxController = OI.xboxController;
    }
    
    @Override
    protected void initialize() {
        //Robot.planetary.stop();
        System.out.println("MovePlanetary Initialized");
    }
    
    @Override
    protected void execute() {
        //Robot.planetary.toggle();
        //Robot.planetary.setRaw(xboxController.rt.getX());
        Robot.forklift.startPositionMode();
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
    
    @Override
    protected void end() {
        //Robot.planetary.stop();
    }
    
    @Override
    protected void interrupted() {
    }
}
