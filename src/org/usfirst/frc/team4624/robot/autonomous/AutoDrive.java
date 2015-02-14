package org.usfirst.frc.team4624.robot.autonomous;

import org.usfirst.frc.team4624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoDrive extends Command {

	private int time;
	private Timer timer;
	private boolean forward;
	
	public AutoDrive(int time, boolean forward) {
		requires(Robot.powertrain);
		this.time = time;
		this.forward = forward;
	}
	
	@Override
	protected void end() {
		timer.stop();
	}

	@Override
	protected void execute() {
	    if(forward) {
	        Robot.powertrain.move(0, .25, 0); // Will have to guess/check these later
	    }
	    else {
	        Robot.powertrain.move(0, -.25, 0);
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
