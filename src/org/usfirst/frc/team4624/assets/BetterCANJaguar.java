package org.usfirst.frc.team4624.assets;
import edu.wpi.first.wpilibj.CANJaguar;


public class BetterCANJaguar {
    CANJaguar   jaguar;
    double      minRPM;
    double      maxRPM;
    double      targetRotation;
    boolean     spinning;
    double      globalPosition;
    
    public BetterCANJaguar( int ID, int codesPerRevolution, double p, double i, double d ) {
        this.jaguar = new CANJaguar( ID );
        
        this.targetRotation = 0;
        this.minRPM         = 0;
        this.maxRPM         = 0;
        this.spinning       = false;
        this.globalPosition = 0;
        
        jaguar.setSpeedMode( CANJaguar.kQuadEncoder, codesPerRevolution, p, i, d );
        jaguar.enableControl( 0 );
    }
    
    double speedAtPosition( double currentRotation ) {
        // A large amount of algebra and calculus
        // TODO
        // Test this monstrosity
        final double t      = (targetRotation/(2*maxRPM)) * Math.log( ( -(2-4*maxRPM/minRPM) - Math.sqrt( Math.pow( 2-4*maxRPM/minRPM, 2) - 4 ) ) / 2 );
        final double x      = Math.log( ((targetRotation/currentRotation) - 1) / Math.pow( Math.E, (4*maxRPM*t)/(2*targetRotation) ) ) * (targetRotation/(-4*maxRPM));
        final double c      = Math.pow( Math.E, (4*maxRPM*t)/(2*targetRotation));
        final double speed  = (4*maxRPM * c * Math.pow( Math.E, (-4*maxRPM*x)/targetRotation)) / ( 1 + c * Math.pow( Math.E, Math.pow( Math.E, (-4*maxRPM*x)/targetRotation)));
        
        if( targetRotation < 0 ) {
            return -speed;
        } else {
            return speed;
        }
    }
    
    public boolean atTarget() {
        return Math.abs(jaguar.getPosition()) >= Math.abs(targetRotation);
    }
    
    public void setTarget( double targetRotation, double minRPM, double maxRPM ) {
        if ( !spinning ) {
            this.targetRotation = targetRotation;
            this.minRPM         = minRPM;
            this.maxRPM         = maxRPM;
            spinning            = true;
        } else {
            System.out.println( "The motor is already moving towards a goal!\nUse .cancel() to cancel the current operation.");
        }
    }
    
    public double getTarget() {
        return targetRotation;
    }
    
    void reset() {
        globalPosition  += jaguar.getPosition();
        jaguar.enableControl( 0 );
        
        targetRotation  = 0;
        minRPM          = 0;
        maxRPM          = 0;
        
        jaguar.set( 0 );
        spinning        = false;
    }
    
    public void cancel() {
        reset();
    }
    
    public void update() {
        if ( !atTarget() ) {
            jaguar.set( speedAtPosition(jaguar.getPosition()) ) ;
        } else if ( spinning ) {
            reset();
        }
    }
    
    public double getPosition() {
        return jaguar.getPosition();
    }
    
    public double getGlobalPosition() {
        return globalPosition + jaguar.getPosition();
    }
    
    public void setPID( double p, double i, double d ) {
        jaguar.setPID(p, i, d);
    }
    
    public double getSpeed() {
        return jaguar.getSpeed();
    }
}