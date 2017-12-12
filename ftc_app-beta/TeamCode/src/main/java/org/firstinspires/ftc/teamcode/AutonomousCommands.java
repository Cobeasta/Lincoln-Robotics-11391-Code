package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Coby on 10/28/2017.
 */

public class AutonomousCommands {
    ElapsedTime time;
    HardwareLiftBot hardware;

    /**
     * Creates the Autonomous commands class. Pass in the hardware and call the various methods in
     * order to send commands to an autonomous linear op mode.
     * @param hw
     */
    public AutonomousCommands(HardwareLiftBot hw, ElapsedTime et){
        hardware = hw;
        time = et;
        hw = null;

    }

    /**
     * 1 ft fast:
     * 1 ft medium:
     * 1 ft slow:
     *
     * Turns the robot for a specified duration, power and direction
     * @param d direction to move
     * @param duration How long to move
     * @param power The power/speed at which to move
     */
    public void turn(Direction d,double duration, double power){
        time.reset();
        while(time.seconds() < duration) {
            switch (d) {
                case LEFT:
                    hardware.leftMotor.setPower(-power);
                    hardware.rightMotor.setPower(power);
                    break;
                case RIGHT:
                    hardware.leftMotor.setPower(power);
                    hardware.rightMotor.setPower(-power);
                    break;
            }
        }
    }

    /**
     * 90 degrees:
     * 45 degrees:
     * 60 degrees:
     * 30 degrees:
     * TODO Find how much power for how much time will turn each of the above measurements
     *
     * Drive forward or pass in negative value for backwards
     * @param power The power/ speed at which to move
     * @param elapsedTime The time to turn for
     */
    public void driveForward(double power, double elapsedTime){
        time.reset();
        while(time.seconds() < elapsedTime){
                hardware.leftMotor.setPower(power);
            hardware.rightMotor.setPower(power);
        }
    }


    /**
     * Direction enumeration representing left as counter clockwise, and right as clockwise.
     */
    public enum Direction{LEFT, RIGHT}{

    }



}
