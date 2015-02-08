package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.LifterSubsystemTest;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LifterCommand extends Command {
	private LifterSubsystemTest lifter;

    public LifterCommand() {
        requires(Subsystems.lifterTest);
        lifter = Subsystems.lifterTest;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	lifter.move(Robot.oi.controlJoystick.getY());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	lifter.move(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	lifter.move(0);
    }
}
