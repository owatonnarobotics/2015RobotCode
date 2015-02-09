package org.usfirst.frc.team4624.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team4624.autonomous.*;
import org.usfirst.frc.team4624.robot.commands.*;
import org.usfirst.frc.team4624.robot.input.*;
import org.usfirst.frc.team4624.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    
    /* Subsystems */
/** A reference to the Powertrain subsystem */
    public static Powertrain     powertrain = new Powertrain();
    
/** A reference to the Forkliftsubsystem */
    public static Forklift       forklift = new Forklift();
    
/** A reference to the PneumaticArms subsystem */
    public static PneumaticArms  pneumaticArms = new PneumaticArms();
    
/** A reference to the CAN_Compressor subsystem */
    public static CAN_Compressor compressor = new CAN_Compressor();
    
    
    // TODO make static
    public final DashboardIO dashboardio = new DashboardIO();
    
    /* Commands */
    Command driveCommand;
    

    //CommandGroup currentAutoPreset;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        /* Initialize operator input */
        new OI();
        
        
        

        
        
        
        /* Initialize 'always on' commands */
        driveCommand    = new DriveCommand();
        
        //currentAutoPreset = new ExampleAutonomusCommand();
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        //currentAutoPreset.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        driveCommand.start();
        dashboardio.updatePID();
    }

    /**
     * This function is called when the disabled button is hit. You can use it
     * to reset subsystems before shutting down.
     */
    public void disabledInit() {
        System.out.printf("Hey! Did you guys %s?\n", "win");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        // Update rate on the forklift
        forklift.update();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
