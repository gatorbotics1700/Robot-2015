package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterLimitSwitchSubsystem extends Subsystem {
	private DigitalInput leftLS, rightLS;
	
	public LifterLimitSwitchSubsystem() {
		super();
		leftLS = new DigitalInput(RobotMap.LIFTER_LIMIT_SWITCH_LEFT);
		rightLS = new DigitalInput(RobotMap.LIFTER_LIMIT_SWITCH_RIGHT);
	}
	
	public boolean isHit() {
		// LEFT: false is open, true is pressed  |  RIGHT: false is pressed, true is open
		return (leftLS.get() || !rightLS.get());
	}
	
    public void initDefaultCommand() {
    	
    }
}

