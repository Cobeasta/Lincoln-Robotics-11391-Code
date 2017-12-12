package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Coby on 10/21/2017.
 */


@TeleOp(name = "gyro op", group = "tele")
public class GyroOp extends OpMode {
    HardwareLiftBot robot;
    float[] rotationVector;

    @Override
    public void init() {
       robot = new HardwareLiftBot(hardwareMap);
        robot.init();
        robot  = new HardwareLiftBot(hardwareMap);
        rotationVector =new float[4];
    }

    @Override
    public void loop() {
        rotationVector = HardwareLiftBot.getRotationVector();
        telemetry.addData("x", rotationVector[0]);
        telemetry.addData("y", rotationVector[1]);
        telemetry.addData("z", rotationVector[2]);
        telemetry.addData("osdj", rotationVector[2]);
    }
}
