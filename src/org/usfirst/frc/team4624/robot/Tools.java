package org.usfirst.frc.team4624.robot;



/**
 * A utility class used to simplify the code.
 * @author Team 4624
 *
 */
public class Tools {
    
    
    
    /**
     * Clamp a value between two different values
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
     * @param value
     * @param min
     * @param max
     * @return clamped value
     */
    public static short clamp(short value, short min, short max) {
        return (short) Math.max(min, Math.min(max, value));
    }
    
    
    
    /**
     * Find the difference between two values
     * @param a
     * @param b
     * @return difference
     */
    public static byte difference(byte a, byte b) {
        return (byte) Math.abs(a - b);
    }
    
    /**
     * Find the difference between two values
     * @param a
     * @param b
     * @return difference
     */
    public static double difference(double a, double b) {
        return Math.abs(a - b);
    }
    
    /**
     * Find the difference between two values
     * @param a
     * @param b
     * @return difference
     */
    public static float difference(float a, float b) {
        return Math.abs(a - b);
    }
    
    /**
     * Find the difference between two values
     * @param a
     * @param b
     * @return difference
     */
    public static int difference(int a, int b) {
        return Math.abs(a - b);
    }
    
    /**
     * Find the difference between two values
     * @param a
     * @param b
     * @return difference
     */
    public static long difference(long a, long b) {
        return Math.abs(a - b);
    }
    
    /**
     * Find the difference between two values
     * @param a
     * @param b
     * @return difference
     */
    public static short difference(short a, short b) {
        return (short) Math.abs(a - b);
    }
    
    
    
}
