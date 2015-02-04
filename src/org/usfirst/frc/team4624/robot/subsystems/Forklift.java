package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;
import org.usfirst.frc.team4624.robot.commands.DriveCommand;
import org.usfirst.frc.team4624.robot.commands.LiftManual;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Forklift extends Subsystem {

    CANJaguar liftMotor;
    
    private int codesPerRev = 497;
    private double goal; // In Rotations
    
    public Forklift() {
        liftMotor = new CANJaguar(RobotMap.PORT_FORKLIFT);
        liftMotor.setPositionMode(CANJaguar.kQuadEncoder, codesPerRev,
                RobotMap.p, RobotMap.i, RobotMap.d);
        liftMotor.enableControl(0);
        goal = liftMotor.getPosition();
    }
    
    public void stop() {
        goal = liftMotor.getPosition();
        liftMotor.set(goal);
    }


    public void changeLevel(int changeLevel) { // Convert to rotations here (changeLevel is in levels)
        // TODO Auto-generated method stub
    }
    
    public void changeHeight(double changeHeight) { // Convert to rotations here (changeHeight is in distance)
        // TODO Auto-generated method stub
    }
    
    private void changeGoal(double changeGoal) { // Takes in Rotations
        goal += changeGoal;
        goal = clamp(goal, 0, RobotMap.FORKLIFT_MAX_ROTATIONS);
        liftMotor.set(goal);
    }
    
    private double clamp(double raw, double min, double max) {
        return Math.max(min, Math.min(max, raw));
    }
    
    private double distanceToRotations(double distance) { //This is the important one
        return 0.0; //TODO Add Maths
    }
    
    public boolean isFinished() {
        return (Math.abs(liftMotor.getPosition() - goal) < RobotMap.MOTOR_ACCURACY);
    }
    
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new LiftManual());
    }
    
    /* Smart Dashboard */
    public double getPosition() {
        return liftMotor.getPosition();
    }
    
    public double getGoal() {
        return goal;
    }
    
    public void setGoal(double newGoal) {
        goal = newGoal;
        liftMotor.set(goal);
    }
    
    public void reinit() {
        liftMotor.setPositionMode(CANJaguar.kQuadEncoder, codesPerRev,
                RobotMap.p, RobotMap.i, RobotMap.d);
    }
 
}
