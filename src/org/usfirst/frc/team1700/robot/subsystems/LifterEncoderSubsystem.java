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
	private double distanceToLevel1 = 1;
	private Encoder pulleyDetector = new Encoder(RobotMap.LIFTER_ENCODER_PORT_1, RobotMap.LIFTER_ENCODER_PORT_2, false, Encoder.EncodingType.k4X);
	
	public void resetEncoder() {
		pulleyDetector.reset();
	}
	
	public boolean isAtLevel1() {
		return (pulleyDetector.getDistance() >= distanceToLevel1);
	}
	public boolean isAtLevel0() {
		return (pulleyDetector.getDistance() <= 0);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
