package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MecanumDriveCommand extends Command {
	
//	private final double wheelRadius = 4; // inches
	private final double wheelRadius = 1;
	private final double L1 = 14.5;
	private final double L2 = 15.5; //inches
//	private final double rotationalConstant = L1 + L2;
	private final double rotationalConstant = 1;
	private static final double DEADBAND = .15;
	private static final double JOY_SCALE = 1/(1-DEADBAND);

	
	private DriveSubsystem driveSubsystem;
	private OI oi;
	
    public MecanumDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.drive);
    	this.driveSubsystem = Subsystems.drive;
    	this.oi = Robot.oi;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double vy = - deadband(oi.driveJoystick.getRawAxis(RobotMap.MOVE_Y));
    	double vx = 2 * deadband(oi.driveJoystick.getRawAxis(RobotMap.MOVE_X));
    	double wz = -2 * deadband(oi.driveJoystick.getRawAxis(RobotMap.ROTATE_X));
    	if (Robot.oi.driveJoystick.getRawButton(RobotMap.DEBUGGING_BUTTON)) System.out.println("x:"+vx+"\ty:"+vy+"\tr:"+wz);
    	
    	double FL = (1/wheelRadius)*(vy + vx - rotationalConstant * wz);
    	double FR = - (1/wheelRadius)*(vy - vx + rotationalConstant * wz);
    	double BL = (1/wheelRadius)*(vy - vx - rotationalConstant * wz);
    	double BR = - (1/wheelRadius)*(vy + vx + rotationalConstant * wz);
    	
    	driveSubsystem.move(FR, FL, BR, BL);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	driveSubsystem.stop();
    }
    
    private double deadband(double value) {
		return (value > DEADBAND || value < -DEADBAND) ? (value - DEADBAND)*JOY_SCALE : 0; // maps (0.1, 1) to (0,1)
	}
}
