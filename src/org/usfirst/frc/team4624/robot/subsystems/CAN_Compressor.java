package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CAN_Compressor extends Subsystem {

    Compressor compressor;
    
    /**
     * Initializes CAN_Compressor subsystem. Should only be called once.
     */
    public CAN_Compressor() {
        compressor = new Compressor(RobotMap.PCM_COMPRESSOR_PORT);
        compressor.start();
    }
    
    @Override
    protected void initDefaultCommand() {
    }

}