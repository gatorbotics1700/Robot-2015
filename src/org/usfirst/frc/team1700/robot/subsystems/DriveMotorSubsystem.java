package org.usfirst.frc.team1700.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;

/**
 * This class manages an individual drive motor and its movement through speed PIDF control.
 * Runs speeds through a second degree low pass filter to scale and minimize jerk.
 */
public class DriveMotorSubsystem {
	private CANTalon driveTalon;
	private int talonID;
	private static final int MAX_SPEED = 5000;
	private static final double JOY_DEADBAND = 0.1;
	private double prevFilt1 = 0, prevFilt2 = 0;
	private static final double FILTER_CONSTANT_1 = 0.3;
	private static final double FILTER_CONSTANT_2 = 0.2;
	
	public DriveMotorSubsystem(int ID) {
		talonID = ID;
		initTalon();
	}
	
	/** ==================== MOVING METHODS ==================== */
	
	/**
	 * Given a commanded speed from the operator joystick, enforces a deadband,
	 * runs the speed through the second degree low pass filter, and sends the
	 * commands to the motor.
	 * @param speed - joystick command
	 */
	public void move(double speed) {
		if(speed > JOY_DEADBAND || speed < -JOY_DEADBAND){ 
			speed = Math.pow(speed, 3);
			double setpoint =  filter(speed, FILTER_CONSTANT_1, FILTER_CONSTANT_2) * MAX_SPEED;
			System.out.println("Joystick: "+ speed + "\tSetpoint: " + setpoint + "\t Measured:" + driveTalon.getSpeed());
			driveTalon.set(setpoint); 
		} else {
			driveTalon.ClearIaccum();
			driveTalon.set(filter(0, FILTER_CONSTANT_1, FILTER_CONSTANT_2)* MAX_SPEED);
		}
	}
	
	public void stop() {
		driveTalon.set(filter(0, FILTER_CONSTANT_1, FILTER_CONSTANT_2)*MAX_SPEED);
	}
	
	/** ==================== ENCODER METHODS ==================== */
	
	public double getPosition() {
		return driveTalon.getPosition();
	}
	
	public void zeroEncoder() {
		driveTalon.setPosition(0);
	}
	
	/** ==================== HELPER METHODS ==================== */

	/**
	 * Given a target speed, implements a second degree low pass filter for smoother acceleration.
	 * Also limits maximum acceleration in a given time step.
	 * @param targetSpeed - target speed from joystick (deadband implemented)
	 * @return - output speed to command to the motor
	 */
	private double filter(double input, double filterConstant1, double filterConstant2) {
		double maxDelta = 0.03; // max acceleration
		double filter1 = filterConstant1 * input + (1 - filterConstant1) * prevFilt1;
		double filter2 = filterConstant2 * filter1 + (1 - filterConstant2) * prevFilt2;
		
		if (Math.abs(filter2 - prevFilt2) > maxDelta) {
			filter2 = prevFilt2 + Math.signum(filter2 - prevFilt2) * maxDelta;
		}
		prevFilt1 = filter1;
		prevFilt2 = filter2;
		
		return filter2;
	}
	
	private CANTalon initTalon() {
    	driveTalon = new CANTalon(talonID);
    	
    	driveTalon.disableControl();
    	driveTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder); // set input device
    	
		driveTalon.changeControlMode(CANTalon.ControlMode.Speed);
		driveTalon.setPID(0.4,0.001,0);
    	
		// custom feed forward terms for each motor
    	switch(talonID) {
    		case 1: driveTalon.setF(0.94/2); // back left
    			break;
    		case 2: driveTalon.setF(1.044/2); // front left
    			break;
    		case 3: driveTalon.setF(1.01/2); // front right
    			break;
    		case 4: driveTalon.setF(0.977/2); // back right
    			break;
    	}
    	
    	driveTalon.set(0);
    	driveTalon.enableControl();
    	
    	return driveTalon;
    }
	
}
