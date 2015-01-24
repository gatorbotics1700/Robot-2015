package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterLimitSwitchSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private DigitalInput topLimitSwitch = new DigitalInput(RobotMap.LIFTER_LIMIT_SWITCH_TOP_PORT);
	private DigitalInput bottomLimitSwitch = new DigitalInput(RobotMap.LIFTER_LIMIT_SWITCH_BOTTOM_PORT);
	public boolean wasTopSwitchHit(){
		return topLimitSwitch.get();
	}
	public boolean wasBottomSwitchHit(){
		return bottomLimitSwitch.get();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

