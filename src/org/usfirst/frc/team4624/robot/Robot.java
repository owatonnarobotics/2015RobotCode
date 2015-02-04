package org.usfirst.frc.team4624.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team4624.robot.commands.DriveCommand;
import org.usfirst.frc.team4624.robot.input.DashboardIO;
import org.usfirst.frc.team4624.robot.subsystems.Powertrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    public static       OI              oi;

    public static final Powertrain      powertrain      = new Powertrain();
    //public static final DashboardIO     dashboardio     = new DashboardIO();

    Command driveCommand;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        oi              = new OI();
        driveCommand    = new DriveCommand();
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        driveCommand.start();
        //dashboardio.updatePID();
    }

    /**
     * This function is called when the disabled button is hit. You can use it
     * to reset subsystems before shutting down.
     */
    public void disabledInit() {
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        /*
        Scheduler.getInstance().run();
        if(dashboardio.newPID()) {
            dashboardio.setPID();
            forklift.reinit();
        }
        if(dashboardio.newU()) {
            dashboardio.setU();
            forklift.reinit();
        }
        if(dashboardio.newGoal(forklift.getGoal())) {
            forklift.setGoal(dashboardio.getGoal());
        }
        dashboardio.updateCurrentAndGoal(forklift.getCurrent(), forklift.getGoal());
        forklift.update();
        */
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
