package org.usfirst.frc.team4624.robot.autonomous;

import org.usfirst.frc.team4624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoDrive extends Command {

	private int time;
	private Timer timer;
	
	public AutoDrive(int dist) {
		requires(Robot.powertrain);
		//time = (dist * conversion); //TODO find time-distance conversion
		time = 1000;
	}
	
	@Override
	protected void end() {
		timer.reset();
		timer.stop();
		
	}

	@Override
	protected void execute() {
		//Robot.powertrain.move(x, y, 0);
		
	}

	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
