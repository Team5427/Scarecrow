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
public class MoveHead extends Command {
	
	public double speed;
	public static boolean pause = false;
	public static final double LEFT_TURN_TIME = 3.5;
	public static final double PAUSE_TURN_TIME = 3.0;
	
	//going to right until hit limit switch -> pause -> go left
	//going to left for timeout -> pause -> go right
	
	// - is clockwise (right)
	//+ is anticlockwise (left)
	
	public MoveHead(double speed) {
		// Use requires() here to declare subsystem dependencies
//		requires(Robot.m_subsystem);
		this.speed = speed;
		System.out.println(pause);
		
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		if(!pause) {	
			Robot.head.set(speed);
			if(speed>0) 
				setTimeout(LEFT_TURN_TIME);
		}
		else {
			Robot.head.set(0);
			setTimeout(PAUSE_TURN_TIME);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if(this.speed < 0 && !pause) 
			return !Robot.limitRight_Head.get();
		else 
			return this.isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		this.pause = !pause;
		double sp = -this.speed;
		if(this.pause) {
			sp = -sp;
		}
			
		new MoveHead(sp).start();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
