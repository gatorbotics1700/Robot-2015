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
	private static final double FILTER_CONSTANT_1 = 0.1;
	private static final double FILTER_CONSTANT_2 = 0.1; // should probably be a little higher.
	
	public DriveMotorSubsystem(int ID) {
		driveTalon = initTalon(ID);
	}
	
	/**
	 * Given a commanded speed from the operator joystick, enforces a deadband
	 * and sends the scaled speed to the motor's Talon.
	 * @param speed
	 */
	public void move(double speed) {
		if(speed > JOYSTICK_DEADBAND || speed < -JOYSTICK_DEADBAND){ 
			driveTalon.set(scale(speed, FILTER_CONSTANT_1, FILTER_CONSTANT_2) * 6000); //max speed
			//if (Robot.oi.driveJoystick.getRawButton(RobotMap.DEBUGGING_BUTTON)) System.out.println(scale(speed));
			//System.out.println("Speed: " + driveTalon.getSpeed());
	    	//System.out.println("Error: " + driveTalon.getClosedLoopError());
	    	System.out.println("Output Voltage: "+ driveTalon.getOutputVoltage());
		} else {
//			System.out.println("set back to zero");
			driveTalon.set(scale(0, FILTER_CONSTANT_1, FILTER_CONSTANT_2));
			stop();
		}
		
    	
     
	}
	
	public void stop() {
		driveTalon.set(scale(0, FILTER_CONSTANT_1, FILTER_CONSTANT_2));
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
	
	private CANTalon initTalon(int address) {
    	CANTalon talon = new CANTalon(address);
    	
    	talon.disableControl(); // disable before set up
    	talon.changeControlMode(CANTalon.ControlMode.Speed); // position mode
    	talon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder); // set input device
    	talon.setPID(0.3,0.002,0); // 0.3,0.002,0.01
    	talon.setF(0.2);
    	talon.setIZone(0);
    	talon.set(0);
    	talon.enableControl();
    	//talon.setCloseLoopRampRate(0.2);
    	System.out.println("Ramp Rate: " + talon.getCloseLoopRampRate());
    	
    	return talon;
    }
}
