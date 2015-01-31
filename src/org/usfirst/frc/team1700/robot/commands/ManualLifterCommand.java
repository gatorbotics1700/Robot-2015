package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1700.robot.subsystems.LifterMotorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualLifterCommand extends Command {
	private LifterMotorSubsystem lifterMotor;
	private OI oi = Robot.oi;
	public static final double DEADBAND = 0.1;
	public static final double JOY_SCALE = 1/(1-DEADBAND);
	private static final double SCALE = 1;

    public ManualLifterCommand() {
    	super();
    	requires(Subsystems.lifterMotor);
    	lifterMotor = Subsystems.lifterMotor;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lifterMotor.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// carrot on a stick
    	lifterMotor.setTalons(deadband(oi.controlJoystick.getY()) * SCALE + lifterMotor.getPosition());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	lifterMotor.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	lifterMotor.disable();
    }
    
    private double deadband(double value) {
		return (value > DEADBAND || value < -DEADBAND) ? (value - DEADBAND)*JOY_SCALE : 0; // maps (0.1, 1) to (0,1)
	}
}
