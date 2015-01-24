package org.usfirst.frc.team1700.robot.subsystems;

import org.usfirst.frc.team1700.robot.Robot;
import org.usfirst.frc.team1700.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterMotorSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Talon lifterTalon1 = new Talon(RobotMap.LIFTER_TALON_1_ID);
	private Talon lifterTalon2 = new Talon(RobotMap.LIFTER_TALON_2_ID);
	private static final double LIFTER_SPEED = 0.2;
	public static final double DEADBAND = 0.1;
								

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public void pulleyUp() {
    	// Makes pulley go up
    	lifterTalon1.set(LIFTER_SPEED);
    	lifterTalon2.set(LIFTER_SPEED);
    }
    
    public void pulleyDown() {
    	// Makes pulley go down
    	lifterTalon1.set(-LIFTER_SPEED);
    	lifterTalon2.set(-LIFTER_SPEED);
    }
    
    public void pulleyStop() {
    	// Makes pulley stop
    	lifterTalon1.set(0);
    	lifterTalon2.set(0);
    }
    
    public void lifterMove(double speed) {
		lifterTalon1.set(speed);
		lifterTalon2.set(speed);
    }
}

