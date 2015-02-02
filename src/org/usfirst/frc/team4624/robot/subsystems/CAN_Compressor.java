package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CAN_Compressor extends Subsystem {

    Compressor compressor;
    
    public CAN_Compressor() {
        this.init();
    }
    
    public void init() {
        compressor = new Compressor(RobotMap.PORT_COMPRESSOR);
        compressor.start();
    }

    @Override
    protected void initDefaultCommand() {
    }

}
