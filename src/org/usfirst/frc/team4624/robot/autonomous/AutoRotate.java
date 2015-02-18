package org.usfirst.frc.team4624.robot.autonomous;

import org.usfirst.frc.team4624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoRotate extends Command {

	private boolean direction;
	
	public AutoRotate(boolean direction) {
		requires(Robot.powertrain);
		this.direction = direction;
	}
	@Override
	protected void initialize() {
		this.setTimeout(.7);
	}
	
    @Override
    protected void execute() {
        if(direction) {
            Robot.powertrain.move(0, 0, -.5); //TODO find correct rate of rotation (90 degrees)
        }
        else {
            Robot.powertrain.move(0, 0, .5); //TODO find correct rate of rotation (90 degrees)
        }
    }

    @Override
    protected boolean isFinished() {
        return this.isTimedOut();
    }
    
    @Override
    protected void end() {
    }


	@Override
	protected void interrupted() {
	}
	
}
