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
	public static AlignmentPotentiometerSubsystem longAlignmentPotentiometerSubystem = 
			new AlignmentPotentiometerSubsystem(true);
	public static AlignmentPotentiometerSubsystem shortAlignmentPotentiometerSubsystem = 
			new AlignmentPotentiometerSubsystem(false);
	
	// chain lifter
	public static LifterMotorSubsystem lifterMotor = new LifterMotorSubsystem();
	public static LifterLimitSwitchSubsystem lifterLimitSwitch = new LifterLimitSwitchSubsystem();
}
