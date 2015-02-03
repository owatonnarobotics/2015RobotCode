package org.usfirst.frc.team4624.robot.commands;

import org.usfirst.frc.team4624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftLevel extends Command {
    
    public int level;
    
    public LiftLevel(int changeLevel) {
        requires(Robot.forklift)
        if(changeLevel == 0) {
            Robot.forklift.stop();
        } else {
            Robot.forklift.changeLevel(changeLevel);
        }
    }
    
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return true;    // Move levels can be cumulative
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}
