package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives forward a certain distance during autonomous. Can be changed to go forward
 * or backward based on the state of a hardware switch on the robot.
 */
public class AutonomousDriveCommand extends Command {
	private DigitalInput autoSwitch;
	private boolean goingForward;
	private int direction, distance; // determined by state of auto switch

    public AutonomousDriveCommand() {
    	requires(Subsystems.drive);
    	autoSwitch = new DigitalInput(RobotMap.AUTONOMOUS_SWITCH);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	goingForward = autoSwitch.get();
    	Subsystems.drive.zeroEncoders();
    	direction = (goingForward) ? 1 : -1;
    	distance = (goingForward) ? RobotMap.AUTONOMOUS_FORWARD_FLAT_DISTANCE : RobotMap.AUTONOMOUS_BACKWARD_FLAT_DISTANCE;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Subsystems.drive.autonomousMove(direction * RobotMap.AUTONOMOUS_DRIVE_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean done;
    	if (goingForward) {
    		done = Subsystems.drive.getPosition() >= distance;
    	} else {
    		done = Subsystems.drive.getPosition() <= distance;
    	}
    	return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Subsystems.drive.stop();
    }

    // Called when another command which requires one or more of the same subsystems is scheduled to run
    protected void interrupted() {
    	Subsystems.drive.stop();
    }
}
