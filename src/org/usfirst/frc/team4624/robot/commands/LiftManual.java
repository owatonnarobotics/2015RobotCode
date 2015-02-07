package org.usfirst.frc.team4624.robot.commands;

import org.usfirst.frc.team4624.robot.OI;
import org.usfirst.frc.team4624.robot.Robot;
import org.usfirst.frc.team4624.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class LiftManual extends Command {

    public LiftManual() {
        requires(Robot.forklift);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.forklift.setRaw((OI.xboxController.lt.getX() -
                                     OI.xboxController.rt.getX()) * RobotMap.MANUAL_LIFT_SPEED);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
        end();
    }

}
