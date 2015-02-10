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
	
	private static final double SCALE_DOWN = .8; // scale down factor
	private final double rotationalConstant = 1;
	private static final double DEADBAND = .15;
	private static final double JOY_SCALE = 1/(1-DEADBAND);
	private static final double STRAFE_FRONT_SCALE = 1;
	
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
    	double vx = deadband(oi.driveJoystick.getRawAxis(RobotMap.MOVE_X));
    	double wz = - deadband(oi.driveJoystick.getRawAxis(RobotMap.ROTATE_X));
//    	double vx = deadband(oi.driveJoystick.getRawAxis(RobotMap.ROTATE_X));
//    	double wz = 0;
    	if (Robot.oi.driveJoystick.getRawButton(RobotMap.DEBUGGING_BUTTON)) System.out.println("x:"+vx+"\ty:"+vy+"\tr:"+wz);
    	
    	double FL = (SCALE_DOWN)*(vy - STRAFE_FRONT_SCALE * vx - wz);
    	double FR = - (SCALE_DOWN)*(vy + STRAFE_FRONT_SCALE* vx + wz);
    	double BL = (SCALE_DOWN)*(vy + vx - wz);
    	double BR = - (SCALE_DOWN)*(vy - vx + wz);
    	
    	//System.out.println("FL"+ FL + "\tFR" + FR + "\tBL" + BL + "\tBR" + BR);	
    	
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
		double output = 0;
		if (value > DEADBAND || value < -DEADBAND) { // maps (0.1, 1) to (0,1)
			if (value > DEADBAND){
				output = (value - DEADBAND)*JOY_SCALE;
			} else {
				output = (value + DEADBAND)*JOY_SCALE;
			}
		} else { // outside of deadband
			output = 0;
		}
		
		return output;
	}
}
