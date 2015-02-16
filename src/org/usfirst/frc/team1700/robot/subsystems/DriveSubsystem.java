package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.MecanumDriveCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The drive subsystem manages and controls the four drive motors.
 * Used in both normal mecanum drive and also the align to tote command.
 */
public class DriveSubsystem extends Subsystem {
	
	public final DriveMotorSubsystem driveMotorFR1 = new DriveMotorSubsystem(RobotMap.TALON_DRIVE_FR_ID);
	public final DriveMotorSubsystem driveMotorFL2 = new DriveMotorSubsystem(RobotMap.TALON_DRIVE_FL_ID);
	public final DriveMotorSubsystem driveMotorBL3 = new DriveMotorSubsystem(RobotMap.TALON_DRIVE_BL_ID);
	public final DriveMotorSubsystem driveMotorBR4 = new DriveMotorSubsystem(RobotMap.TALON_DRIVE_BR_ID);
			
	public DriveSubsystem() {
		super();
	}
	
	
	//Encoder functions
	public double getPosition(){
		return driveMotorFL2.getPosition();
	}
	
	public void zeroEncoders() {
		driveMotorFR1.zeroEncoder();
		driveMotorFL2.zeroEncoder();
		driveMotorBL3.zeroEncoder();
		driveMotorBR4.zeroEncoder();
	}

	//Moving Methods
	public void teleopMove(double joyValueFR, double joyValueFL, double joyValueBR, double joyValueBL){
		driveMotorFR1.move(joyValueFR);
		driveMotorFL2.move(joyValueFL);
		driveMotorBR4.move(joyValueBR);
		driveMotorBL3.move(joyValueBL);
	}
	
	
	public void stop(){
		driveMotorFR1.stop();
		driveMotorFL2.stop();
		driveMotorBR4.stop();
		driveMotorBL3.stop();
	}
	

	//Autonomous Move Methods
	public void autonomousMove(double speed) {
		driveMotorFR1.move(-speed);
		driveMotorFL2.move(speed);
		driveMotorBR4.move(-speed);
		driveMotorBL3.move(speed);
	}
	

	
    public void initDefaultCommand() {
    	setDefaultCommand(new MecanumDriveCommand()); // drive command is always active
    }
}

