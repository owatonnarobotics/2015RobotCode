package org.usfirst.frc.team4624.robot.autonomous;

import org.usfirst.frc.team4624.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class AutoLift extends Command {

    int level;
    
    public AutoLift(int level) {
        requires(Robot.forklift);
        this.level = level;
    }
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.forklift.liftToHeight(level);
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
    }
    
}
