package org.firstinspires.ftc.TeamCode_reformatted.commands;

import org.firstinspires.ftc.TeamCode_reformatted.BasicCommand;
import org.firstinspires.ftc.TeamCode_reformatted.Hardware;

import java.util.Date;

/**
 * Created by Coby on 12/1/2017.
 */

public class TimedDriveStraight implements BasicCommand {
    Date date;
    long duration;
    Hardware robot;
    long startTime;
    double power;

    public TimedDriveStraight( long duration, double power){
        this.duration = duration;
        this.power = power;
    }


    @Override
    public void init() {
        date = new Date();
        startTime = date.getTime();

    }

    @Override
    public void execute(Hardware robot) {
        date = new Date();
        this.robot = robot;
        robot.leftDrive.setPower(power);
        robot.rightDrive.setPower(power);

    }

    @Override
    public boolean isFinished() {
        return date.getTime() - startTime >= duration;
    }

    @Override
    public void stop() {
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
    }
}
