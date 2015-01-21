package org.usfirst.frc.team4624.robot;

import org.usfirst.frc.team4624.robot.commands.Ice_Cream_Cone;
//import org.usfirst.frc.team4624.robot.commands.MovePlanetary;
import org.usfirst.frc.team4624.robot.input.XboxController;

public class OI {
    public XboxController xboxController    = new XboxController();
    
    public OI() {
        xboxController.a.whenPressed( new Ice_Cream_Cone( "Vanill_A_" ) );
        xboxController.rb.whenPressed(  new Ice_Cream_Cone( "_R_oot _B_eer float" ) );
        xboxController.leftStick.whenPressed(  new Ice_Cream_Cone( "Icecream truck" ) );
        xboxController.dPad.whenPressed(  new Ice_Cream_Cone( "Sherbert" ) );
        xboxController.dPad.right.whenPressed(  new Ice_Cream_Cone( "ICE CREAM PARTY!!!" ) );
    }
}
