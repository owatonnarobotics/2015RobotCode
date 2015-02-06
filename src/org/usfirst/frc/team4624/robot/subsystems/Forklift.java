package org.usfirst.frc.team4624.robot.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Forklift extends Subsystem {

    CANJaguar liftParent;
    CANJaguar liftChild;
    
    public Forklift() {
        //liftParent = new CANJaguar()
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
}
