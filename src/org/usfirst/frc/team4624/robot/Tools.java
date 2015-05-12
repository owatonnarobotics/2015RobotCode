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
    
    /**
     * Find the difference between two strings
     * @param a
     * @param b
     * @return the Levenshtein distance between the two strings
     */
    public static int difference(String a, String b) {
        int[][] distance = new int[a.length() + 1][b.length() + 1];        
        
        for (int i = 0; i <= a.length(); i++)                                 
            distance[i][0] = i;                                                  
        for (int j = 1; j <= b.length(); j++)                                 
            distance[0][j] = j;                                                  
 
        for (int i = 1; i <= a.length(); i++)                                 
            for (int j = 1; j <= b.length(); j++)                             
                distance[i][j] =    Math.min(Math.min(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1),
                        distance[i - 1][j - 1] + ((a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1));
 
        return distance[a.length()][b.length()];
    }
    
    
    
}
