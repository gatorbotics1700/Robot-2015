package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterMotorSubsystem extends Subsystem {
	private CANTalon lifterTalon1 = new CANTalon(RobotMap.LIFTER_TALON_1_ID);
	private CANTalon lifterTalon2 = new CANTalon(RobotMap.LIFTER_TALON_2_ID);
	LifterLimitSwitchSubsystem limitSwitches;
	
	private static final double LIFTER_SPEED = 0.2;
	public static final double DEADBAND = 0.1;
	
	public LifterMotorSubsystem() {
		super();
		limitSwitches = Subsystems.lifterLimitSwitch;
	}
    
    public void lifterMove(double speed) {
    	if (safeToMove()) {
    		lifterTalon1.set(speed);
    		lifterTalon2.set(speed);
    	} else {
    		lifterStop();
    	}
    }
    
    public void lifterUp() {
    	// Makes lifter go up
    	lifterMove(LIFTER_SPEED);
    }
    
    public void lifterDown() {
    	// Makes lifter go down
    	lifterMove(-LIFTER_SPEED);
    }
    
    public void lifterStop() {
    	// Makes lifter stop
    	lifterTalon1.set(0);
		lifterTalon2.set(0);
    }
    
    private boolean safeToMove() {
    	return (!limitSwitches.wasTopSwitchHit() && !limitSwitches.wasBottomSwitchHit());
    }
    
    public void initDefaultCommand() {
        
    }
}

