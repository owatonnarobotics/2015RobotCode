package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Planetary extends Subsystem {
    
     CANJaguar planetary;
     
     private boolean spinning;
     
     public Planetary() {
         this.init();
     }
     
     public void init() {
         spinning  = false;
         //planetary = new CANJaguar( RobotMap.PORT_ENCODER_JAGUAR );
         planetary = new CANJaguar( 16 );
         //this.planetary.
     }
    
    public void toggle() {
        spinning = !spinning;
        setMotor(spinning );
    }
    
    public void setRaw( double number ) {
        planetary.set( number );
    }
    
    public void setMotor( boolean spinning ) {
        if (spinning ) {
            setRaw(.5);
        } else {
            setRaw(0);
        }
    }
    
    public void stop() {
        planetary.set(0);
    }
    
    public double getSpeed(){
        return planetary.getSpeed();
    }
    public boolean getSpinning() {
        return spinning;
    }
    
    @Override
    protected void initDefaultCommand() {
        
    }

}
