package org.firstinspires.ftc.TeamCode_reformatted.commands;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.TeamCode_reformatted.BasicCommand;
import org.firstinspires.ftc.TeamCode_reformatted.Hardware;

/**
 * Created by Coby on 1/16/2018.
 */

public class EncoderDrive implements BasicCommand {
    int distance;
    Hardware robot;
    private final int TICKS_TO_INCH = 44;
    private int currPositionn;

    public EncoderDrive(Hardware robot, int distance){
        this.distance = distance;
        this.robot = robot;
    }
    @Override
    public void init() {
        robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        currPositionn = robot.leftDrive.getCurrentPosition();

        robot.leftDrive.setTargetPosition(currPositionn +TICKS_TO_INCH*(distance));
        robot.rightDrive.setTargetPosition(currPositionn +TICKS_TO_INCH *(distance));

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
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
    }
    public double getPosition(){
        return robot.leftDrive.getCurrentPosition();
    }
}
