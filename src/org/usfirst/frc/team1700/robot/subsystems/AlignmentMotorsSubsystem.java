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
	private CANTalon alignmentTalon1, alignmentTalon2;
	private static int ALIGNER_SETPOINT_OFFSET = 20;
	private double verticalSetpoint = RobotMap.ALIGNER_VERTICAL_STATE + ALIGNER_SETPOINT_OFFSET;
	private double horizontalSetpoint;
	
	/**
	 * Initializes the two Talons for the appropriate gearbox (either the long
	 * width or the short width tote aligner) based on the parameter isLong.
	 * @param isLong - boolean that decides which Talon pair to initialize
	 */
	public AlignmentMotorsSubsystem(boolean isLong){
		super();
    	if(isLong){
        	alignmentTalon1 = initTalon(RobotMap.LONG_ALIGNMENT_TALON_1_ID);
        	alignmentTalon2 = initTalon(RobotMap.LONG_ALIGNMENT_TALON_2_ID);
        	horizontalSetpoint = RobotMap.LONG_ALIGNER_HORIZONTAL_STATE;
    	} else {
    		alignmentTalon1 = initTalon(RobotMap.SHORT_ALIGNMENT_TALON_1_ID);
        	alignmentTalon2 = initTalon(RobotMap.SHORT_ALIGNMENT_TALON_2_ID);
        	horizontalSetpoint = RobotMap.SHORT_ALIGNER_HORIZONTAL_STATE;
    	}
    	horizontalSetpoint -= ALIGNER_SETPOINT_OFFSET; 
	}

	public void goToVertical() {
		alignmentTalon1.set(verticalSetpoint);
		alignmentTalon2.set(verticalSetpoint);
	}
	
	public void goToHorizontal() {
		alignmentTalon1.set(horizontalSetpoint);
		alignmentTalon2.set(horizontalSetpoint);
	}
	
	public boolean isVertical(){
		return (alignmentTalon1.getPosition() >= RobotMap.ALIGNER_VERTICAL_STATE);
	}
	
	public boolean isHorizontal(){
		return (alignmentTalon1.getPosition() <= horizontalSetpoint + ALIGNER_SETPOINT_OFFSET);
	}
	
	// sets the aligners' setpoints to their current position
	public void stop() {
		alignmentTalon1.set(alignmentTalon1.getPosition());
		alignmentTalon2.set(alignmentTalon2.getPosition());
	}
	
	public double getPosition() {
		return alignmentTalon1.getPosition();
	}
	
	public void zeroEncoder() {
		alignmentTalon1.setPosition(0);
		alignmentTalon2.setPosition(0);
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
	
    public void initDefaultCommand() {
        // no need to set default because command is already bound to a joystick button
    }
}

