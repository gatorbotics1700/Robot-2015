package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousLiftCommand extends Command {

    public AutonomousLiftCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.lifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Subsystems.lifter.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Subsystems.lifter.safeMove(RobotMap.LEVEL_1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Subsystems.lifter.getPosition() - RobotMap.LEVEL_1) < 1000; // check deadband
    }

    // Called once after isFinished returns true
    protected void end() {
    	Subsystems.lifter.stop();
    	System.out.println("Finished Lifting ---------------");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Subsystems.lifter.stop();
    }
}
