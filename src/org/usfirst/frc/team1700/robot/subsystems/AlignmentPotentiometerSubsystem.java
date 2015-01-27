package org.usfirst.frc.team1700.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The alignment potentiometer subsystem creates and gets readings from
 * a potentiometer attached to the tote aligner. Used to control how much
 * the tote aligner motors are commanded to move.
 */
public class AlignmentPotentiometerSubsystem extends Subsystem {
	private AnalogPotentiometer pot = new AnalogPotentiometer(1);
	
	// TODO: find these values experimentally
	private double verticalValue = 1; // pot reading when tote aligner is vertical
	private double horizontalValue = 0; // pot reading when tote aligner is horizontal
	
	public AlignmentPotentiometerSubsystem() {
		super();
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

