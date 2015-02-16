package org.usfirst.frc.team4624.robot.autonomous;

import org.usfirst.frc.team4624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoDrive extends Command {

	private boolean forward;
	
	public AutoDrive(double time, boolean forward) {
		requires(Robot.powertrain);
		this.setTimeout(time);
		this.forward = forward;
	}

    @Override
    protected void initialize() {
    }

	@Override
	protected void execute() {
	    if(forward) {
	        Robot.powertrain.move(0, -.25, 0); // Will have to guess/check these later
	    }
	    else {
	        Robot.powertrain.move(0, .25, 0);
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
