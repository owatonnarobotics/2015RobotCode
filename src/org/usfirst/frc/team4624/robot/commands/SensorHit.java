package org.usfirst.frc.team4624.robot.commands;

import org.usfirst.frc.team4624.robot.OI;
import org.usfirst.frc.team4624.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class SensorHit extends Command {
    
    public SensorHit() {
        requires(Robot.forklift);
    }
    @Override
    protected void initialize() {
        this.setTimeout(.5);
        OI.xboxController.setRumble(.5);
        System.out.println("Sensor Initialized");
        Robot.forklift.levelGoal = 0;
    }

    @Override
    protected void execute() { 
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return this.isTimedOut();
    }

    @Override
    protected void end() {
        OI.xboxController.setRumble(0);
        
    }

    @Override
    protected void interrupted() {
        // TODO Auto-generated method stub
        
    }

}
