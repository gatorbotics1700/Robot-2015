package org.usfirst.frc.team1700.robot;

import org.usfirst.frc.team1700.robot.subsystems.*;

/**
 * This class holds all of the robot's subsystem instances to be referenced 
 * in the commands.  
 */
public class Subsystems {
	// mecanum drive
	
	public Subsystems(){
		System.out.println("created subystems");
	}
	
	
	public static DriveSubsystem drive = new DriveSubsystem();
	
	
	// tote aligner
	public static AlignmentMotorsSubsystem longAlignmentMotorsSubsystem = 
			new AlignmentMotorsSubsystem(true); // true = long
	public static AlignmentMotorsSubsystem shortAlignmentMotorsSubsystem = 
			new AlignmentMotorsSubsystem(false); // false = short
	public static AlignmentEncoderSubsystem longAlignmentEncoderSubystem = 
			new AlignmentEncoderSubsystem(true);
	public static AlignmentEncoderSubsystem shortAlignmentEncoderSubsystem = 
			new AlignmentEncoderSubsystem(false);
	
	// chain lifter
	public static LifterMotorSubsystem lifterMotor = new LifterMotorSubsystem();
	public static LifterLimitSwitchSubsystem lifterLimitSwitch = new LifterLimitSwitchSubsystem();
	public static LifterSubsystemTest lifterTest = new LifterSubsystemTest();
}
