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
	private boolean isLong;
	private boolean debug = true;
	private double vertical = RobotMap.ALIGNER_VERTICAL_STATE + 20; // try to go a little further
	private double horizontal;
	
	/**
	 * Initializes the two Talons for the appropriate gearbox (either the long
	 * width or the short width tote aligner) based on the parameter isLong.
	 * @param isLong - boolean that decides which Talon pair to initialize
	 */
	public AlignmentMotorsSubsystem(boolean isLong){
		super();
		this.isLong = isLong;
    	if(isLong){
        	alignmentTalon1 = initTalon(RobotMap.LONG_ALIGNMENT_TALON_1_ID);
        	alignmentTalon2 = initTalon(RobotMap.LONG_ALIGNMENT_TALON_2_ID);
        	horizontal = RobotMap.LONG_ALIGNER_HORIZONTAL_STATE;
    	} else {
    		alignmentTalon1 = initTalon(RobotMap.SHORT_ALIGNMENT_TALON_1_ID);
        	alignmentTalon2 = initTalon(RobotMap.SHORT_ALIGNMENT_TALON_2_ID);
        	horizontal = RobotMap.SHORT_ALIGNER_HORIZONTAL_STATE;
    	}
    	horizontal -= 20; // try to go a little further
	}
	
	/**
	 * Moves motors forward at a constant speed.
	 * (NOTE: in the tote aligner gearboxes (Vex VersaPlanetary Gearbox) the two motors
	 * should move in the same direction.) 
	 */
	public void goToVertical() {
		if (isLong) System.out.println("going to vertical " + getPosition());
		alignmentTalon1.set(vertical);
		alignmentTalon2.set(vertical);
		System.out.println("setpoint: " + alignmentTalon1.getSetpoint());
	}
	
	/**
	 * Moves motors backward at a constant speed.
	 */
	public void goToHorizontal() {
		if (isLong) System.out.println("going to horizontal " + getPosition());
		alignmentTalon1.set(horizontal);
		alignmentTalon2.set(horizontal);
		System.out.println("setpoint: " + alignmentTalon1.getSetpoint());
	}
	
	public boolean isVertical(){
		return (alignmentTalon1.getPosition() >= RobotMap.ALIGNER_VERTICAL_STATE);
	}
	
	public boolean isHorizontal(){
		return (alignmentTalon1.getPosition() <= horizontal + 20);
	}
	
	public void stop(){
		alignmentTalon1.set(alignmentTalon1.getPosition());
		alignmentTalon2.set(alignmentTalon2.getPosition());
	}
	
	public double getPosition(){
		return alignmentTalon1.getPosition();
	}
	
	public void zeroEncoder() {
		alignmentTalon1.setPosition(0);
		alignmentTalon2.setPosition(0);
		System.out.println(alignmentTalon1.getPosition());
	}
	// -12, -60
	// long, short
	// 
	private CANTalon initTalon(int ID) {
    	CANTalon talon = new CANTalon(ID);
    	
    	talon.disableControl(); // disable before set up
    	talon.changeControlMode(CANTalon.ControlMode.Position);
    	talon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder); // set input device
    	talon.set(talon.getPosition());
    	talon.enableControl();
    	if (debug) talon.setPosition(0);
    	talon.setPID(0.2,0,0);
    	//talon.setF(1);
    	    	
    	return talon;
    }
	
    public void initDefaultCommand() {
        // no need to set default because command is already bound to a joystick button
    }
}

