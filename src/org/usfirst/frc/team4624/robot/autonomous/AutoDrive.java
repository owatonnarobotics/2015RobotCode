package org.usfirst.frc.team4624.robot.autonomous;

import org.usfirst.frc.team4624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoDrive extends Command {

    private double forward;
    private double right;
	
	public AutoDrive(double time, double forward) {
		requires(Robot.powertrain);
		this.setTimeout(time);
		this.forward = -forward;
		this.right = 0;
	}
	
	public AutoDrive(double time, double forward, double right) {
        this(time, forward);
        this.right = right;
    }

    @Override
    protected void initialize() {
    }

	@Override
	protected void execute() {
	    Robot.powertrain.move(right, forward, 0); // Will have to guess/check these later
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
