package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class manages an individual drive train motor and its movement.
 */
public class DriveMotorSubsystem {
	private CANTalon driveTalon;
	private static final double DEADBAND = 0.1;
	private static final double SCALE_FACTOR = 0.75;
	private static final double JOYSTICK_DEADBAND = 0.1;
	private static final double FILTER_CONSTANT = .4;
	private double prevSpeed;
	
	public DriveMotorSubsystem(int ID) {
		driveTalon = new CANTalon(ID);
	}
	
	/**
	 * Given a commanded speed from the operator joystick, enforces a deadband
	 * and sends the scaled speed to the motor's Talon.
	 * @param speed
	 */
	public void move(double speed) {
		if(speed > DEADBAND || speed < -DEADBAND){ 
			System.out.println(speed);
		}
		if(speed > JOYSTICK_DEADBAND || speed < -JOYSTICK_DEADBAND){ 
			driveTalon.set(scale(speed));
			if (Robot.oi.driveJoystick.getRawButton(RobotMap.DEBUGGING_BUTTON)) System.out.println(scale(speed));
		} else {
			System.out.println("set back to zero");
			driveTalon.set(0);
			stop();
		}
	}
	
	public void stop() {
		driveTalon.set(scale(0));
	}
	
	/**
	 * Given a target speed, implements low pass filter for smoother acc/deceleration.
	 * If the calculated output speed is close enough to the target speed, sets ouptut
	 * speed directly to target.
	 * @param targetSpeed - target speed from joystick (joystick deadband implemented)
	 * @return - output speed to command to the motor
	 */
	private double scale(double targetSpeed) {
		double outputSpeed = FILTER_CONSTANT * targetSpeed + (1 - FILTER_CONSTANT) * prevSpeed;
		if (Math.abs(outputSpeed - targetSpeed) < .05) outputSpeed = targetSpeed; // close enough
		prevSpeed = outputSpeed;
		return outputSpeed;
	}
}
