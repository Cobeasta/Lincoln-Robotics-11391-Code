package org.firstinspires.ftc.TeamCode_reformatted.commands;

import org.firstinspires.ftc.TeamCode_reformatted.BasicCommand;
import org.firstinspires.ftc.TeamCode_reformatted.Hardware;

import java.util.Date;

/**
 * Created by Coby on 12/1/2017.
 */

public class GyroTurn implements BasicCommand {
    double degrees;
    Date date;
    long startTime;
    double radians;
    Hardware robot;
    double power;
    private final double degrees2Radians = (2*Math.PI)/360;
    public GyroTurn( double degrees, double power){
        this.degrees = degrees;
        this.power = power;


    }
    @Override
    public void init() {
        while(!robot.gyro.isGyroCalibrated()){

        }
    }

    @Override
    public void execute(Hardware robot) {
        this.robot = robot;

            robot.leftDrive.setPower(power);
            robot.rightDrive.setPower(-power);

    }

    @Override
    public boolean isFinished() {
        return true;

    }

    @Override
    public void stop() {

    }
}
