package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.AlignmentMotorsSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.AlignmentEncoderSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command toggles the state of the desired tote aligner between 
 * vertical and horizontal when the appropriate button is pressed.
 */
public class ChangeAlignerStateCommand extends Command {
	// remember previous state of tote aligner
	private boolean wasVertical = false;
	private boolean wasHorizontal = false;
	private AlignmentMotorsSubsystem motors;
	private AlignmentEncoderSubsystem encoder;
	
	/**
	 * Requires the appropriate tote aligner motor subsystem (as decided by
	 * the isLong boolean argument) and the potentiometer subsystem.
	 * @param isLong
	 */
    public ChangeAlignerStateCommand(boolean isLong) {
    	if(isLong) { // choose which motor subsystem
        	motors = Subsystems.longAlignmentMotorsSubsystem;
        	encoder = Subsystems.longAlignmentEncoderSubystem;
    	} else {
    		motors = Subsystems.shortAlignmentMotorsSubsystem;
    		encoder = Subsystems.shortAlignmentEncoderSubsystem;
    	}
		requires(motors);
    	requires(encoder);
    }

    // Called just before this Command runs the first time
    /**
     * Checks and stores initial state of tote aligner.
     */
    protected void initialize() {
    	if(encoder.isAlignerVertical()) {
    		wasVertical = true;
    	}
    	else if(encoder.isAlignerHorizontal()) {
    		wasHorizontal = true;
    	} 
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Called when operator presses button to toggle tote aligner state.
     */
    protected void execute() {
    	if(wasVertical == true) {
    		motors.goForward();
    	} else if(wasHorizontal == true) {
    		motors.goBackward();
    	} else { 
    		// default position = vertical (if tote aligner started in the middle or something went wrong)
    		wasHorizontal = true;
    		motors.goBackward();
    	}
//    	System.out.println("Encoder: " + encoder.encoderValue());
//    	System.out.println("Encoder verticle: " + encoder.isAlignerVertical());
//    	System.out.println("Encoder horizontal: " + encoder.isAlignerHorizontal());
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Command is complete when tote aligner has reached its desired position.
     * Before execution stops, updates tote aligner's stored state based on its 
     * new position.
     */
    protected boolean isFinished() {
    	if(wasVertical && encoder.isAlignerHorizontal()) {
    		wasHorizontal = true;
    		wasVertical = false; 
    		return true;
    	} else if (wasHorizontal && encoder.isAlignerVertical()) {
    		wasVertical = true;
    		wasHorizontal = false;
    		encoder.resetEncoder(); // set back to zero
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	motors.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	motors.stop();
    }
}
