package org.usfirst.frc.team4624.robot.subsystems;



import org.usfirst.frc.team4624.robot.RobotMap; // ENUMS for the ports
import org.usfirst.frc.team4624.robot.commands.DriveCommand;
import org.usfirst.frc.team4624.robot.library.XboxController;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;



public class Powertrain extends Subsystem {
    
    
    
    /* Instance Values */
    RobotDrive motors;
    
    
    
    /**
     * Initializes Powertrain subsystem. Should only be called once.
     */
    public Powertrain() {
    
        /* Initialize */
        final Jaguar rearleftMotor = new Jaguar(RobotMap.PWM_MOTOR_REAR_LEFT_PORT);
        final Jaguar rearRightMotor = new Jaguar(RobotMap.PWM_MOTOR_REAR_RIGHT_PORT);
        final Jaguar frontLeftMotor = new Jaguar(RobotMap.PWM_MOTOR_FRONT_LEFT_PORT);
        final Jaguar frontRightMotor = new Jaguar(RobotMap.PWM_MOTOR_FRONT_RIGHT_PORT);
        
        motors = new RobotDrive(frontLeftMotor, rearleftMotor, frontRightMotor, rearRightMotor);
        
        stop();
        
    }
    
    @Override
    public void initDefaultCommand() {
    
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveCommand());
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    /**
     * Map the linear values of the controller input to something else
     * 
     * @param input
     * @return the input mapped to a quarter circle
     */
    double inputFunction(final double input) {
    
        final double abs = Math.abs(input);
        final double output = -Math.sqrt(1 - Math.pow(abs, 2)) + 1;
        if (input > 0) {
            return output;
        } else {
            return -output;
        }
    }
    
    /**
     * Inform any listening commands to end
     * 
     * @return true will end the command
     */
    public boolean isFinished() {
    
        return false;
    }
    
    /**
     * Move the powertrain in a sensible fashion for autonomous mode
     * 
     * @param x
     * @param y
     * @param rotation
     */
    public void move(final double x, final double y, final double rotation) {
    
        motors.mecanumDrive_Cartesian(x, y, rotation, 0);
    }
    
    /**
     * Control the powertrain directly from an XBoxController
     * 
     * @param controller
     */
    public void setAsTankdrive(final XboxController controller) {
    
        final double boostScale = .5; // Smaller makes non boost slower. Boost is always full speed.
        
        final double x = inputFunction(controller.leftStick.getX()) *
                         (controller.leftStick.get() ? 1 : boostScale);
        final double y = inputFunction(controller.leftStick.getY()) *
                         (controller.leftStick.get() ? 1 : boostScale);
        final double turn = inputFunction(controller.rightStick.getX()) *
                            RobotMap.ROTATE_SPEED;
        
        motors.mecanumDrive_Cartesian(x, -y, // We already corrected for the mistake that this method also corrects
                turn, 0);
    }
    
    /**
     * Stop the powertrain motors if needed for some reason...
     */
    public void stop() {
    
        motors.stopMotor();
    }
}
