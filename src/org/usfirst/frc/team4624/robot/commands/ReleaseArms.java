package org.usfirst.frc.team4624.robot.commands;



import org.usfirst.frc.team4624.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class ReleaseArms extends Command {
    
    
    
    /**
     * Cause the arms to release
     */
    public ReleaseArms() {
    
        requires(Robot.pneumaticArms);
    }
    
    @Override
    protected void end() {
    
    }
    
    @Override
    protected void execute() {
    
        Robot.pneumaticArms.release();
        SmartDashboard.putString("Arm Status: ", "Releasing");
    }
    
    @Override
    protected void initialize() {
    
    }
    
    @Override
    protected void interrupted() {
    
    }
    
    @Override
    protected boolean isFinished() {
    
        return true;
    }
}