package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.commands.ManualLifterCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Manages the tote/bin lifter through position PID control.
 */
public class LifterMotorSubsystem extends Subsystem {

	private CANTalon lifterTalon1, lifterTalon2;
	private LifterLimitSwitchSubsystem limitSwitches;
	
	public LifterMotorSubsystem() {
		super();
		lifterTalon1 = initTalon(RobotMap.LIFTER_TALON_1_ID);
		lifterTalon2 = initTalon(RobotMap.LIFTER_TALON_2_ID);
		limitSwitches = Subsystems.lifterLS;
	}
	
	
    
	/** ==================== SETUP ==================== */
	 public void enable() {
	    // called if command is interrupted and restarted
	    safeMove(getPosition());
	    lifterTalon1.enableControl();
	    lifterTalon2.enableControl();
	 }
	    
	 public void disable() {
	    stop();
	    lifterTalon1.disableControl();
	    lifterTalon2.disableControl();
	 }
	 
	 /** ==================== CHECKS ==================== */
	
	 public boolean limitSwitchHit() {
		return limitSwitches.isHit();
	 }
	 
	private boolean safeToMoveUp() {
		double position = getPosition();
		return (position < 155000);
	}
	
	private boolean safeToMoveDown() {
		double position = getPosition();
		return (position > 0 && !limitSwitches.isHit());
	}
  
	/** ==================== MOVING ==================== */
	public void stop() {
		// sets Talon to current position (so doesn't move back to zero)
		lifterTalon1.set(getPosition());
		lifterTalon2.set(getPosition());
	 }
	
	// Does not check for the encoder soft limits. Used during calibration and for emergencies.
	public void uncheckedMove(double position) {
		boolean goingUp = position >= getPosition();
		if ((!goingUp && !limitSwitches.isHit()) || (goingUp)) {
			lifterTalon1.set(position);
			lifterTalon2.set(position);
		} else {
			lifterTalon1.set(getPosition());
			lifterTalon2.set(getPosition());
		}
	}
	
	// Checks for encoder's soft limits.
	public void safeMove(double position) {
		boolean goingUp = position >= getPosition();
		if ((goingUp && safeToMoveUp()) || (!goingUp && safeToMoveDown())) {
			lifterTalon1.set(position);
			lifterTalon2.set(position);
		} else {
			lifterTalon1.set(getPosition());
			lifterTalon2.set(getPosition());
		}
	}
	
	/** ==================== ENCODERS ==================== */
	public void zeroEncoders() {
    	lifterTalon1.setPosition(0);
    	lifterTalon2.setPosition(0);
    }
	
	public double getPosition() {
	    return lifterTalon1.getPosition();
	}
	
    private CANTalon initTalon(int address) {
    	CANTalon talon = new CANTalon(address);
    	
    	talon.disableControl(); // disable before set up
    	talon.changeControlMode(CANTalon.ControlMode.Position);
    	talon.enableBrakeMode(true);
    	talon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	talon.setPID(0.055, 0, 0);
    	talon.reverseOutput(true);
    	talon.enableForwardSoftLimit(false);
    	talon.enableReverseSoftLimit(false);
    	talon.set(talon.getPosition());
    	talon.enableControl();
    	
    	return talon;
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualLifterCommand());
    }
    
}

