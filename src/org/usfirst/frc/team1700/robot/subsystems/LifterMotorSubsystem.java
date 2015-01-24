package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterMotorSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Talon lifterMotor = new Talon(RobotMap.LIFTER_TALON_ID);
	private static final double LIFTER_SPEED = 0.2;
								

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public void pulleyUp() {
    	// Makes pulley go up
    	lifterMotor.set(LIFTER_SPEED);
    }
    
    public void pulleyDown() {
    	// Makes pulley go down
    	lifterMotor.set(-LIFTER_SPEED);
    }
    
    public void pulleyStop() {
    	// Makes pulley stop
    	lifterMotor.set(0);
    }
}

