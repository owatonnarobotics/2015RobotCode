package org.usfirst.frc.team4624.robot.library;



/**
 * A utility class used to simplify the code.
 * 
 * @author Team 4624
 *
 */
public class Tools {
    
    
    
    /**
     * Clamp a value between two different values
     * 
     * @param value
     * @param min
     * @param max
     * @return clamped value
     */
    public static byte clamp(byte value, byte min, byte max) {
    
        return (byte) Math.max(min, Math.min(max, value));
    }
    
    /**
     * Clamp a value between two different values
     * 
     * @param value
     * @param min
     * @param max
     * @return clamped value
     */
    public static double clamp(double value, double min, double max) {
    
        return Math.max(min, Math.min(max, value));
    }
    
    /**
     * Clamp a value between two different values
     * 
     * @param value
     * @param min
     * @param max
     * @return clamped value
     */
    public static float clamp(float value, float min, float max) {
    
        return Math.max(min, Math.min(max, value));
    }
    
    /**
     * Clamp a value between two different values
     * 
     * @param value
     * @param min
     * @param max
     * @return clamped value
     */
    public static int clamp(int value, int min, int max) {
    
        return Math.max(min, Math.min(max, value));
    }
    
    /**
     * Clamp a value between two different values
     * 
     * @param value
     * @param min
     * @param max
     * @return clamped value
     */
    public static long clamp(long value, long min, long max) {
    
        return Math.max(min, Math.min(max, value));
    }
    
    /**
     * Clamp a value between two different values
     * 
     * @param value
     * @param min
     * @param max
     * @return clamped value
     */
    public static short clamp(short value, short min, short max) {
    
        return (short) Math.max(min, Math.min(max, value));
    }
    
    
    
}
