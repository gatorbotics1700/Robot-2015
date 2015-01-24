package org.usfirst.frc.team1700.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AlignmentMotorsSubsystem extends Subsystem {
	
	private Victor alignmentMotor1 = new Victor(1);
	private Victor alignmentMotor2 = new Victor(2);
	
	public void goForward() {
		alignmentMotor1.set(0.2);
		alignmentMotor2.set(0.2);
		
		
	}
	public void goBackward() {
		alignmentMotor1.set(-0.2);
		alignmentMotor2.set(-0.2);
		
	}
	
	public void stop() {
		alignmentMotor1.set(0);
		alignmentMotor2.set(0);
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

