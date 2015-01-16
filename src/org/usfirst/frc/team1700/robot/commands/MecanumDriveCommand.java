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
	
	private final double wheelRadius = 0;
	private final double L1 = 0;
	private final double L2 = 0;
	private final double rotationalConstant = L1 + L2;
	
	private DriveSubsystem driveSubsystem;
	private OI oi;
	
    public MecanumDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Subsystems.driveSubsystem);
    	this.driveSubsystem = Subsystems.driveSubsystem;
    	this.oi = Robot.oi;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double vy = oi.driveJoystick.getRawAxis(RobotMap.MOVE_Y);
    	double vx = oi.driveJoystick.getRawAxis(RobotMap.MOVE_X);
    	double wz = oi.driveJoystick.getRawAxis(RobotMap.ROTATE_X);
    	
    	double FL = vy + vx - rotationalConstant * wz;
    	double FR = vy - vx + rotationalConstant * wz;
    	double BL = vy - vx - rotationalConstant * wz;
    	double BR = vy + vx + rotationalConstant * wz;
    	
    	driveSubsystem.move(FR, FL, BR, BL);;

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
}
