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
	private static final double JOYSTICK_DEADBAND = 0.1;
	private double prevFilt1 = 0;
	private double prevFilt2 = 0;
	private static final double FILTER_CONSTANT_1 = 0.2;
	private static final double FILTER_CONSTANT_2 = 0.2; // should probably be a little higher.
	private int TalonID;
	
	public DriveMotorSubsystem(int ID) {
		TalonID = ID;
		initTalon();
	}
	
	/**
	 * Given a commanded speed from the operator joystick, enforces a deadband
	 * and sends the scaled speed to the motor's Talon.
	 * @param speed
	 */
	public void speedModeMove(double speed) {
		if(speed > JOYSTICK_DEADBAND || speed < -JOYSTICK_DEADBAND){ 
			double setpoint =  scale(speed, FILTER_CONSTANT_1, FILTER_CONSTANT_2) * 5000;
//			double setpoint =  speed * 6000;
			driveTalon.set(setpoint); //max speed
//			System.out.print(Math.abs(driveTalon.getSpeed()) + "\t");
	    	//System.out.println("Error: " + driveTalon.getClosedLoopError());
//			System.out.println("Setpoint: " + driveTalon.getSetpoint());
		} else {
//			System.out.println("set back to zero");
			driveTalon.set(scale(0, FILTER_CONSTANT_1, FILTER_CONSTANT_2)*5000);
		}
	}
	
	public void positionModeMove(double position) {
		driveTalon.set(position);
//		System.out.println("Setpoint: " + driveTalon.getSetpoint());
	}
	
	public double getPosition() {
		return driveTalon.getPosition();
	}
	
	public void zeroEncoder() {
		driveTalon.setPosition(0); //beware! not sure if this is the right one to use
	}
	
	
	public void speedModeStop() {
		driveTalon.set(scale(0, FILTER_CONSTANT_1, FILTER_CONSTANT_2));
	}
	
	public void positionModeStop() {
		driveTalon.set(getPosition());
	}
	
	/**
	 * Given a target speed, implements low pass filter for smoother acc/deceleration.
	 * If the calculated output speed is close enough to the target speed, sets ouptut
	 * speed directly to target.
	 * @param targetSpeed - target speed from joystick (joystick deadband implemented)
	 * @return - output speed to command to the motor
	 */
	
	private double scale(double input, double filterConstant1, double filterConstant2) {
		double maxDelta = 0.03;
		double filt1 = filterConstant1 * input + (1 - filterConstant1) * prevFilt1;
		double filt2 = filterConstant2 * filt1 + (1 - filterConstant2) * prevFilt2;
		
		//if (Math.abs(outputSpeed - targetSpeed) < .0005) outputSpeed = targetSpeed; // close enough
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
    	setToPositionMode();
    	
    	
    	driveTalon.set(0);
    	driveTalon.enableControl();

    	driveTalon.setCloseLoopRampRate(12);
//    	System.out.println("Ramp Rate: " + driveTalon.getCloseLoopRampRate());
    	
    	return driveTalon;
    }
	
	public void setToSpeedMode(){
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
	}
	
	public void setToPositionMode(){
		driveTalon.changeControlMode(CANTalon.ControlMode.Position);
		driveTalon.setPID(0.05, 0, 0); //test
		driveTalon.setF(0);
	}
}
