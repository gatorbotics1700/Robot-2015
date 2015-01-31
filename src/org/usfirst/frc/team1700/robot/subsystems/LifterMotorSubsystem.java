package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterMotorSubsystem extends Subsystem {
	private CANTalon lifterTalon1;
	private CANTalon lifterTalon2;
	private LifterLimitSwitchSubsystem limitSwitches;
	private static final double P = 0,
							    I = 0,
							    D = 0;
	private static final int TOP = 0, 
							 BOTTOM = 0;
	
	private static final double LIFTER_SPEED = 0.2;
	public static final double DEADBAND = 0.1;
	
	public LifterMotorSubsystem() {
		super();
		lifterTalon1 = initTalon(RobotMap.LIFTER_TALON_1_ID);
		lifterTalon2 = initTalon(RobotMap.LIFTER_TALON_2_ID);
		limitSwitches = Subsystems.lifterLimitSwitch;
	}

    
    public void lifterStop() {
    	// sets Talon to current position (so doesn't move back to zero), then disables
    	setTalons(getPosition());
    	disable();
    }
    
    public double getPosition() {
    	return lifterTalon1.getPosition();
    }
    
    public void setTalons(double setpoint) {
    	if(safeToMove()){
    		lifterTalon1.set(setpoint);
        	lifterTalon2.set(setpoint);
    	}
    }
    
    private boolean safeToMove() {
    	return (!limitSwitches.wasTopSwitchHit() && !limitSwitches.wasBottomSwitchHit());
    }
    
    private CANTalon initTalon(int address) {
    	CANTalon talon = new CANTalon(address);
    	
    	talon.disableControl(); // disable before set up
    	talon.changeControlMode(CANTalon.ControlMode.Position); // position mode
    	talon.enableBrakeMode(true); // set brake mode
    	talon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder); // set input device
    	talon.setPID(P, I, D);
    	talon.setForwardSoftLimit(TOP);
    	talon.setReverseSoftLimit(BOTTOM);
    	talon.set(talon.getPosition());
    	talon.enableControl();
    	
    	return talon;
    }
    
    public void enable() {
    	// called if command is interrupted and restarted
    	setTalons(getPosition());
    	lifterTalon1.enableControl();
    	lifterTalon2.enableControl();
    }
    
    public void disable() {
    	lifterTalon1.disableControl();
    	lifterTalon2.disableControl();
    }
    
    public void initDefaultCommand() {
        
    }
}

