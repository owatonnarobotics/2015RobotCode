package org.usfirst.frc.team4624.robot.subsystems;

import org.usfirst.frc.team4624.robot.RobotMap;

import edu.wpi.first.wpilibj.CANJaguar;
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

    CANJaguar planetary;
    private boolean spinning;
    private long    startTime;
    private int     codesPerRev = 497;

    public Planetary() {
        this.init();
    }

    public void init() {
        spinning = false;
        planetary = new CANJaguar(RobotMap.PORT_ENCODER_JAGUAR);
        planetary.setPositionMode(CANJaguar.kQuadEncoder, codesPerRev,
                RobotMap.p, RobotMap.i, RobotMap.d);
        planetary.set(100);
    }

    public void reinit() {
        planetary.setPositionMode(CANJaguar.kQuadEncoder, codesPerRev,
                RobotMap.p, RobotMap.i, RobotMap.d);
    }

    public void toggle() {
        spinning = !spinning;
        setMotor(spinning);
    }

    public void togglePositionMode() {
        spinning = !spinning;
        if (spinning) {
            System.out.println("Test");
            planetary.enableControl();
        } else {
            stopPositionMode();
        }
    }

    public void startPositionMode() {
        startTime = System.currentTimeMillis();
        spinning = true;
        planetary.enableControl();
        planetary.set(5);
    }

    public void stopPositionMode() {
        if (spinning) {
            planetary.disableControl();
            spinning = false;
        }
    }

    public double getPosition() {
        return planetary.getPosition();
    }

    public double getP() {
        return planetary.getP();
    }

    public double getD() {
        return planetary.getD();
    }

    public double getTime() {
        return startTime;
    }

    public double getSet() {
        return planetary.get();
    }

    public boolean isStopped() {
        return planetary.get() == 0;
    }

    public void updateSet() {
        planetary.set(planetary.get() + 5);
        startTime = System.currentTimeMillis();
    }

    public void update() {
        planetary.set(planetary.getPosition() + 5);
    }
    
    public double getCurrent() {
        return planetary.getPosition();
    }
    
    public double getGoal() {
        return planetary.get();
    }

    public void setRaw(double number) {
        planetary.set(number);
    }

    public void setMotor(boolean spinning) {
        if (spinning) {
            setRaw(.5);
        } else {
            setRaw(0);
        }
    }

    public void stop() {
        planetary.set(0);
    }

    public double getSpeed() {
        return planetary.getSpeed();
    }

    public boolean getSpinning() {
        return spinning;
    }

    public void setGoal(double goal) {
        planetary.set(goal);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
