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
	
	private static final double ALIGN_SPEED = 0.4; // needs to be pretty large (maybe even larger)
		
	public DriveSubsystem() {
		super();
	}
	
	public void zeroEncoders() {
		driveMotorFR1.zeroEncoder();
		driveMotorFL2.zeroEncoder();
		driveMotorBL3.zeroEncoder();
		driveMotorBR4.zeroEncoder();
	}

	public void move(double joyValueFR, double joyValueFL, double joyValueBR, double joyValueBL){
		driveMotorFR1.move(joyValueFR);
		driveMotorFL2.move(joyValueFL);
		driveMotorBR4.move(joyValueBR);
		driveMotorBL3.move(joyValueBL);
//		System.out.println("------ moving ------");
//		System.out.println(joyValueFR+"\t"+joyValueFL+"/t"+joyValueBR+"/t"+joyValueBL);
	}
	
	public void autonomousMove(double speed) {
		move(-speed, speed, -speed, speed); // moving forward, right side is reversed
	}
	
	public void autonomousTurn(double speed) { //very low speed because we haven't scaled it down
		move(speed, speed, speed, speed); // turning right (clockwise), right side is reversed
	}
	
	public double getPosition(){
		return driveMotorFL2.getPosition();
	}
	
	public void stop(){
		driveMotorFR1.stop();
		driveMotorFL2.stop();
		driveMotorBR4.stop();
		driveMotorBL3.stop();
	}
	
	/**
	 * Moves the right side of the robot forward at a constant speed. 
	 * For use when aligning with the tote with proximity sensors 
	 * (see AlignToToteCommand).
	 * TODO: be able to move forward or backwards when aligning based on
	 * distance from tote. 
	 */
	public void alignRight() {
		driveMotorFR1.move(ALIGN_SPEED);
		driveMotorBR4.move(ALIGN_SPEED);
	}
	
	/**
	 * Moves the left side of the robot forward at a constant speed. 
	 * For use when aligning with the tote with proximity sensors 
	 * (see AlignToToteCommand).
	 * TODO: be able to move forward or backwards when aligning based on
	 * distance from tote. 
	 */
	public void alignLeft() {
		driveMotorFL2.move(-ALIGN_SPEED);
		driveMotorBL3.move(-ALIGN_SPEED);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new MecanumDriveCommand()); // drive command is always active
    }
}

