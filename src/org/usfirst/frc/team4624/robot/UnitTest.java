package org.usfirst.frc.team4624.robot;

public class UnitTest
{
    public double getHeight(double angle) {
        return (38 * Math.toDegrees(Math.sin(Math.toRadians(angle))) / Math.toDegrees(Math.sin(Math.toRadians(136.8 - angle))));
    }

    public double getAngle(double curveDistance) {
        return 43.2 + Math.toDegrees(curveDistance / RobotMap.ARM_LENGTH);
        //TODO Get 43.2 into robotmap
    }

    public double getStrapLength(double angle) { //Starting from 43.2 degreees
        return Math.sqrt(Math.pow(RobotMap.CORNER_TO_PIVOT, 2) + Math.pow(RobotMap.ARM_LENGTH, 2) - (2 * RobotMap.CORNER_TO_PIVOT
                * RobotMap.ARM_LENGTH * Math.cos(angle)));
    }

    public double distanceToRevolutions(double distance) {
        return distance / 2.5; //TODO Adjust for strap wrapping around
    }
}
