package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeAlignerStateCommand extends Command {

    public ChangeAlignerStateCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.alignmentPotentiometerSubsystem);
    	requires(Subsystems.alignmentMotorsSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Subsystems.alignmentPotentiometerSubsystem.isAlignerVertical()) {
    		Subsystems.alignmentMotorsSubsystem.goForward();
    	}
    	else if(Subsystems.alignmentPotentiometerSubsystem.isAlignerHorizontal()) {
    		Subsystems.alignmentMotorsSubsystem.goBackward();
    	} else {
    		Subsystems.alignmentMotorsSubsystem.goBackward();
    	}
    	
    }
    	

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
