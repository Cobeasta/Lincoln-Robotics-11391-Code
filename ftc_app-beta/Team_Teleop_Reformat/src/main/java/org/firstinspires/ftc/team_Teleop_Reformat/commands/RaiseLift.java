package org.firstinspires.ftc.team_Teleop_Reformat.commands;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team_Teleop_Reformat.Hardware;
import org.firstinspires.ftc.team_Teleop_Reformat.commands.BasicCommand;

/**
 * Uses the encoder to move the lift to the proper position
 * Created by Coby on 12/9/2017.
 */

public class RaiseLift implements BasicCommand {
    Hardware robot;
    int targetPosition;
    private boolean up = false;
    public RaiseLift(Hardware robot, boolean up){
        this.robot = robot;
        this.up = up;
        if(up){
            targetPosition = -6000;
        }else{
            targetPosition = 0;
        }

    }
    @Override
    public void init() {
        robot.liftMotor.setTargetPosition(targetPosition);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void execute(Hardware robot) {
        this.robot = robot;

        robot.liftMotor.setPower(1);
    }

    @Override
    public boolean isFinished() {
        return robot.liftMotor.getCurrentPosition() - targetPosition == 0;
    }

    @Override
    public void stop() {
        robot.liftMotor.setPower(0);
    }
    public int getTargetPosition(){
        return targetPosition;
    }
}
