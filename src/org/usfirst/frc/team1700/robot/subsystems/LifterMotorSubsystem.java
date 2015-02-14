package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.commands.ManualLifterCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterMotorSubsystem extends Subsystem {

	private static final double DEADBAND = .15;
	private static final double JOY_SCALE = 1/(1-DEADBAND);
	private static final double SCALE_DOWN = 0.2;
	
	private CANTalon lifterTalon1;
	private CANTalon lifterTalon2;
	private LifterLimitSwitchSubsystem limitSwitches;
	private static final double P = 0,
							    I = 0,
							    D = 0;
	

	
	public LifterMotorSubsystem() {
		super();
		lifterTalon1 = initTalon(RobotMap.LIFTER_TALON_1_ID);
		lifterTalon2 = initTalon(RobotMap.LIFTER_TALON_2_ID);
//		limitSwitches = Subsystems.lifterLimitSwitch;
	}
	
	
	//Lifter Methods
    
	//SetUp
	 public void enable() {
	    	// called if command is interrupted and restarted
	    	safeMove(getPosition());
	    	lifterTalon1.enableControl();
	    	lifterTalon2.enableControl();
	    	System.out.println("ENABLING ----------------");
	 }
	    
	 public void disable() {
	    stop();
	    lifterTalon1.disableControl();
	    lifterTalon2.disableControl();
	    System.out.println("DISABLING ----------------");
	 }
	
	public void stop() {
    	// sets Talon to current position (so doesn't move back to zero)
    	lifterTalon1.set(getPosition());
    	lifterTalon2.set(getPosition());
    }

    
    //Checks
    private boolean safeToMove() {
    	return (!limitSwitches.wasTopSwitchHit() && !limitSwitches.wasBottomSwitchHit());
    }
    
	private boolean safeToMoveUp() {
		double position = getPosition();
		return (position < 155000);
	}
	
	private boolean safeToMoveDown() {
		double position = getPosition();
		return (position > 0);
	}
  
	//Moving
	public void unsafeMove(double position) {
		lifterTalon1.set(position);
		lifterTalon2.set(position);
		System.out.println("unsafe");
		System.out.println(lifterTalon1.getPosition() + "\t" + lifterTalon1.getSetpoint() + "\t" + position);
//		System.out.println(lifterTalon1.getClosedLoopError());
	}
	
	public void safeMove(double position) {
		boolean goingUp = position >= getPosition();
		if ((goingUp && safeToMoveUp()) || (!goingUp && safeToMoveDown())) {
			lifterTalon1.set(position);
			lifterTalon2.set(position);
		} else {
			lifterTalon1.set(0);
			lifterTalon2.set(0);
		}
		
		System.out.println("safe");
		System.out.println(lifterTalon1.getPosition() + "\t" + lifterTalon1.getSetpoint() + "\t" + position);
	}
	
	
	//Encoder methods
	public void zeroEncoders() {
    	lifterTalon1.setPosition(0);
    	lifterTalon2.setPosition(0);
    }
	
	public double getPosition() {
	    return lifterTalon1.getPosition();
	}
	
	//Helper methods
	private double deadband(double value) {
		double output = 0;
		if (value > DEADBAND || value < -DEADBAND) { // maps (0.1, 1) to (0,1)
			if (value > DEADBAND){
				output = (value - DEADBAND)*JOY_SCALE;
			} else {
				output = (value + DEADBAND)*JOY_SCALE;
			}
		} else { // outside of deadband
			output = 0;
		}
		
		return output;
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
    
    
    //Default
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualLifterCommand());
    }
}

