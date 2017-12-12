package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by Coby on 11/21/2017.
 */
@Autonomous
@Disabled
public class TapeSensorClass extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        OpticalDistanceSensor tape = hardwareMap.get(OpticalDistanceSensor.class, "ods");
        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("tape light", tape.getLightDetected());

        }

    }
}
