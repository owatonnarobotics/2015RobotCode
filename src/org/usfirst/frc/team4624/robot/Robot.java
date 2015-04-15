package org.usfirst.frc.team4624.robot;



import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4624.robot.autonomous.Autonomous;
import org.usfirst.frc.team4624.robot.commands.*;
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
    public static Powertrain     powertrain    = new Powertrain();
    
    /** A reference to the Forklift subsystem */
    public static Forklift       forklift      = new Forklift();
    
    /** A reference to the PneumaticArms subsystem */
    public static PneumaticArms  pneumaticArms = new PneumaticArms();
    
    /** A reference to the CAN_Compressor subsystem */
    public static CAN_Compressor compressor    = new CAN_Compressor();
    
    public static DigitalInput   toteDetector  = new DigitalInput(
                                                       RobotMap.PORT_TOTE_DETECTOR);
    
    /* Commands */
    Command                      driveCommand;
    
    Command                      autoCommand;
    
    SendableChooser              locationChooser;
    
    SendableChooser              goalChooser;
    
    SendableChooser              rotationChooser;
    
    SendableChooser              tripleChooser;
    
    SendableChooser              armChooser;
    
    SendableChooser              driveChooser;
    
    boolean                      pressed       = false;
    
    // CommandGroup currentAutoPreset;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        /* Initialize operator input */
        new OI();
        
        /* Initialize 'always on' commands */
        driveCommand = new DriveCommand();
        
        // currentAutoPreset = new ExampleAutonomusCommand();
        
        locationChooser = new SendableChooser();
        locationChooser.addDefault("No Platform", Integer.valueOf(0));
        locationChooser.addObject("Platform", Integer.valueOf(1)); // Could be a problem if it doesn't give the user time to choose
        
        goalChooser = new SendableChooser();
        goalChooser.addDefault("Bin", Integer.valueOf(0));
        goalChooser.addObject("Tote", Integer.valueOf(1));
        goalChooser.addObject("Drive", Integer.valueOf(2));
        goalChooser.addObject("Nothing", Integer.valueOf(3));
        
        driveChooser = new SendableChooser();
        driveChooser.addDefault("Full Drive", Integer.valueOf(0));
        driveChooser.addDefault("Half Drive", Integer.valueOf(1));
        
        rotationChooser = new SendableChooser();
        rotationChooser.addDefault("Rotate Left (Counter-Clockwise)", Integer.valueOf(0));
        rotationChooser.addObject("Rotate Right (Clockwise)", Integer.valueOf(1));
        rotationChooser.addObject("No Rotate", Integer.valueOf(2));
        
        tripleChooser = new SendableChooser();
        tripleChooser.addDefault("Standard", Integer.valueOf(0));
        tripleChooser.addObject("3 Tote (UNSTABLE)", Integer.valueOf(1));
        
        armChooser = new SendableChooser();
        armChooser.addDefault("Arms Enabled", Integer.valueOf(0));
        armChooser.addObject("Arms Disabled", Integer.valueOf(1));
        
        SmartDashboard.putData("Auto Location", locationChooser);
        SmartDashboard.putData("Auto Goal", goalChooser);
        SmartDashboard.putData("Auto Rotation", rotationChooser);
        SmartDashboard.putData("Auto Type", tripleChooser);
        SmartDashboard.putData("Auto Arms", armChooser);
        SmartDashboard.putData("Auto Drive Distance", driveChooser);
        
    }
    
    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }
    
    @Override
    public void autonomousInit() {
        autoCommand = new Autonomous(
                ((Integer) locationChooser.getSelected()).intValue(),
                ((Integer)     goalChooser.getSelected()).intValue(),
                ((Integer) rotationChooser.getSelected()).intValue(),
                ((Integer)   tripleChooser.getSelected()).intValue(),
                ((Integer)      armChooser.getSelected()).intValue(),
                ((Integer)    driveChooser.getSelected()).intValue());
        
        autoCommand.start();
    }
    
    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        forklift.update();
    }
    
    @Override
    public void teleopInit() {
        driveCommand.start();
    }
    
    /**
     * This function is called when the disabled button is hit. You can use it
     * to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit() {
        System.out.printf("Hey! Did you guys %s?\n", "win");
    }
    
    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        if (toteDetector.get() && !pressed) {
            new SensorHit().start();
            pressed = true;
        } else if (! toteDetector.get()) {
            pressed = false;
        }
        // Update rate on the forklift
        forklift.update();
    }
    
    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }
}
