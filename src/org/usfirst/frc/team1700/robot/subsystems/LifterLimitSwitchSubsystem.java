package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterLimitSwitchSubsystem extends Subsystem {
	private DigitalInput topLimitSwitch = new DigitalInput(RobotMap.LIFTER_LIMIT_SWITCH_TOP_PORT);
	private DigitalInput bottomLimitSwitch = new DigitalInput(RobotMap.LIFTER_LIMIT_SWITCH_BOTTOM_PORT);
	
	public LifterLimitSwitchSubsystem() {
		super();
	}
	
	public boolean wasTopSwitchHit(){
		return topLimitSwitch.get();
	}
	
	public boolean wasBottomSwitchHit(){
		return bottomLimitSwitch.get();
	}
	
    public void initDefaultCommand() {
    	
    }
}

