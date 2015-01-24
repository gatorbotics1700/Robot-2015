package org.usfirst.frc.team1700.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterLimitSwitchSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private DigitalInput topLimitSwitch = new DigitalInput(1);
	private DigitalInput bottomLimitSwitch = new DigitalInput(0);
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

