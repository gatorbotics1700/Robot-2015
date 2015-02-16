package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Resets the tote aligners to vertical position in cases of emergency.
 */
public class ResetAlignerCommand extends Command {

    public ResetAlignerCommand() {
        requires(Subsystems.longAlignmentMotorsSubsystem);
        requires(Subsystems.shortAlignmentMotorsSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Subsystems.longAlignmentMotorsSubsystem.goToVertical();
    	Subsystems.shortAlignmentMotorsSubsystem.goToVertical();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Subsystems.longAlignmentMotorsSubsystem.stop();
    	Subsystems.shortAlignmentMotorsSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Subsystems.longAlignmentMotorsSubsystem.stop();
    	Subsystems.shortAlignmentMotorsSubsystem.stop();
    }
}
