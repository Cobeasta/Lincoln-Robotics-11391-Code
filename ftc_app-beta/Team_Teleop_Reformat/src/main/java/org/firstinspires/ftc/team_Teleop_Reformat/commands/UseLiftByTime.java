package org.firstinspires.ftc.TeamCode_reformatted.commands;

import org.firstinspires.ftc.team_Teleop_Reformat.commands.BasicCommand;
import org.firstinspires.ftc.TeamCode_reformatted.Hardware;

import java.util.Date;

/**
 * Created by Coby on 12/11/2017.
 */

public class UseLiftByTime implements BasicCommand{
    long startTime;
    double power;
    long duration;
    Hardware robot;
    long currTime;
    public UseLiftByTime(double power, long duration){
        this.power = power;
        this.duration = duration;
    }
    @Override
    public void init() {
        startTime = new Date().getTime();
    }

    @Override
    public void execute(Hardware robot) {
        currTime = new Date().getTime();
        this.robot = robot;
        robot.liftMotor.setPower(-power);

    }

    @Override
    public boolean isFinished() {
        return (new Date().getTime() - startTime) >= duration;
    }

    @Override
    public void stop() {
        robot.liftMotor.setPower(0);
    }
}
