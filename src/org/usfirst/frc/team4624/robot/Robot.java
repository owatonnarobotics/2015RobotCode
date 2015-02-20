package org.usfirst.frc.team4624.robot;



import org.usfirst.frc.team4624.robot.autonomous.Autonomous;
import org.usfirst.frc.team4624.robot.commands.DriveCommand;
import org.usfirst.frc.team4624.robot.commands.SensorHit;
import org.usfirst.frc.team4624.robot.subsystems.CAN_Compressor;
import org.usfirst.frc.team4624.robot.subsystems.Forklift;
import org.usfirst.frc.team4624.robot.subsystems.PneumaticArms;
import org.usfirst.frc.team4624.robot.subsystems.Powertrain;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



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
    
    /** Digital Input (DIO) used to detect totes */
    public static DigitalInput   toteDetector  = new DigitalInput(
                                                       RobotMap.PORT_TOTE_DETECTOR);
    
    /* Commands */
    Command                      driveCommand;
    Command                      autoCommand;
    SendableChooser              locationChooser;
    SendableChooser              goalChooser;
    private boolean              pressed       = false;
    
    
    
    @Override
    public void autonomousInit() {
    
        autoCommand = new Autonomous(
                ((Integer) locationChooser.getSelected()).intValue(),
                ((Integer) goalChooser.getSelected()).intValue());
        
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
    
    /**
     * This function is called when the disabled button is hit. You can use it
     * to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit() {
    
        System.out.printf("Hey! Did you guys %s?\n", "win");
    }
    
    @Override
    public void disabledPeriodic() {
    
        Scheduler.getInstance().run();
    }
    
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
        
        locationChooser = new SendableChooser();
        locationChooser.addDefault("No Platform", Integer.valueOf(0));
        locationChooser.addObject("Platform", Integer.valueOf(1)); // Could be a problem if it doesn't give the user time to choose
        
        goalChooser = new SendableChooser();
        goalChooser.addDefault("Bin", Integer.valueOf(0));
        goalChooser.addObject("Tote", Integer.valueOf(1));
        goalChooser.addObject("Nothing", Integer.valueOf(2));
        
        SmartDashboard.putData("Auto Location", locationChooser);
        SmartDashboard.putData("Auto Goal", goalChooser);
    }
    
    @Override
    public void teleopInit() {
    
        driveCommand.start();
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
        } else if (!toteDetector.get()) {
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