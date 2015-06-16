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
    GyroSensor gyro;
    
    
    
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
        
        gyro = new GyroSensor();
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
    public void move(double x, double y, double rotation) {
    
        motors.mecanumDrive_Cartesian(x, y, rotation, 0);
    }
    
    /**
     * Control the powertrain directly from an XBoxController
     * 
     * @param controller
     */
    public void setAsTankdrive(XboxController controller) {
    
        final double boostScale = .5; // Smaller makes non boost slower. Boost is always full speed.
        
        final double x = inputFunction(controller.leftStick.getX())
                * (controller.leftStick.get() ? 1 : boostScale);
        final double y = inputFunction(controller.leftStick.getY())
                * (controller.leftStick.get() ? 1 : boostScale);
        final double turn = inputFunction(controller.rightStick.getX())
                * RobotMap.ROTATE_SPEED;
        
        motors.mecanumDrive_Cartesian(x, -y, // We already corrected for the mistake that this method also corrects
                turn, 0);
    }
    
    /**
     * Stop the powertrain motors if needed for some reason...
     */
    public void stop() {
    
        motors.stopMotor();
    }
    
    
    /**
     * Calculates the difference between two angles. Max is 180.
     * @param angleA
     * @param angleB
     */
    private double angleDifference(double targetAngle, double currentAngle) {
        final double fullRotation   = 360;
        final double halfRotation   = fullRotation / 2;
        
        
        
        final double angleDifference = targetAngle - currentAngle;
        
        
        
        if (angleDifference > halfRotation) {
            return angleDifference - fullRotation;
        }
        if (angleDifference < -halfRotation) {
            return angleDifference + fullRotation;
        }
        
        return angleDifference;
    }
    
    /**
     * Rotate the robot to match the gyro angle
     * @param angle
     */
    private void setRawAngle(double angle) {
        final double constant = 360;
        double turnPower;
        
        turnPower = angleDifference(angle, gyro.getAngle()) / constant;
        
        turnPower = Math.pow( turnPower, 3);
        
        // Replace with clamp method
        if (turnPower > 1) {
            turnPower = 1;
        } else if (turnPower < -1) {
            turnPower = -1;
        }
        
        motors.mecanumDrive_Cartesian(0,0,turnPower,0);
    }
    
    boolean isTurning = false;
    double targetAngle = 0;
    double turnEndTime = 0;
    double totalTurnTime = 0;
    
    /**
     * Turn the robot to this relative angle
     */
    public void setAngle(double angle, double timeToTurn) {
        
        targetAngle = angle;
        totalTurnTime = timeToTurn;
        turnEndTime = System.currentTimeMillis() + (timeToTurn / 1000);
        isTurning = true;
    }
    
    /**
     * Returns a percent of the angle it should be at. 1 = complete.
     * @param endTime
     * @param totalTime
     * @return
     */
    private double functionOfT(double endTime, double totalTime) {
        final double currentTime        = System.currentTimeMillis();
        final double percentComplete    = 1 - ((endTime - currentTime) / totalTime);
        
        // Function of time T
        return 1 - Math.cos((Math.PI / 2) * percentComplete);
    }
    
    /**
     * Update the Powertraim for gyro stuff
     */
    public void update() {
        if (isTurning) {
            double currentTime = System.currentTimeMillis();
            
            
            
            double currentTargetAngle = functionOfT(turnEndTime, totalTurnTime) * targetAngle;
            
            // Offset currentTargetAngle to 0-360
            while(currentTargetAngle < 0) {
                currentTargetAngle += 360;
            }
            currentTargetAngle %= 360;
            
            // Set the current angle as a function of time T
            setRawAngle(currentTargetAngle);
            
            //double angleDifference = angleDifference(targetAngle, gyro.getAngle() - 180);
            //boolean reachedTarget = Math.abs(angleDifference) < 1;
            
            boolean reachedTarget = currentTime > turnEndTime;
            
            if (reachedTarget) {
                isTurning = false;
                gyro.reset();
            }
        }
    }
}
