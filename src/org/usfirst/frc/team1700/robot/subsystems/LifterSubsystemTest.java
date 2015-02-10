package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.commands.LifterCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterSubsystemTest extends Subsystem {
    
	private CANTalon talon1, talon2;
	private static final double DEADBAND = .15;
	private static final double JOY_SCALE = 1/(1-DEADBAND);
	private static final double SCALE_DOWN = 0.2;
	
	public LifterSubsystemTest() {
		talon1 = initTalon(9);
		talon2 = initTalon(10);
	}
	
	public void unsafeMove(double joyInput) {
		double speed = (SCALE_DOWN * deadband(joyInput));
		talon1.set(speed);
		talon2.set(speed);
	}
	
	public void safeMove(double joyInput) {
		double speed = (SCALE_DOWN * deadband(joyInput)); //negative bc joystick is backwards
		if ((speed >= 0 && safeToMoveUp()) || (speed < 0 && safeToMoveDown())) {
			talon1.set(speed); // negative bc of control preference
			talon2.set(speed);
		} else {
			talon1.set(0);
			talon2.set(0);
		}
	}
	
	private boolean safeToMoveUp() {
		int position = getPosition();
		return (position < 150220);
	}
	
	private boolean safeToMoveDown() {
		int position = getPosition();
		return (position > 0);
	}
	
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
	
	public int getPosition() {
		return talon1.getEncPosition();
	}

    public void initDefaultCommand() {
        setDefaultCommand(new LifterCommand());
    }
    
    private CANTalon initTalon(int address) {
    	CANTalon talon = new CANTalon(address);
    	talon.changeControlMode(CANTalon.ControlMode.PercentVbus);
    	talon.enableForwardSoftLimit(false);
    	talon.enableReverseSoftLimit(false);
    	talon.setPosition(0); // zero
    	talon.enableControl();
//    	talon.reverseOutput(true);
    	
    	return talon;
    }
    
    public void zeroEncoders() {
    	talon1.setPosition(0);
    	talon2.setPosition(0);
    }
}

