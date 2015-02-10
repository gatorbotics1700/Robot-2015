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
	
	private static final double DEADBAND = .15;
	private static final double JOY_SCALE = 1/(1-DEADBAND);
	
	public LifterMotorSubsystem() {
		super();
		lifterTalon1 = initTalon(RobotMap.LIFTER_TALON_1_ID);
		lifterTalon2 = initTalon(RobotMap.LIFTER_TALON_2_ID);
//		limitSwitches = Subsystems.lifterLimitSwitch;
	}

    public void stop() {
    	// sets Talon to current position (so doesn't move back to zero), then disables
    	lifterTalon1.set(getPosition());
    	lifterTalon2.set(getPosition());
    }
    
    // reads from only one Talon (readings should be the same for both b/c using the same encoder)
    public double getPosition() {
    	return lifterTalon1.getPosition();
    }
    
    public void setTalons(double setpoint) {
    	//if (safeToMove()){
    		lifterTalon1.set(setpoint);
        	lifterTalon2.set(setpoint);
    	//} else { 
    	//	stop();
    	//}
        	System.out.println(lifterTalon1.getEncPosition() + "\t" + lifterTalon1.getClosedLoopError() + "\t" + lifterTalon1.getSetpoint());
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
    	talon.setPID(0.04, 0, 0);
    	talon.reverseOutput(true);
    	talon.enableForwardSoftLimit(false);
    	talon.enableReverseSoftLimit(false);
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
    	stop();
    	lifterTalon1.disableControl();
    	lifterTalon2.disableControl();
    }
    
    public void initDefaultCommand() {
        
    }
}

