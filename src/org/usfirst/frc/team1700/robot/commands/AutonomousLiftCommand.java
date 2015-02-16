package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Lifts up yellow tote during autonomous.
 */
public class AutonomousLiftCommand extends Command {
	private static final int AUTO_HEIGHT = 20000;
	private static final int HEIGHT_DEADBAND = 1500;

    public AutonomousLiftCommand() {
    	requires(Subsystems.lifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Subsystems.lifter.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Subsystems.lifter.safeMove(AUTO_HEIGHT);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Subsystems.lifter.getPosition() - AUTO_HEIGHT) < HEIGHT_DEADBAND;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Subsystems.lifter.stop();
    }

    // Called when another command which requires one or more of the same subsystems is scheduled to run
    protected void interrupted() {
    	Subsystems.lifter.stop();
    }
}
