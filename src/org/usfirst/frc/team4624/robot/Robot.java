package org.usfirst.frc.team4624.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team4624.robot.commands.DriveCommand;
import org.usfirst.frc.team4624.robot.commands.LiftManual;
import org.usfirst.frc.team4624.robot.input.DashboardIO;
import org.usfirst.frc.team4624.robot.subsystems.CAN_Compressor;
import org.usfirst.frc.team4624.robot.subsystems.Forklift;
import org.usfirst.frc.team4624.robot.subsystems.PneumaticArms;
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

    public static final Powertrain     powertrain    = new Powertrain();
    public static final Forklift       forklift      = new Forklift();
    public static final PneumaticArms  pneumaticArms = new PneumaticArms();
    public static final CAN_Compressor compressor    = new CAN_Compressor();
    public static final DashboardIO    dashboardio   = new DashboardIO();

    Command driveCommand;
    Command movePlanetary;
    Command releaseArms;
    Command liftManual;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        oi             = new OI();
      //compressor     = new CAN_Compressor();
        driveCommand   = new DriveCommand();
        liftManual     = new LiftManual();
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
        liftManual.start();
        dashboardio.updatePID();
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
        
        if(dashboardio.newPID()) {
            dashboardio.setPID();
            forklift.reinit();
        }
        if(dashboardio.newU()) {
            dashboardio.setU();
            forklift.reinit();
        }
        if(dashboardio.newGoal(forklift.getPosition())) {
            forklift.setGoal(dashboardio.getGoal());
        }
        dashboardio.updateCurrentAndGoal(forklift.getPosition(), forklift.getGoal());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
