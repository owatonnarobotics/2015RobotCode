package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticArms extends Subsystem {

    /* Instance Values */
    Solenoid arms;
    
    public PneumaticArms() {
        arms = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.PCM_SOLENOID_PORT); //TODO Place these in RobotMap
        arms.clearAllPCMStickyFaults();
        grab();
    }
    
    public void grab() {
        arms.set(true);
        System.out.println("Grabbed");
    }
    
    public void release() {
        arms.set(false);
        System.out.println("Released");
    }

    @Override
    protected void initDefaultCommand() {        
    }
}
