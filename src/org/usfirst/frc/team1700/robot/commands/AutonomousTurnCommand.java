package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousTurnCommand extends Command {

    public AutonomousTurnCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Subsystems.drive.autonomousTurn(0.2); //very low because not scaled down, also a guess
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isQuarterTurn(); // isHalfTurn();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private boolean isHalfTurn() {
    	return Subsystems.drive.getPosition() > RobotMap.AUTONOMOUS_HALF_TURN_DIST;
    }
    
    private boolean isQuarterTurn(){
    	return Subsystems.drive.getPosition() > RobotMap.AUTONOMOUS_QUARTER_TURN_DIST;
    }
}
