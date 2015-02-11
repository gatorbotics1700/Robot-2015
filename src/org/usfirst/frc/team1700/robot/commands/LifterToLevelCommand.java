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
    	requires(Subsystems.lifterMotor);
    	lifter = Subsystems.lifterMotor;
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
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Math.abs(currentLevel - targetLevel) < 5); // deadband of 5 ticks
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
