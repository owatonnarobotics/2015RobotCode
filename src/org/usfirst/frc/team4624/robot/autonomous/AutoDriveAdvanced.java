package org.usfirst.frc.team4624.robot.autonomous;

import org.usfirst.frc.team4624.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class AutoDriveAdvanced extends Command{

    private double time;
    private double distX;
    private double distY;
    
    public AutoDriveAdvanced(double time, double distX, double distY){
        
        this.time = time;
        this.distX = distX;
        this.distY = distY;
        requires(Robot.powertrain);
        this.setTimeout(time);
    }
    @Override
    protected void initialize() {
        
    }

    @Override
    protected void execute() { 
       Robot.powertrain.move(distX / time, distY / time, 0);
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
