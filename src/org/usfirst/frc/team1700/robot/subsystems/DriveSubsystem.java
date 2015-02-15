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

	//Moving Methods
	public void teleopMove(double joyValueFR, double joyValueFL, double joyValueBR, double joyValueBL){
//		move(joyValueFR, joyValueFL, joyValueBR, joyValueBL);
		driveMotorFR1.move(joyValueFR);
		driveMotorFL2.move(joyValueFL);
		driveMotorBR4.move(joyValueBR);
		driveMotorBL3.move(joyValueBL);
//		System.out.println(driveMotorFR1.getPosition());
	}
	
	
	public void stop(){
		driveMotorFR1.stop();
		driveMotorFL2.stop();
		driveMotorBR4.stop();
		driveMotorBL3.stop();
	}
	

	//Autonomous Move Methods
	public void autonomousMove(double speed) {
		//starts in autonomous mode, so no need to explicitly set it
//		move(-speed, speed, -speed, speed); // moving forward, right side is reversed
		driveMotorFR1.move(-speed);
		driveMotorFL2.move(speed);
		driveMotorBR4.move(-speed);
		driveMotorBL3.move(speed);
	}
	

	
    public void initDefaultCommand() {
    	setDefaultCommand(new MecanumDriveCommand()); // drive command is always active
    }
}

