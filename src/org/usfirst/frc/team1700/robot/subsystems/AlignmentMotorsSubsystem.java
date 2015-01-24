package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AlignmentMotorsSubsystem extends Subsystem {
	
	private CANTalon alignmentTalon1; 
	private CANTalon alignmentTalon2;
	
	private static final double TALON_SPEED = 0.2;
			
	public AlignmentMotorsSubsystem(boolean isLong){
    	if(isLong){
        	alignmentTalon1 = new CANTalon(RobotMap.LONG_ALIGNMENT_TALON_1_ID);
        	alignmentTalon2 = new CANTalon(RobotMap.LONG_ALIGNMENT_TALON_2_ID);
    	} else {
    		alignmentTalon1 = new CANTalon(RobotMap.SHORT_ALIGNMENT_TALON_1_ID);
        	alignmentTalon2 = new CANTalon(RobotMap.SHORT_ALIGNMENT_TALON_2_ID);
    	}
	}
	
	public void goForward() {
		alignmentTalon1.set(TALON_SPEED);
		alignmentTalon2.set(TALON_SPEED);
	}
	
	public void goBackward() {
		alignmentTalon1.set(-TALON_SPEED);
		alignmentTalon2.set(-TALON_SPEED);
		
	}
	
	public void stop() {
		alignmentTalon1.set(0);
		alignmentTalon2.set(0);
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

