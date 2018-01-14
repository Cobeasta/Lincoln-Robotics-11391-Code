package org.firstinspires.ftc.TeamCode_reformatted.commands;

import org.firstinspires.ftc.TeamCode_reformatted.BasicCommand;
import org.firstinspires.ftc.TeamCode_reformatted.Hardware;

import java.util.Date;

/**
 * Created by Coby on 12/5/2017.
 */

public class TimeDriveTurn implements BasicCommand {
    Date date;
    private long startTime;
    private Hardware robot;
    private long duration;
    private double power;

    /**
     * Left is positive
     * @param duration
     * @param power
     */
    public TimeDriveTurn(long duration, double power){

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
        this.robot = robot;
        date = new Date();
        robot.leftDrive.setPower(power);
        robot.rightDrive.setPower(-1 *(power));
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
