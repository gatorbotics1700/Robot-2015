package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LifterToLevel extends Command {

	private int targetLevel;
	private int currentLevel;
	private boolean goingUp;
	
    public LifterToLevel(int level) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.lifterMotor);
    	requires(Subsystems.lifterEncoder);
    	requires(Subsystems.lifterLimitSwitch);
    	targetLevel = level;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	currentLevel = Subsystems.lifterEncoder.currentLevel();
    	goingUp = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(targetLevel < currentLevel) {
    		//Move down
    		Subsystems.lifterMotor.pulleyDown();
    	} else if(targetLevel > currentLevel) {
    		//Move up
    		goingUp = true;
    		Subsystems.lifterMotor.pulleyUp();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (goingUp && targetLevel >= currentLevel){
        	return true;
        } else if (!goingUp && targetLevel <= currentLevel) {
        	return true;
        } else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Subsystems.lifterEncoder.setCurrentLevel(targetLevel);
    	if(Subsystems.lifterEncoder.currentLevel() == Subsystems.lifterEncoder.level0()){
    		Subsystems.lifterEncoder.resetEncoder();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Subsystems.lifterMotor.pulleyStop();
    }
}
