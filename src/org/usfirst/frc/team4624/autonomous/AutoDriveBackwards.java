package org.usfirst.frc.team4624.autonomous;

import org.usfirst.frc.team4624.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveBackwards extends Command {

    private int time;
    private Timer timer;
    
    public AutoDriveBackwards(int time) {
        requires(Robot.powertrain);
        this.time = time;
    }
    
    @Override
    protected void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    protected void execute() {
        Robot.powertrain.move(0,-1,0);  // Move backwards at supersonic speeds. Don't stop please
    }

    @Override
    protected boolean isFinished() {
        return timer.get() >= time;
    }

    @Override
    protected void end() {
        timer.stop();
        timer.reset();
    }

    @Override
    protected void interrupted() {
        end();
    }

}
