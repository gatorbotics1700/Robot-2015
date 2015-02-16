package org.usfirst.frc.team1700.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous extends CommandGroup {
    
    public  Autonomous() {
    	
    	addSequential(new CalibrateLifterCommand());
    	addSequential(new AutonomousLiftCommand());
    	addSequential(new AutonomousDriveCommand());
    	
    }
}
