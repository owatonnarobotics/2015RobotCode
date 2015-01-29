package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Planetary extends Subsystem {
    
     CANJaguar planetary;
     
     private boolean spinning;
     
     private long startTime;
     
     public Planetary() {
         this.init();
     }
     
     public void init() {
         spinning  = false;
         planetary = new CANJaguar(RobotMap.PORT_ENCODER_JAGUAR);
         planetary.setPositionMode(CANJaguar.kQuadEncoder, 511, 10000, 0, 0); // P, I, D
     }
    
    public void toggle() {
        spinning = !spinning;
        setMotor(spinning );
    }
    
    public void togglePositionMode(){
        spinning = !spinning;
        if (spinning){
            System.out.println("Test");
            planetary.enableControl();
            //planetary.set(1);
        }
        else{
            stopPositionMode();
        }
    }
    
    public void startPositionMode(){
        startTime = System.currentTimeMillis();
        spinning = true;
        planetary.enableControl();
        planetary.set(5);
    }
    
    public void stopPositionMode(){
        if (spinning){
            planetary.disableControl();
            spinning = false;
        }
    }
    
    public double getPosition(){
        return planetary.getPosition();
    }
    
    public double getP(){
        return planetary.getP();
    }
    
    public double getD(){
        return planetary.getD();
    }
    
    public double getTime(){
        return startTime;
    }
    
    public double getSet(){
        return planetary.get();
    }
    
    public void updateSet(){
        planetary.set(planetary.get() + 5);
        startTime = System.currentTimeMillis();
    }
    
    public void update(){
        //planetary.setP(10 + (planetary.getPosition() * 0.01));
        //planetary.setD(0.5 - (planetary.getPosition() * 0.01));
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
