package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterMotorSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Victor pulleyMotor = new Victor(RobotMap.LIFTER_VICTOR_PORT);
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public void pulleyUp() {
    	// Makes pulley go up
    	pulleyMotor.set(0.2);
    }
    
    public void pulleyDown() {
    	// Makes pulley go down
    	pulleyMotor.set(-0.2);
    }
    
    public void pulleyStop() {
    	// Makes pulley stop
    	pulleyMotor.set(0);
    }
}

