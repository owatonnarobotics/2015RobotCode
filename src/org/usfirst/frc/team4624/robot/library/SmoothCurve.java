package org.usfirst.frc.team4624.robot.library;



/**
 * Smoothly changes values over time
 * 
 * @author AJ Granowski
 */
public class SmoothCurve {
    
    
    
    double  startValue;
    double  endValue;
    
    double  startTime;
    double  totalTime;
    
    boolean isFinished;
    
    
    
    /**
     * Initialize the curve
     * 
     * @param startValue
     * @param endValue
     * @param seconds
     */
    public SmoothCurve(final double startValue, final double endValue, final double seconds) {
    
        this.startValue = startValue;
        this.endValue = endValue;
        
        startTime = currentSeconds();
        totalTime = seconds;
        
        isFinished = false;
    }
    
    
    
    /**
     * The current time of the system in seconds
     * 
     * @return seconds
     */
    private double currentSeconds() {
    
        return System.currentTimeMillis() / 1000;
    }
    
    /**
     * Get the current value as it moves along the curve
     * 
     * @return
     */
    public double get() {
    
        if (!isFinished) {
            final double timePassed = currentSeconds() - startTime;
            final double timeAsPercent = timePassed / totalTime;
            
            // The return MUST be the end value if it has been longer than the time of the curve
            if (timePassed > totalTime) {
                isFinished = true;
                return endValue;
            }
            
            return startValue +
                   ((endValue - startValue) * mapToCurve(timeAsPercent));
        }
        
        return endValue;
    }
    
    /**
     * Has this curve been completed?
     * 
     * @return
     */
    public boolean isFinished() {
    
        return isFinished;
    }
    
    /**
     * Map a linear value (0%-100%) to a curve (0%-100%)
     * 
     * @param percentComplete
     * @return corresponding curve percent
     */
    private double mapToCurve(double percentComplete) {
    
        percentComplete = Tools.clamp(percentComplete, 0, 1);
        
        return (1 - Math.cos(Math.PI * percentComplete)) / 2;
    }
}
