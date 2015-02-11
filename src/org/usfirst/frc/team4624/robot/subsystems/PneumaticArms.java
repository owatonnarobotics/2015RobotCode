package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticArms extends Subsystem {

    Solenoid arms;
    
    /**
     * Initializes PneumaticArms subsystem. Should only be called once.
     */
    public PneumaticArms() {
        arms = new Solenoid(RobotMap.PCM_CAN_ID, RobotMap.PCM_SOLENOID_PORT);
        grab();
    }
    
    /**
     * When run, causes the arms of the robot to close.
     */
    public void grab() {
        arms.set(true);
    }
    
    /**
     * When run, causes the arms of the robot to open.
     */
    public void release() {
        arms.set(false);
    }

    @Override
    protected void initDefaultCommand() {        
    }
}
