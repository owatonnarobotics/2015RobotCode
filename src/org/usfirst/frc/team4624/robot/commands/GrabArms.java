package org.usfirst.frc.team4624.robot.commands;

import org.usfirst.frc.team4624.robot.OI;
import org.usfirst.frc.team4624.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GrabArms extends Command {

    public GrabArms() {
        requires(Robot.pneumaticArms);
    }
    
    @Override
    protected void initialize() {
    }
    
    @Override
    protected void execute() {
        Robot.pneumaticArms.grab();
        SmartDashboard.putString("Arm Status: ", "Grabbing");
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
