package org.firstinspires.ftc.TeamCode_reformatted.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.configuration.MotorConfigurationType;

import org.firstinspires.ftc.TeamCode_reformatted.BasicCommand;
import org.firstinspires.ftc.TeamCode_reformatted.Hardware;

/**
 * Uses the encoder to move the lift to the proper position
 * Created by Coby on 12/9/2017.
 */

public class RaiseLift implements BasicCommand {
    Hardware robot;
    int targetPosition;
    private boolean up = false;
    public RaiseLift(boolean up){
        this.up = up;
        if(up){
            targetPosition = 0;
        }else{
            targetPosition = 50;
        }

    }
    @Override
    public void init() {

    }

    @Override
    public void execute(Hardware robot) {
        this.robot = robot;
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.liftMotor.setTargetPosition(targetPosition);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.liftMotor.setPower(.6);
    }

    @Override
    public boolean isFinished() {
        return robot.liftMotor.getCurrentPosition() - targetPosition == 0;
    }

    @Override
    public void stop() {
        robot.liftMotor.setPower(0);
    }
}
