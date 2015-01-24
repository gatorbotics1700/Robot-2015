package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.commands.MecanumDriveCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	
	public final DriveMotor driveMotorFR1 = new DriveMotor(RobotMap.VICTOR_FR);
	public final DriveMotor driveMotorFL2 = new DriveMotor(RobotMap.VICTOR_FL);
	public final DriveMotor driveMotorBL3 = new DriveMotor(RobotMap.VICTOR_BL);
	public final DriveMotor driveMotorBR4 = new DriveMotor(RobotMap.VICTOR_BR);
		
	public DriveSubsystem(){
		super();
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public void move(double joyValueFR, double joyValueFL, double joyValueBR, double joyValueBL){
		driveMotorFR1.move(joyValueFR);
		driveMotorFL2.move(joyValueFL);
		driveMotorBR4.move(joyValueBR);
		driveMotorBL3.move(joyValueBL);
		System.out.println("------ moving ------");
		//System.out.println(joyValueFR+"\t"+joyValueFL+"/t"+joyValueBR+"/t"+joyValueBL);
	}
	
	public void stop(){
		driveMotorFR1.stop();
		driveMotorFL2.stop();
		driveMotorBR4.stop();
		driveMotorBL3.stop();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new MecanumDriveCommand());
    	
    }
}

