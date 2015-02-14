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
	private static final double PRECISION_STRAFE_SCALE_DOWN = .3;
	
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
    	double precisionStrafeRight = deadband(oi.driveJoystick.getRawAxis(RobotMap.STRAFE_RIGHT));
    	double precisionStrafeLeft = -1 * deadband(oi.driveJoystick.getRawAxis(RobotMap.STRAFE_LEFT));
    	double vy = - deadband(oi.driveJoystick.getRawAxis(RobotMap.MOVE_Y));
    	double vx = deadband(oi.driveJoystick.getRawAxis(RobotMap.MOVE_X));
    	double wz = - deadband(oi.driveJoystick.getRawAxis(RobotMap.ROTATE_X));
    	double FL, FR, BL, BR;
    	
    	if (precisionStrafeRight <= DEADBAND && precisionStrafeLeft >= -DEADBAND) {
	    	// normal drive
	    	FL = (SCALE_DOWN)*(vy - STRAFE_FRONT_SCALE * vx - wz);
	    	FR = - (SCALE_DOWN)*(vy + STRAFE_FRONT_SCALE* vx + wz);
	    	BL = (SCALE_DOWN)*(vy + vx - wz);
	    	BR = - (SCALE_DOWN)*(vy - vx + wz);
    	}
	    else {
	    	vx = precisionStrafeRight + precisionStrafeLeft;
	    	FL = (PRECISION_STRAFE_SCALE_DOWN)*(- STRAFE_FRONT_SCALE * vx);
	    	FR = - (PRECISION_STRAFE_SCALE_DOWN)*(STRAFE_FRONT_SCALE* vx);
	    	BL = (PRECISION_STRAFE_SCALE_DOWN)*(vx);
	    	BR = - (PRECISION_STRAFE_SCALE_DOWN)*(-vx);
	    }
    	
    	//System.out.println("Setpoint: FR: " + FR + "  FL: " + FL + "  BR: " + BR + "  BL: " + BL);
//    	System.out.print("\n");
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
		} else { // inside of deadband
			output = 0;
		}
		
		return output;
	}
}
