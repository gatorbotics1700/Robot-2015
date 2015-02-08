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
		talon1 = new CANTalon(9);
		talon2 = new CANTalon(10);
	}
	
	public void move(double joyInput) {
		double speed = (SCALE_DOWN * deadband(joyInput));
		talon1.set(speed);
		talon2.set(speed);
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

    public void initDefaultCommand() {
        setDefaultCommand(new LifterCommand());
    }
}

