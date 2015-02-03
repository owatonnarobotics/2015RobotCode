package org.usfirst.frc.team4624.robot.input;

import org.usfirst.frc.team4624.robot.RobotMap;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardIO {

    public DashboardIO() {
        SmartDashboard.putString("Arm Status: ", "Unactivated");
        SmartDashboard.putNumber("U", 0);
        SmartDashboard.putNumber("P", 0);
        SmartDashboard.putNumber("I", 0);
        SmartDashboard.putNumber("D", 0);
        SmartDashboard.putNumber("Goal", 0);
        SmartDashboard.putNumber("Position", 0);
    }
    
    public boolean getBoolean(String key) {
        return SmartDashboard.getBoolean(key);
    }

    public String getString(String key) {
        return SmartDashboard.getString(key);
    }

    public double getNumber(String key) {
        return SmartDashboard.getNumber(key);
    }
    
    public void updateCurrentAndGoal(double current, double goal) {
        SmartDashboard.putNumber("Position", current);
        SmartDashboard.putNumber("Goal", goal);
    }
    
    public boolean newGoal(double goal) {
        return goal != getNumber("Goal");
    }
    
    public double getGoal() {
        return getNumber("Goal");
    }
    
    public void updatePID() {
        SmartDashboard.putNumber("U", RobotMap.u);
        SmartDashboard.putNumber("P", RobotMap.p);
        SmartDashboard.putNumber("I", RobotMap.i);
        SmartDashboard.putNumber("D", RobotMap.d);
    }

    public boolean newPID() {
        return !(RobotMap.p == getNumber("P") &&
                 RobotMap.i == getNumber("I") &&
                 RobotMap.d == getNumber("D"));
    }
    
    public boolean newU() {
        return !(RobotMap.u == getNumber("U"));
    }

    public void setPID() {
        RobotMap.p = getNumber("P");
        RobotMap.i = getNumber("I");
        RobotMap.d = getNumber("D");
        updatePID();
        System.out.println("Updated PID Values");
        System.out.println(RobotMap.p + " " + RobotMap.i + " " + RobotMap.d);
    }
    
    public void setU() {
        RobotMap.u = (int) getNumber("U");
        RobotMap.p = .6 * getNumber("U");
        RobotMap.i = 2 * getNumber("P") / getNumber("U");
        RobotMap.d = getNumber("P") * getNumber("U") / 8;
        updatePID();
        System.out.println("Updated U and PID Values");
        System.out.println(RobotMap.p + " " + RobotMap.i + " " + RobotMap.d);
    }
}
