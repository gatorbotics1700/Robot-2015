package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterPotentiometerSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private AnalogPotentiometer pot = new AnalogPotentiometer(RobotMap.LIFTER_POTENTIOMETER_PORT);
	private final int level1 = 1;
	private final int level0 = 0;
	public boolean isAtLevel1() {
		return (pot.get() >= level1);
	}
	public boolean isAtLevel0() {
		return (pot.get() <= level0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

