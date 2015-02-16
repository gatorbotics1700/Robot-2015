package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.OI;
import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Manual driving command for mecanum drive with Xbox controller. Right joystick 
 * controls translation, while left joystick controls rotation. Also contains
 * precision move modes: trigger strafing, and POV moving.
 */
public class MecanumDriveCommand extends Command {
	
	private static final double SCALE_DOWN = .8; // scale down factor
	private static final double JOY_DEADBAND = .15;
	private static final double JOY_SCALE = 1/(1-JOY_DEADBAND);
	private static final double PRECISION_STRAFE_SCALE_DOWN = .3;
	private static final double POV_MOVE_SPEED = .2;
	
	private static final int POV_NONE = -1, 
							 POV_FRONT = 0, 
							 POV_BACK = 180, 
							 POV_RIGHT = 90, 
							 POV_LEFT = 270;
	
	private DriveSubsystem driveSubsystem;
	private OI oi;
	
    public MecanumDriveCommand() {
    	requires(Subsystems.drive);
    	this.driveSubsystem = Subsystems.drive;
    	this.oi = Robot.oi;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// get values from joystick for processing
    	double precisionStrafeRight = deadband(oi.driveJoystick.getRawAxis(RobotMap.STRAFE_RIGHT));
    	double precisionStrafeLeft = -1 * deadband(oi.driveJoystick.getRawAxis(RobotMap.STRAFE_LEFT));
    	double vy = - deadband(oi.driveJoystick.getRawAxis(RobotMap.MOVE_Y));
    	double vx = deadband(oi.driveJoystick.getRawAxis(RobotMap.MOVE_X));
    	double wz = - deadband(oi.driveJoystick.getRawAxis(RobotMap.ROTATE_X));
    	int POV = oi.driveJoystick.getPOV();
    	double FL = 0, FR = 0, BL = 0, BR = 0;
    	
    	// use joystick values to calculate drive commands
    	if (precisionStrafeRight <= JOY_DEADBAND && precisionStrafeLeft >= -JOY_DEADBAND && POV == POV_NONE) { // normal drive
	    	FL = (SCALE_DOWN)*(vy - vx - wz);
	    	FR = - (SCALE_DOWN)*(vy + vx + wz);
	    	BL = (SCALE_DOWN)*(vy + vx - wz);
	    	BR = - (SCALE_DOWN)*(vy - vx + wz);
    	} else if (POV == POV_NONE) { // precision strafing
	    	vx = precisionStrafeRight + precisionStrafeLeft;
	    	FL = (PRECISION_STRAFE_SCALE_DOWN)*(-vx);
	    	FR = - (PRECISION_STRAFE_SCALE_DOWN)*(vx);
	    	BL = (PRECISION_STRAFE_SCALE_DOWN)*(vx);
	    	BR = - (PRECISION_STRAFE_SCALE_DOWN)*(-vx);
	    } else { // precision moving
	    	switch (POV) {
	    	case POV_FRONT:
	    		FL = POV_MOVE_SPEED;
	    		FR = - POV_MOVE_SPEED;
	    		BL = POV_MOVE_SPEED;
	    		BR = - POV_MOVE_SPEED;
	    		break;
	    	case POV_BACK:
	    		FL = - POV_MOVE_SPEED;
	    		FR = POV_MOVE_SPEED;
	    		BL = -POV_MOVE_SPEED;
	    		BR = POV_MOVE_SPEED;
	    		break;
	    	case POV_RIGHT:
	    		FL = -POV_MOVE_SPEED;
	    		FR = -POV_MOVE_SPEED;
	    		BL = POV_MOVE_SPEED;
	    		BR = POV_MOVE_SPEED;
	    		break;
	    	case POV_LEFT:
	    		FL = POV_MOVE_SPEED;
	    		FR = POV_MOVE_SPEED;
	    		BL = -POV_MOVE_SPEED;
	    		BR = -POV_MOVE_SPEED;
	    		break;
	    	default:
	    		break;
	    	}
	    }
    	
    	// command drive subsystem
    	driveSubsystem.teleopMove(FR, FL, BR, BL);
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
    
    /**
     * Enforces deadband (currently 0.1) on passed in value. Used to deadband
     * joystick input to drive.
     * @param value
     * @return
     */
    private double deadband(double value) {
		double output = 0;
		if (value > JOY_DEADBAND || value < -JOY_DEADBAND) { // maps (0.1, 1) to (0,1)
			if (value > JOY_DEADBAND){
				output = (value - JOY_DEADBAND)*JOY_SCALE;
			} else {
				output = (value + JOY_DEADBAND)*JOY_SCALE;
			}
		} else { // inside of deadband
			output = 0;
		}
		
		return output;
	}
}
