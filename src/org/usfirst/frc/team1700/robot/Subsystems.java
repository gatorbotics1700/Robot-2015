package org.usfirst.frc.team1700.robot;

import org.usfirst.frc.team1700.robot.subsystems.*;

public class Subsystems {
	
	//Drive
	public static DriveSubsystem driveSubsystem = new DriveSubsystem();
	
	//Aligner
	public static AlignmentMotorsSubsystem alignmentMotorsSubsystem = new AlignmentMotorsSubsystem();
	public static AlignmentPotentiometerSubsystem alignmentPotentiometerSubsystem = new AlignmentPotentiometerSubsystem();
	
	//Lifter
	public static LifterMotorSubsystem lifterMotor = new LifterMotorSubsystem();
	public static LifterEncoderSubsystem lifterEncoder = new LifterEncoderSubsystem();
	public static LifterLimitSwitchSubsystem lifterLimitSwitch = new LifterLimitSwitchSubsystem();
	

}
