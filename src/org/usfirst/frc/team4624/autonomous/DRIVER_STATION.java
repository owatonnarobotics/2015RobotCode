package org.usfirst.frc.team4624.autonomous;

public enum DRIVER_STATION {
    LEFT    (1),
    CENTER  (2),
    RIGHT   (3);
    
    private int value;
    
    DRIVER_STATION(int value) {
        this.value = value;
    }
    
    public int getInt() {
        return this.value;
    }
    
    public DRIVER_STATION getFromInt(int input) {
        DRIVER_STATION[] enums = DRIVER_STATION.values();
        for (int i = 0; i <= enums.length; i++) {
            if( input == enums[i].getInt() ) {
                return enums[i];
            }
        }
        System.out.println( "DRIVER_STATION.getFromInt() got the invalid interger " + input + "! Returning CENTER" );
        return CENTER;
    }
    
}
