package org.usfirst.frc.team4624.robot;



public class Util {
    
    
    
    /**
     * Clamp a value between two other values
     * 
     * @param value
     * @param min
     * @param max
     * @return The clamped value
     */
    public static double clamp(double value, double min, double max) {
    
        return Math.max(min, Math.min(max, value));
    }
    
    /**
     * Clamp a value between two other values
     * 
     * @param value
     * @param min
     * @param max
     * @return The clamped value
     */
    public static int clamp(int value, int min, int max) {
    
        return Math.max(min, Math.min(max, value));
    }
}
