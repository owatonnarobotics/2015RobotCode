package org.usfirst.frc.team4624.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PneumaticArms extends Subsystem {

    /* Instance Values */
    DoubleSolenoid arms;
    
    public PneumaticArms() {
        arms = new DoubleSolenoid(0, 1); //TODO Place these in RobotMap
        grab();
    }
    
    public void grab() {
        arms.set(DoubleSolenoid.Value.kForward);
    }
    
    public void release() {
        arms.set(DoubleSolenoid.Value.kOff);
    }

    @Override
    protected void initDefaultCommand() {        
    }
}
