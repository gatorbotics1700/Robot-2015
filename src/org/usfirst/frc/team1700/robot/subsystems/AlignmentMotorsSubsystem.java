package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AlignmentMotorsSubsystem extends Subsystem {
	
	private Talon alignmentTalon1 = new Talon(RobotMap.ALIGNMENT_TALON_1_ID);
	private Talon alignmentTalon2 = new Talon(RobotMap.ALIGNMENT_TALON_2_ID);
	private static final double TALON_SPEED = 0.2;
			
	
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

