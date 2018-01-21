package org.firstinspires.ftc.team_Teleop_Reformat.commands;

import org.firstinspires.ftc.TeamCode_reformatted.*;
import org.firstinspires.ftc.team_Teleop_Reformat.Hardware;

/**
 * Created by Coby on 1/16/2018.
 */

public class EncoderDrive implements BasicCommand {
    double distance;
    Hardware robot;
    private final int TICKS_TO_INCH = 1;

    public EncoderDrive(Hardware robot, double distance){
        this.distance = distance;
        this.robot = robot;
    }
    @Override
    public void init() {
        robot.leftDrive.setTargetPosition((int) (TICKS_TO_INCH*distance));
        robot.rightDrive.setTargetPosition((int) (TICKS_TO_INCH *distance));
    }

    @Override
    public void execute(Hardware robot) {
        robot.leftDrive.setPower(1);
        robot.rightDrive.setPower(1);
    }


    @Override
    public boolean isFinished() {
        return !robot.leftDrive.isBusy();
    }

    @Override
    public void stop() {

    }
}
