package org.usfirst.frc.team1700.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous sequence. Can be changed to drive forwards or backwards, or over the platform.
 */
public class Autonomous extends CommandGroup {
    
    public Autonomous() {
    	addSequential(new CalibrateLifterCommand());
    	addSequential(new AutonomousLiftCommand());
    	addSequential(new AutonomousDriveCommand());
    }
}
