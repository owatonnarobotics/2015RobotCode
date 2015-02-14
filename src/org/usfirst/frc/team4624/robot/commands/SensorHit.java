package org.usfirst.frc.team4624.robot.commands;

import org.usfirst.frc.team4624.robot.OI;

import edu.wpi.first.wpilibj.command.Command;

public class SensorHit extends Command {
    
    @Override
    protected void initialize() {
        this.setTimeout(.5);
        OI.xboxController.setRumble(.5);
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
