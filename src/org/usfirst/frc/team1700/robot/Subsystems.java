package org.usfirst.frc.team1700.robot;

import org.usfirst.frc.team1700.robot.subsystems.*;

public class Subsystems {
	
	//Drive
	public static DriveSubsystem drive = new DriveSubsystem();
	
	//Aligner
	public static AlignmentMotorsSubsystem longAlignmentMotorsSubsystem = new AlignmentMotorsSubsystem(true); // true = long
	public static AlignmentMotorsSubsystem shortAlignmentMotorsSubsystem = new AlignmentMotorsSubsystem(false); // false = short
	public static AlignmentPotentiometerSubsystem alignmentPotentiometerSubsystem = new AlignmentPotentiometerSubsystem();
	
	//Lifter
	public static LifterMotorSubsystem lifterMotor = new LifterMotorSubsystem();
	public static LifterEncoderSubsystem lifterEncoder = new LifterEncoderSubsystem();
	public static LifterLimitSwitchSubsystem lifterLimitSwitch = new LifterLimitSwitchSubsystem();
	

}
