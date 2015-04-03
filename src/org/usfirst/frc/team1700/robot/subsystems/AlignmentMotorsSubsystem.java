package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The alignment motors subsystem creates and controls the two motors of
 * one of the tote aligner gearboxes. Used in the ChangeAlignerState command
 * to move up or down, and in the ResetAligner command.
 */
public class AlignmentMotorsSubsystem extends Subsystem {
	private CANTalon alignmentTalon;
	private double verticalSetpoint;
	private double horizontalSetpoint;
	
	/**
	 * Initializes the two Talons for the appropriate gearbox (either the long
	 * width or the short width tote aligner) based on the parameter isLong.
	 * @param isLong - boolean that decides which Talon pair to initialize
	 */
	public AlignmentMotorsSubsystem(boolean isLong){
		super();
    	if(isLong){
        	alignmentTalon = initTalon(RobotMap.LONG_ALIGNMENT_TALON_1_ID);
        	horizontalSetpoint = RobotMap.LONG_ALIGNER_HORIZONTAL_STATE;
        	verticalSetpoint = RobotMap.LONG_ALIGNER_VERTICAL_STATE;
    	} else {
    		alignmentTalon = initTalon(RobotMap.SHORT_ALIGNMENT_TALON_1_ID);
        	horizontalSetpoint = RobotMap.SHORT_ALIGNER_HORIZONTAL_STATE;
        	verticalSetpoint = RobotMap.SHORT_ALIGNER_VERTICAL_STATE;
    	}
	}

	public void goToVertical() {
		System.out.println("going vertical");
		System.out.println("position:\t" + alignmentTalon.getPosition());
		System.out.println("voltage:\t" + alignmentTalon.getOutputVoltage());
		System.out.println("error:\t" + alignmentTalon.getClosedLoopError());
		System.out.println("i value:\t" + alignmentTalon.GetIaccum());
		alignmentTalon.set(verticalSetpoint);
	}
	
	public void goToHorizontal() {
		System.out.println("going horizontal");
		System.out.println("position:\t" + alignmentTalon.getPosition());
		System.out.println("voltage:\t" + alignmentTalon.getOutputVoltage());
		System.out.println("error:\t" + alignmentTalon.getClosedLoopError());
		System.out.println("i value:\t" + alignmentTalon.GetIaccum());
		alignmentTalon.set(horizontalSetpoint);
	}
	
	public boolean isVertical(){
		return (alignmentTalon.getPosition() >= verticalSetpoint);
	}
	
	public boolean isHorizontal(){
		return (alignmentTalon.getPosition() <= horizontalSetpoint);
	}
	
	// sets the aligners' setpoints to their current position
	public void stop() {
		alignmentTalon.set(alignmentTalon.getPosition());
		
	}
	
	public double getPosition() {
		return alignmentTalon.getPosition();
	}
	
	public void zeroEncoder() {
		alignmentTalon.setPosition(0);
	}
	
	private CANTalon initTalon(int ID) {
    	CANTalon talon = new CANTalon(ID);
    	
    	talon.disableControl();
    	talon.changeControlMode(CANTalon.ControlMode.Position);
    	talon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	talon.enableControl();
    	talon.setPosition(0);
    	talon.set(talon.getPosition());
    	talon.setPID(0.2,0,0);
    	    	
    	return talon;
    }
	
	public void clearIAccumulator (){
		alignmentTalon.ClearIaccum();
	}
	
	public double getAccumulation () {
		return alignmentTalon.GetIaccum();
	}
	
    public void initDefaultCommand() {
        // no need to set default because command is already bound to a joystick button
    }
}

