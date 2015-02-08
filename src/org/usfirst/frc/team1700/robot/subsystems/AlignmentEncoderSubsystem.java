package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The alignment potentiometer subsystem creates and gets readings from
 * a potentiometer attached to the tote aligner. Used to control how much
 * the tote aligner motors are commanded to move.
 */
public class AlignmentEncoderSubsystem extends Subsystem {
	private Encoder encoder;
	private static final int ENCODER_TICK_DEADBAND = 7;
	
	// TODO: find these values experimentally
	private int verticalValue = RobotMap.ALIGNER_VERTICLE_STATE; // encoder tick reading when tote aligner is vertical
	private int horizontalValue; // encoder tick reading when tote aligner is horizontal
	
	public AlignmentEncoderSubsystem(boolean isLong) {
		super();
    	if(isLong){
        	encoder = new Encoder(RobotMap.LONG_ALIGNER_ENCODER_A, RobotMap.LONG_ALIGNER_ENCODER_B);
        	horizontalValue = RobotMap.LONG_ALIGNER_HORIZONTAL_STATE;
    	} else {
    		encoder = new Encoder(RobotMap.SHORT_ALIGNER_ENCODER_A, RobotMap.SHORT_ALIGNER_ENCODER_B);
    		horizontalValue =  RobotMap.SHORT_ALIGNER_HORIZONTAL_STATE;
    	}
	}
	
	/**
	 * Returns whether the tote aligner is at or past the vertical position.
	 * @return
	 */
	public boolean isAlignerVertical (){
		return encoder.get() <= verticalValue;
	}
	
	/**
	 * Returns whether the tote aligner is at or past the horizontal position.
	 * @return
	 */
	public boolean isAlignerHorizontal (){
		return encoder.get() >= horizontalValue;
	}

	public void resetEncoder(){
		encoder.reset();
	}
	
	public int encoderValue(){
		return encoder.get();
	}
	
    public void initDefaultCommand() {
    	// no need to set default because command is already bound to a joystick button
    }
}

