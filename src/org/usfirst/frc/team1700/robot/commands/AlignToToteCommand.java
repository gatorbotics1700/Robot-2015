package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.RobotMap;
import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.ProximitySensorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 */
public class AlignToToteCommand extends Command {

	private ProximitySensorSubsystem sensorR, sensorL;
	private static final double DEADBAND = 0.5; //cm
	
    public AlignToToteCommand() {
        requires(Subsystems.drive);
        
        sensorR = new ProximitySensorSubsystem(RobotMap.PROXIMITY_SENSOR_R);
        sensorL = new ProximitySensorSubsystem(RobotMap.PROXIMITY_SENSOR_L);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// update averages
    	sensorR.updateRollingAverage();
    	sensorL.updateRollingAverage();
    	
    	sensorR.printDistance();
    	sensorL.printDistance();
    	
    	if ((sensorR.getDistance() - sensorL.getDistance()) > DEADBAND) {
    		//Left should move forward
//    		Subsystems.drive.alignLeft();
    	} else if ((sensorR.getDistance() - sensorL.getDistance()) < -DEADBAND) {
    		//Right should move forward
//    		Subsystems.drive.alignRight();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(sensorR.getDistance() - sensorL.getDistance()) < DEADBAND);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Subsystems.drive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Subsystems.drive.stop();
    }
}
