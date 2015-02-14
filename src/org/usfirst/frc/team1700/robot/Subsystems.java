package org.usfirst.frc.team1700.robot;

import org.usfirst.frc.team1700.robot.subsystems.*;

/**
 * This class holds all of the robot's subsystem instances to be referenced 
 * in the commands.  
 */
public class Subsystems {
	
	// mecanum drive
	public static DriveSubsystem drive = new DriveSubsystem();
	
	// tote aligner
	public static AlignmentMotorsSubsystem longAlignmentMotorsSubsystem = 
			new AlignmentMotorsSubsystem(true); // true = long
	public static AlignmentMotorsSubsystem shortAlignmentMotorsSubsystem = 
			new AlignmentMotorsSubsystem(false); // false = short
	
	// chain lifter
	public static LifterMotorSubsystem lifter = new LifterMotorSubsystem();
	public static LifterLimitSwitchSubsystem lifterLS = new LifterLimitSwitchSubsystem();
	
	// camera
	//public static CameraSubsystem camera = new CameraSubsystem();
}
