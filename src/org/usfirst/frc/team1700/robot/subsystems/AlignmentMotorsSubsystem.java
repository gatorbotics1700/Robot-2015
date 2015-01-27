package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The alignment motors subsystem creates and controls the two motors of
 * one of the tote aligner gearboxes. Used in the ChangeAlignerState command
 * to move up or down at a constant speed.
 */
public class AlignmentMotorsSubsystem extends Subsystem {
	private CANTalon alignmentTalon1; 
	private CANTalon alignmentTalon2;
	
	private static final double TALON_SPEED = 0.2;
	
	/**
	 * Initializes the two Talons for the appropriate gearbox (either the long
	 * width or the short width tote aligner) based on the parameter isLong.
	 * @param isLong - boolean that decides which Talon pair to initialize
	 */
	public AlignmentMotorsSubsystem(boolean isLong){
		super();
		
    	if(isLong){
        	alignmentTalon1 = new CANTalon(RobotMap.LONG_ALIGNMENT_TALON_1_ID);
        	alignmentTalon2 = new CANTalon(RobotMap.LONG_ALIGNMENT_TALON_2_ID);
    	} else {
    		alignmentTalon1 = new CANTalon(RobotMap.SHORT_ALIGNMENT_TALON_1_ID);
        	alignmentTalon2 = new CANTalon(RobotMap.SHORT_ALIGNMENT_TALON_2_ID);
    	}
	}
	
	/**
	 * Moves motors forward at a constant speed.
	 * (NOTE: in the tote aligner gearboxes (Vex VersaPlanetary Gearbox) the two motors
	 * should move in the same direction.) 
	 */
	public void goForward() {
		alignmentTalon1.set(TALON_SPEED);
		alignmentTalon2.set(TALON_SPEED);
	}
	
	/**
	 * Moves motors backward at a constant speed.
	 */
	public void goBackward() {
		alignmentTalon1.set(-TALON_SPEED);
		alignmentTalon2.set(-TALON_SPEED);
		
	}
	
	/**
	 * Stops motors.
	 */
	public void stop() {
		alignmentTalon1.set(0);
		alignmentTalon2.set(0);
	}
	
    public void initDefaultCommand() {
        // no need to set default because command is already bound to a joystick button
    }
}

