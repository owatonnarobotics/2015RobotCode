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
    
    
    
    /**
     * Find the difference between two values
     * 
     * @param a
     * @param b
     * @return difference
     */
    public static byte difference(byte a, byte b) {
    
        return (byte) Math.abs(a - b);
    }
    
    /**
     * Find the difference between two values
     * 
     * @param a
     * @param b
     * @return difference
     */
    public static double difference(double a, double b) {
    
        return Math.abs(a - b);
    }
    
    /**
     * Find the difference between two values
     * 
     * @param a
     * @param b
     * @return difference
     */
    public static float difference(float a, float b) {
    
        return Math.abs(a - b);
    }
    
    /**
     * Find the difference between two values
     * 
     * @param a
     * @param b
     * @return difference
     */
    public static int difference(int a, int b) {
    
        return Math.abs(a - b);
    }
    
    /**
     * Find the difference between two values
     * 
     * @param a
     * @param b
     * @return difference
     */
    public static long difference(long a, long b) {
    
        return Math.abs(a - b);
    }
    
    /**
     * Find the difference between two values
     * 
     * @param a
     * @param b
     * @return difference
     */
    public static short difference(short a, short b) {
    
        return (short) Math.abs(a - b);
    }
    
    /**
     * Find the difference between two strings
     * 
     * @param a
     * @param b
     * @return the Levenshtein distance between the two strings
     */
    public static int difference(String s0, String s1) {
    
        int len0 = s0.length() + 1;
        int len1 = s1.length() + 1;
        
        // the array of distances                                                       
        int[] cost = new int[len0];
        int[] newcost = new int[len0];
        
        // initial cost of skipping prefix in String s0                                 
        for (int i = 0; i < len0; i++)
            cost[i] = i;
        
        // dynamically computing the array of distances                                  
        
        // transformation cost for each letter in s1                                    
        for (int j = 1; j < len1; j++) {
            // initial cost of skipping prefix in String s1                             
            newcost[0] = j;
            
            // transformation cost for each letter in s0                                
            for (int i = 1; i < len0; i++) {
                // matching current letters in both strings                             
                int match = (s0.charAt(i - 1) == s1.charAt(j - 1)) ? 0 : 1;
                
                // computing cost for each transformation                               
                int cost_replace = cost[i - 1] + match;
                int cost_insert = cost[i] + 1;
                int cost_delete = newcost[i - 1] + 1;
                
                // keep minimum cost                                                    
                newcost[i] = Math.min(Math.min(cost_insert, cost_delete),
                        cost_replace);
            }
            
            // swap cost/newcost arrays                                                 
            int[] swap = cost;
            cost = newcost;
            newcost = swap;
        }
        
        // the distance is the cost for transforming all letters in both strings        
        return cost[len0 - 1];
    }
    
    
    
}
