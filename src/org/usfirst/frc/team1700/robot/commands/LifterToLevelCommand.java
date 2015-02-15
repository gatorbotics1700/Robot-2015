package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.LifterMotorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LifterToLevelCommand extends Command {
	private double targetLevel;
	private double currentLevel;
	private LifterMotorSubsystem lifter;
	
    public LifterToLevelCommand(int level) {
    	requires(Subsystems.lifter);
    	lifter = Subsystems.lifter;
//    	requires(Subsystems.lifterEncoder);
//    	requires(Subsystems.lifterLimitSwitch);
    	targetLevel = level;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lifter.enable(); // enable and set setpoint to current position
    	currentLevel = lifter.getPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentLevel = lifter.getPosition();
    	lifter.safeMove(targetLevel);
    	System.out.println("Position: " + currentLevel);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean done = (Math.abs(currentLevel - targetLevel) < 100); // maybe even bigger deadband?
    	//System.out.println(" DONE " + done);
    	return done;
    	// TODO: check tick deadband and change as needed
    }

    // Called once after isFinished returns true
    protected void end() {
    	lifter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	lifter.stop();
    }
}
