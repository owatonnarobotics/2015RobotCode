package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.assets.BetterCANJaguar;
import org.usfirst.frc.team4624.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Planetary extends Subsystem { // TODO Clean this code a lot. Get rid of useless methods, organize the rest.

    private double[] levels;

    private void goToPosition() { // Should move current position of motor from something to something else
        // goToRotation();
    }

    private void goToRotation() { // Corrects for inaccuracy in lifting mechanism (and ramps speed)

    }

    public void changePosition() {
        // goToPosition(Something);
    }

    public void setPosition() {
        // goToPosition(Something);
    }

    public void changeLevel() {
        // goToPosition(levels);
    }

    public void setLevel() {
        // goToPosition(levels);
    }

    BetterCANJaguar planetary;
    private boolean spinning;
    private long    startTime;
    private int     codesPerRev = 497;

    public Planetary() {
        this.init();
    }

    public void init() {
        spinning = false;
        planetary = new BetterCANJaguar(RobotMap.PORT_ENCODER_JAGUAR, codesPerRev, RobotMap.p, RobotMap.i, RobotMap.d);
    }
    
    public void setTarget( double rotations ) {
        planetary.setTarget(rotations, 1, 10);
    }

    public void reinit() {
        planetary.setPID(RobotMap.p, RobotMap.i, RobotMap.d);
    }
    
    public void update() {
        planetary.update();
    }

    public double getPosition() {
        return planetary.getPosition();
    }
    
    public double getTarget() {
        return planetary.getTarget();
    }
    
    public boolean isStopped() {
        return planetary.atTarget();
    }
    
    public double getCurrent() {
        return planetary.getPosition();
    }

    public void stop() {
        planetary.cancel();
    }

    public double getSpeed() {
        return planetary.getSpeed();
    }

    public boolean getSpinning() {
        return spinning;
    }
    
    public boolean isFinished() {
        return planetary.atTarget();
    }

    @Override
    protected void initDefaultCommand() {

    }
}
