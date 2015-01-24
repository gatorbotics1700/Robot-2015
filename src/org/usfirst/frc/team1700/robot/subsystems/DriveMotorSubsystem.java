package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveMotorSubsystem {
	private Victor vic;
	private static final double DEADBAND = 0.1;
	private static final double SCALE_FACTOR = .75;
	
	public DriveMotorSubsystem(int port) {
		vic = new Victor(port);
	}
	
	public void move(double speed) {
		if(speed > DEADBAND || speed < -DEADBAND){ 
			vic.set(scale(speed));
//			SmartDashboard.putNumber("drive motor speed: ", scale(speed));
			if (Robot.oi.driveJoystick.getRawButton(RobotMap.DEBUGGING_BUTTON)) System.out.println(scale(speed));
		} else {
			vic.set(0);
		}
	}
	
	public void stop() {
		vic.set(0);
	}
	
	private double scale(double value) {
		return (value * SCALE_FACTOR); // safety for driving w/o gearboxes
	}
}
