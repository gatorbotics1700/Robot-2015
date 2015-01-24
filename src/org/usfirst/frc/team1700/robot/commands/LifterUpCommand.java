package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LifterUpCommand extends Command {

    public LifterUpCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.lifterMotor);
    	requires(Subsystems.lifterEncoder);
    	requires(Subsystems.lifterLimitSwitch);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Subsystems.lifterMotor.pulleyUp();	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Subsystems.lifterEncoder.isAtLevel1() || Subsystems.lifterLimitSwitch.wasTopSwitchHit();
 
    }

    // Called once after isFinished returns true
    protected void end() {
    	Subsystems.lifterMotor.pulleyStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Subsystems.lifterMotor.pulleyStop();
    }
}
