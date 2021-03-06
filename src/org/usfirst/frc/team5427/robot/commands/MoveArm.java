/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5427.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class MoveArm extends Command {
	
	public double speed;
	
	public MoveArm(double speed) {
		// Use requires() here to declare subsystem dependencies
//		requires(Robot.m_subsystem);
		this.speed = speed;
		
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		setTimeout(1.0);

		Robot.arm.set(speed);
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		new MoveArm(-this.speed).start();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
