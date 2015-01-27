package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The lifter encoder subsystem gets readings and processes current lifter level
 * based on encoder readings. Used in LifterToLevel command and ManualLifter command.
 */
public class LifterEncoderSubsystem extends Subsystem {
	private int currentLevel = 0; // current level
	// possible lifter levels
	private static final int LEVEL_0 = 0;
	private static final int LEVEL_1 = 1;
	private static final int LEVEL_2 = 2;
	private static final int LEVEL_3 = 3;

	private Encoder lifterEncoder;
	
	public LifterEncoderSubsystem() {
		super();
		lifterEncoder = new Encoder(RobotMap.LIFTER_ENCODER_PORT_1, RobotMap.LIFTER_ENCODER_PORT_2, 
								    false, Encoder.EncodingType.k4X);
	}
	
	/**
	 * Resets encoder readings back to 0. Called every time lifter reaches the
	 * bottom of its track.
	 */
	public void resetEncoder() {
		lifterEncoder.reset();
	}
	
	/**
	 * Sets currentLevel instance variable.
	 * @param level
	 */
	public void setCurrentLevel(int level){
		currentLevel = level;
	}
	
	public int level0() {
		return LEVEL_0;
	}
	
	public int currentLevel(){
		return currentLevel;
	}
	
	public boolean isAtLevel(int level){
		int targetLevel;
		switch(level){
			case 0: 
				targetLevel = LEVEL_0;
				break;
			case 1: 
				targetLevel = LEVEL_1;
				break;
			case 2:
				targetLevel = LEVEL_2;
				break;
			case 3:
				targetLevel = LEVEL_3;
				break;
			default:
				targetLevel = LEVEL_0;
				break;
		}
		
		return (lifterEncoder.getDistance() >= targetLevel);
	}
	
    public void initDefaultCommand() {
    	// no need to set default because command is already bound to a joystick button
    }
}
