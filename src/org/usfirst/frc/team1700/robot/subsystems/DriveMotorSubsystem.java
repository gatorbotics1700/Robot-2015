package org.usfirst.frc.team1700.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;

/**
 * This class manages an individual drive train motor and its movement.
 */
public class DriveMotorSubsystem {
	private CANTalon driveTalon;
	private static final double JOYSTICK_DEADBAND = 0.1;
	private double prevFilt1 = 0, prevFilt2 = 0;
	private static final double FILTER_CONSTANT_1 = 0.2;
	private static final double FILTER_CONSTANT_2 = 0.2;
	private int TalonID;
	
	public DriveMotorSubsystem(int ID) {
		TalonID = ID;
		initTalon();
	}
	
	//Moving Methods
	/**
	 * Given a commanded speed from the operator joystick, enforces a deadband
	 * and sends the scaled speed to the motor's Talon.
	 * @param speed
	 */
	public void move(double speed) {
		if(speed > JOYSTICK_DEADBAND || speed < -JOYSTICK_DEADBAND){ 
			double setpoint =  scale(speed, FILTER_CONSTANT_1, FILTER_CONSTANT_2) * 5000;
			driveTalon.set(setpoint); 
		} else {
			driveTalon.set(scale(0, FILTER_CONSTANT_1, FILTER_CONSTANT_2)*5000);
		}
	}
	
	public void stop() {
		driveTalon.set(scale(0, FILTER_CONSTANT_1, FILTER_CONSTANT_2));
	}
	
	
	//Encoder Methods
	public double getPosition() {
		return driveTalon.getPosition();
	}
	
	public void zeroEncoder() {
		driveTalon.setPosition(0); //beware! not sure if this is the right one to use
	}
	
	/**
	 * Given a target speed, implements low pass filter for smoother acc/deceleration.
	 * If the calculated output speed is close enough to the target speed, sets ouptut
	 * speed directly to target.
	 * @param targetSpeed - target speed from joystick (joystick deadband implemented)
	 * @return - output speed to command to the motor
	 */
	
	
	//Helper Methods
	private double scale(double input, double filterConstant1, double filterConstant2) {
		double maxDelta = 0.03;
		double filt1 = filterConstant1 * input + (1 - filterConstant1) * prevFilt1;
		double filt2 = filterConstant2 * filt1 + (1 - filterConstant2) * prevFilt2;
		
		if (Math.abs(filt2 - prevFilt2) > maxDelta) {
			filt2 = prevFilt2 + Math.signum(filt2 - prevFilt2) * maxDelta;
		}
		prevFilt1 = filt1;
		prevFilt2 = filt2;
		
		return filt2;
	}
	
	private CANTalon initTalon() {
    	driveTalon = new CANTalon(TalonID);
    	
    	driveTalon.disableControl(); // disable before set up
    	driveTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder); // set input device
    	
		driveTalon.changeControlMode(CANTalon.ControlMode.Speed);
		driveTalon.setPID(0.4,0.001,0); // 0.4,0.002,0 (yay! good!)
    	
    	switch(TalonID) {
    		case 1: driveTalon.setF(0.94/2); //BL
    			break;
    		case 2: driveTalon.setF(1.044/2); //FL
    			break;
    		case 3: driveTalon.setF(1.01/2); //FR
    			break;
    		case 4: driveTalon.setF(0.977/2); //BR
    			break;
    	}
    	
    	driveTalon.set(0);
    	driveTalon.enableControl();
    	
    	return driveTalon;
    }
	
}
