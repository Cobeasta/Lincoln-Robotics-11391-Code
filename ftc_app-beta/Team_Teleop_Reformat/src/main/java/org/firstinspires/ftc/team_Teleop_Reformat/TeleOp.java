package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Coby on 12/9/2017.
 */

public class TeleOp extends OpMode {
    Hardware robot;
    @Override
    public void init() {
        robot = new Hardware(hardwareMap);
        robot.init();
        try {
            wait(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loop() {

    }
    public void tankDrive(){

    }
    public void tankPOV(){

    }

}
