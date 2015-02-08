package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.ProximitySensorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReadProximitySensorCommand extends Command {
	
	private ProximitySensorSubsystem sensorR, sensorL;

    public ReadProximitySensorCommand() {
    	requires(Subsystems.drive);
        
        sensorR = new ProximitySensorSubsystem(RobotMap.PROXIMITY_SENSOR_R);
        sensorL = new ProximitySensorSubsystem(RobotMap.PROXIMITY_SENSOR_L);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	sensorR.updateRollingAverage();
    	sensorL.updateRollingAverage();
    	
    	System.out.println(sensorL.getDistance() + "\t" + sensorR.getDistance());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
