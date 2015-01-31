package org.usfirst.frc.team4624.robot.input;

import org.usfirst.frc.team4624.robot.RobotMap;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardIO {

    public DashboardIO() {
    }

    public void updatePID() {
        SmartDashboard.putNumber("U", RobotMap.u);
        SmartDashboard.putNumber("P", RobotMap.p);
        SmartDashboard.putNumber("I", RobotMap.i);
        SmartDashboard.putNumber("D", RobotMap.d);
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

    public boolean newPID() {
        return (RobotMap.p == getNumber("P") ||
                RobotMap.i == getNumber("I") ||
                RobotMap.d == getNumber("D") ||
                RobotMap.u == getNumber("U"));
    }

    public void setPID() {
        RobotMap.p =       getNumber("P");
        RobotMap.i =       getNumber("I");
        RobotMap.d =       getNumber("D");
        RobotMap.u = (int) getNumber("U");
    }
}
