package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.MecanumDriveCommand;

import edu.wpi.first.wpilibj.CANTalon;
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
	private boolean autonomous = true;
		
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

	//Teleop Moving Methods
	public void teleopMove(double joyValueFR, double joyValueFL, double joyValueBR, double joyValueBL){
		if(autonomous) {
			autonomous = false;
			setToTeleop();
		}
//		move(joyValueFR, joyValueFL, joyValueBR, joyValueBL);
		driveMotorFR1.speedModeMove(joyValueFR);
		driveMotorFL2.speedModeMove(joyValueFL);
		driveMotorBR4.speedModeMove(joyValueBR);
		driveMotorBL3.speedModeMove(joyValueBL);
//		System.out.println(driveMotorFR1.getPosition());
	}
	
	private void setToTeleop(){
		driveMotorFR1.setToSpeedMode();
		driveMotorFL2.setToSpeedMode();
		driveMotorBR4.setToSpeedMode();
		driveMotorBL3.setToSpeedMode();
	}
	
	public void stop(){
		if (autonomous) {
			driveMotorFR1.positionModeStop();
			driveMotorFL2.positionModeStop();
			driveMotorBR4.positionModeStop();
			driveMotorBL3.positionModeStop();
		} else {
			driveMotorFR1.speedModeStop();
			driveMotorFL2.speedModeStop();
			driveMotorBR4.speedModeStop();
			driveMotorBL3.speedModeStop();
		}
	}
	

	//Autonomous Move Methods
	public void autonomousMove(double speed) {
		//starts in autonomous mode, so no need to explicitly set it
//		move(-speed, speed, -speed, speed); // moving forward, right side is reversed
		driveMotorFR1.positionModeMove(-speed);
		driveMotorFL2.positionModeMove(speed);
		driveMotorBR4.positionModeMove(-speed);
		driveMotorBL3.positionModeMove(speed);
	}
	
	public void autonomousTurn(double speed) { //very low speed because we haven't scaled it down
//		move(speed, speed, speed, speed); // turning right (clockwise), right side is reversed
		driveMotorFR1.positionModeMove(speed);
		driveMotorFL2.positionModeMove(speed);
		driveMotorBR4.positionModeMove(speed);
		driveMotorBL3.positionModeMove(speed);
		System.out.println("drive pos: " + getPosition());
	}
	
	//Set Motors
//	private void move(double joyValueFR, double joyValueFL, double joyValueBR, double joyValueBL){
//		driveMotorFR1.moveSpeed(joyValueFR);
//		driveMotorFL2.moveSpeed(joyValueFL);
//		driveMotorBR4.moveSpeed(joyValueBR);
//		driveMotorBL3.moveSpeed(joyValueBL);
//	}
	
	//Aligning Methods
	/**
	 * Moves the right side of the robot forward at a constant speed. 
	 * For use when aligning with the tote with proximity sensors 
	 * (see AlignToToteCommand).
	 * TODO: be able to move forward or backwards when aligning based on
	 * distance from tote. 
	 */
//	public void alignRight() {
//		driveMotorFR1.moveSpeed(ALIGN_SPEED);
//		driveMotorBR4.moveSpeed(ALIGN_SPEED);
//	}
	
	/**
	 * Moves the left side of the robot forward at a constant speed. 
	 * For use when aligning with the tote with proximity sensors 
	 * (see AlignToToteCommand).
	 * TODO: be able to move forward or backwards when aligning based on
	 * distance from tote. 
	 */
//	public void alignLeft() {
//		driveMotorFL2.moveSpeed(-ALIGN_SPEED);
//		driveMotorBL3.moveSpeed(-ALIGN_SPEED);
//	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new MecanumDriveCommand()); // drive command is always active
    }
}

