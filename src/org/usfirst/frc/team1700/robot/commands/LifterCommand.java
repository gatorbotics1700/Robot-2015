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
    	if (Robot.oi.controlJoystick.getRawButton(8)) {
    		lifter.unsafeMove(-Robot.oi.controlJoystick.getY());//compensating for joystick orientation
    	} else {
    		lifter.safeMove(-Robot.oi.controlJoystick.getY());
    	}
    	
    	if (Robot.oi.controlJoystick.getRawButton(9)) {
    		lifter.zeroEncoders();
    	}
    	
//    	System.out.println(lifter.getPosition());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	lifter.safeMove(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	lifter.safeMove(0);
    }
}
