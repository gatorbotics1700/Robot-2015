package org.usfirst.frc.team1700.robot.commands;

import org.usfirst.frc.team1700.robot.Subsystems;
import org.usfirst.frc.team1700.robot.subsystems.LifterMotorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CalibrateLifterCommand extends Command {
	private LifterMotorSubsystem lifter;

    public CalibrateLifterCommand() {
        requires(Subsystems.lifter);
        lifter = Subsystems.lifter;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lifter.enable();
    	System.out.println("CALIBRATE STARTED -----------------");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	lifter.uncheckedMove(-4000 + lifter.getPosition());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return lifter.limitSwitchHit();
    }

    // Called once after isFinished returns true
    protected void end() {
    	lifter.stop();
    	lifter.zeroEncoders();
    }

    // Called when another command which requires one or more of the same subsystems is scheduled to run
    protected void interrupted() {
    	lifter.stop();
    }
}
