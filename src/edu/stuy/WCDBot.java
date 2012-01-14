/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.stuy;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class WCDBot extends IterativeRobot {
    public static final int FRONT_LEFT_MOTOR = 1;
    public static final int REAR_LEFT_MOTOR = 2;
    public static final int FRONT_RIGHT_MOTOR = 3;
    public static final int REAR_RIGHT_MOTOR = 4;
    
    public static final int GAMEPAD_PORT = 1;
    
    RobotDrive drive;
    Joystick gamepad;
    
    // TODO: implement gear switching (PWM ports 5 left and 6 right)
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        drive = new RobotDrive(FRONT_LEFT_MOTOR, REAR_LEFT_MOTOR, FRONT_RIGHT_MOTOR, REAR_RIGHT_MOTOR);
        gamepad = new Joystick(GAMEPAD_PORT);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        drive.tankDrive(gamepad, 2, gamepad, 4);
    }
    
}
