package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Forklift extends Subsystem {

    CANJaguar liftParent;
    CANJaguar liftChild;
    
    public Forklift() {
        this.liftParent = new CANJaguar( RobotMap.CAN_ADDRESS_LIFT_PARENT );
        this.liftChild  = new CANJaguar( RobotMap.CAN_ADDRESS_LIFT_CHILD );
        
        this.liftChild.setVoltageMode();
    }
    
    public void stop() {
        // TODO Auto-generated method stub
    }


    public void changeLevel(int changeLevel) {
        // TODO Auto-generated method stub
    }
    
    @Override
    protected void initDefaultCommand() {
    }

    public void changeHeight(int changeHeight) {
        // TODO Auto-generated method stub
    }
    
    public double getHeight(double curveDistance) {
        return (27.7 - 38 * Math.toDegrees(Math.cos(43.2 + (360 * curveDistance) / (76 * Math.PI))));
    }
    
    public double distanceToRevolutions(double distance) {
        return distance / 2.5;
    }
    
    /**
     * Set the voltage of the child motor to the parent motor
     */
    private void parentChild() {
        liftChild.set(liftParent.getOutputVoltage());
    }
    
    /**
     * Update the system. Call this from a default command.
     */
    public void execute() {
        parentChild();
    }
}
