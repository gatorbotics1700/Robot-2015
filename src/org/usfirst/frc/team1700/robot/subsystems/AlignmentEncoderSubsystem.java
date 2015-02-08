package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The alignment potentiometer subsystem creates and gets readings from
 * a potentiometer attached to the tote aligner. Used to control how much
 * the tote aligner motors are commanded to move.
 */
public class AlignmentPotentiometerSubsystem extends Subsystem {
	private AnalogPotentiometer pot;
	
	// TODO: find these values experimentally
	private double verticalValue = 1; // pot reading when tote aligner is vertical
	private double horizontalValue = 0; // pot reading when tote aligner is horizontal
	
	public AlignmentPotentiometerSubsystem(boolean isLong) {
		super();
    	if(isLong){
        	pot = new AnalogPotentiometer(RobotMap.LONG_ALIGNER_POT);
    	} else {
    		pot = new AnalogPotentiometer(RobotMap.SHORT_ALIGNER_POT);
    	}
	}
	
	/**
	 * Returns whether the tote aligner is at or past the vertical position.
	 * @return
	 */
	public boolean isAlignerVertical (){
		return pot.get() >= verticalValue;
	}
	
	/**
	 * Returns whether the tote aligner is at or past the horizontal position.
	 * @return
	 */
	public boolean isAlignerHorizontal (){
		return pot.get() <= horizontalValue;
	}

    public void initDefaultCommand() {
    	// no need to set default because command is already bound to a joystick button
    }
}

