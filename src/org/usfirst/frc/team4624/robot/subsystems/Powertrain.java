package org.usfirst.frc.team4624.robot.subsystems;



import org.usfirst.frc.team4624.robot.RobotMap; // ENUMS for the ports
import org.usfirst.frc.team4624.robot.commands.DriveCommand;
import org.usfirst.frc.team4624.robot.input.XboxController;
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
        final Jaguar rearleftMotor = new Jaguar(
                RobotMap.PWM_MOTOR_REAR_LEFT_PORT);
        final Jaguar rearRightMotor = new Jaguar(
                RobotMap.PWM_MOTOR_REAR_RIGHT_PORT);
        final Jaguar frontLeftMotor = new Jaguar(
                RobotMap.PWM_MOTOR_FRONT_LEFT_PORT);
        final Jaguar frontRightMotor = new Jaguar(
                RobotMap.PWM_MOTOR_FRONT_RIGHT_PORT);
        
        motors = new RobotDrive(frontLeftMotor, rearleftMotor, frontRightMotor,
                rearRightMotor);
        
        stop();
    }
    
    @Override
    public void initDefaultCommand() {
    
        setDefaultCommand(new DriveCommand());
    }
    
    /**
     * Transforms liner input to curved output to allow for more sensitivity at
     * low values
     * 
     * @param input Linear input from a controller
     * @return Curved output
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
    
    public boolean isFinished() {
    
        return false;
    }
    
    /**
     * Move the robot. This is used in the autonomous mode
     * 
     * @param x
     * @param y
     * @param rotation
     */
    public void move(double x, double y, double rotation) {
    
        motors.mecanumDrive_Cartesian(x, -y, rotation, 0);
    }
    
    
    /**
     * Move the robot based on the controller's current values
     * 
     * @param controller The XboxController
     */
    public void setAsTankdrive(XboxController controller) {
    
        final double boostScale = .5; // Smaller makes non boost slower. Boost is always full speed.
        
        final double x = inputFunction(controller.leftStick.getX())
                * (controller.leftStick.get() ? 1 : boostScale);
        final double y = inputFunction(controller.leftStick.getY())
                * (controller.leftStick.get() ? 1 : boostScale);
        final double turn = inputFunction(controller.rightStick.getX())
                * RobotMap.ROTATE_SPEED;
        
        move(x, y, turn);
    }
    
    /**
     * Stop the motors from moving...?
     */
    public void stop() {
    
        motors.stopMotor();
    }
}