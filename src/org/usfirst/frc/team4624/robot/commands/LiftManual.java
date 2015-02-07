package org.usfirst.frc.team4624.robot.commands;

import org.usfirst.frc.team4624.robot.Robot;

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
        //Robot.forklift.setRaw((OI.xboxController.rt.getX() -
        //                             OI.xboxController.lt.getX()) * RobotMap.MANUAL_LIFT_SPEED);
        Robot.forklift.setRaw(.5);
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
