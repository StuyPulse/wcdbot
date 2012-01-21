/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.stuy;


import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SendablePIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    
    public static final int LEFT_GEAR_SHIFT = 5;
    public static final int RIGHT_GEAR_SHIFT = 6;

    public static final int GYRO_CHANNEL = 1;
    
    public static final int GAMEPAD_PORT = 1;
    
    RobotDrive drive;
    Servo leftShift, rightShift;
    Joystick gamepad;
    Gyro gyro;
    
    SendablePIDController controller;
    
    double Kp;
    double Ki;
    double Kd;
    
    // TODO: implement gear switching (PWM ports 5 left and 6 right)
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        drive = new RobotDrive(FRONT_LEFT_MOTOR, REAR_LEFT_MOTOR, FRONT_RIGHT_MOTOR, REAR_RIGHT_MOTOR);
        leftShift = new Servo(LEFT_GEAR_SHIFT);
        rightShift = new Servo(RIGHT_GEAR_SHIFT);
        gamepad = new Joystick(GAMEPAD_PORT);
        gyro = new Gyro(GYRO_CHANNEL);
        controller = new SendablePIDController(Kp, Ki, Kd, gyro, new PIDOutput() {

            public void pidWrite(double output) {
                drive.arcadeDrive(-0.75, output);
            }
            
        }, 0.005);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        controller.enable();
    }
    
    public void disabledInit() {
        controller.disable();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        drive.tankDrive(gamepad, 2, gamepad, 4);
        
        
        if (gamepad.getRawButton(9)) {
            leftShift.set(0);
            rightShift.set(0);
        }
        else if (gamepad.getRawButton(10)) {
            leftShift.set(1);
            rightShift.set(1);
        }
        
        if(gamepad.getRawButton(6) && gamepad.getRawButton(8)) {
            controller.enable();
        }
        else if(gamepad.getRawButton(5) && gamepad.getRawButton(7)) {
            controller.disable();
            drive.arcadeDrive(0, 0);
        }
        
        if (gamepad.getRawButton(1)) {
            gyro.reset();
        }
        
        if (gamepad.getRawButton(2)) {
            System.out.println(gyro.getAngle());
        }
    }
    
}
