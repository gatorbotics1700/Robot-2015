package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.LifterMotorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualLifterCommand extends Command {
	private LifterMotorSubsystem lifter;
	public static final double DEADBAND = 0.1;
	public static final double JOY_SCALE = 1/(1-DEADBAND);
	private static final double SCALE = 10000;

    public ManualLifterCommand() {
    	super();
    	requires(Subsystems.lifter);
    	lifter = Subsystems.lifter;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lifter.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// carrot on a stick;
    	if (Robot.oi.controlJoystick.getRawButton(RobotMap.LIFTER_UNSAFE_MOVE_BUTTON)) {
    		lifter.uncheckedMove(-Robot.oi.controlJoystick.getY() * SCALE + lifter.getPosition()); //compensating for joystick orientation  
    		System.out.println(lifter.getPosition());
    	} else {
    		lifter.safeMove(-Robot.oi.controlJoystick.getY() * SCALE + lifter.getPosition());
    	}
    	
    	if (Robot.oi.controlJoystick.getRawButton(RobotMap.ZERO_LIFTER_BUTTON)) {
    		lifter.zeroEncoders();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	lifter.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	lifter.stop();
    }

}
