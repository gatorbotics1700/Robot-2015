package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterEncoderSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private int currentLevel = 0; // picking up totes level
	private int level0 = 0;
	private int level1 = 1;
	private int level2 = 2;
	private int level3 = 3;

	private Encoder lifterEncoder = new Encoder(RobotMap.LIFTER_ENCODER_PORT_1, RobotMap.LIFTER_ENCODER_PORT_2, false, Encoder.EncodingType.k4X);
	
	public void resetEncoder() {
		lifterEncoder.reset();
	}
	
	public void setCurrentLevel(int level){
		currentLevel = level;
	}
	
	public int level0(){
		return level0;
	}
	
	public int currentLevel(){
		return currentLevel;
	}
	
	public boolean isAtLevel(int level){
		int targetLevel;
		switch(level){
			case 0: 
				targetLevel = level0;
				break;
			case 1: 
				targetLevel = level1;
				break;
			case 2:
				targetLevel = level2;
				break;
			case 3:
				targetLevel = level3;
				break;
			default:
				targetLevel = level0;
				break;
		}
		
		return (lifterEncoder.getDistance() >= targetLevel);
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
