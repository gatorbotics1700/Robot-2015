package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeAlignerStateCommand extends Command {
	
	private boolean wasVertical = false;
	private boolean wasHorizontal = false;
		
	
    public ChangeAlignerStateCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.alignmentPotentiometerSubsystem);
    	requires(Subsystems.alignmentMotorsSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Subsystems.alignmentPotentiometerSubsystem.isAlignerVertical()) {
    		wasVertical = true;
    	}
    	else if(Subsystems.alignmentPotentiometerSubsystem.isAlignerHorizontal()) {
    		wasHorizontal = true;
    	} 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(wasVertical == true) {
    		Subsystems.alignmentMotorsSubsystem.goForward();
    	}
    	else if(wasHorizontal == true) {
    		Subsystems.alignmentMotorsSubsystem.goBackward();
    	} else {
    		wasHorizontal = true;
    		Subsystems.alignmentMotorsSubsystem.goBackward();
    	}
    }
    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(wasVertical && Subsystems.alignmentPotentiometerSubsystem.isAlignerHorizontal()) {
    		wasHorizontal = true;
    		wasVertical = false; 
    		return true;
    	} else if (wasHorizontal && Subsystems.alignmentPotentiometerSubsystem.isAlignerVertical()) {
    		wasVertical = true;
    		wasHorizontal = false;
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
