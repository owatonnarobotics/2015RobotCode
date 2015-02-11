package org.usfirst.frc.team4624.robot.autonomous;

import org.usfirst.frc.team4624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoRotate extends Command {

	private int time;
	private Timer timer;
	private boolean direction;
	
	public AutoRotate(boolean direction) {
		requires(Robot.powertrain);
		time = 2000; //TODO guess and check time for 90 degree rotation
	}
	
	@Override
	protected void end() {
		timer.reset();
		timer.stop();
	}

	@Override
	protected void execute() {
	    if(direction) {
	        Robot.powertrain.move(0, 0, 1); //TODO find correct rate of rotation (90 degrees)
	    }
	    else {
	        Robot.powertrain.move(0, 0, -1); //TODO find correct rate of rotation (90 degrees)
	    }
	}

	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	@Override
	protected void interrupted() {
	}

	@Override
	protected boolean isFinished() {
		return timer.get() >= time;
	}
	
}
