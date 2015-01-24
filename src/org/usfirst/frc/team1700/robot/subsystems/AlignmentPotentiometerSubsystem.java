package org.usfirst.frc.team1700.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AlignmentPotentiometerSubsystem extends Subsystem {
    
	private AnalogPotentiometer pot = new AnalogPotentiometer(1);
	
	private double verticalValue = 1;
	
	private double horizontalValue = 0;
	
	public boolean isAlignerVertical (){
		return pot.get()>=verticalValue;
			
	}
	
	public boolean isAlignerHorizontal (){
		return pot.get()<=horizontalValue;
	}	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

