package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Coby on 10/28/2017.
 */
@Autonomous(name = " bleu cheese auto", group = "blue team")
public class JewlBlue extends LinearOpMode {
    float hsvValues[] = {0F, 0F, 0F};
    final float values[] = hsvValues;
    final double SCALE_FACTOR = 255;


    @Override
    public void runOpMode() throws InterruptedException {
        HardwareLiftBot robot = new HardwareLiftBot(hardwareMap);
        robot.init();
        Color.RGBToHSV((int) (robot.colorSensor.red() * SCALE_FACTOR),
                (int) (robot.colorSensor.green() * SCALE_FACTOR),
                (int) (robot.colorSensor.blue() * SCALE_FACTOR),
                hsvValues);

        ElapsedTime runtime = new ElapsedTime();

        waitForStart();
        runtime.reset();

        robot.jewlX.setPosition(.85);
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 2 && !isStopRequested()){

        }
        Color.RGBToHSV((int) (robot.colorSensor.red() * SCALE_FACTOR), (int) (robot.colorSensor.green() * SCALE_FACTOR),
                (int) (robot.colorSensor.blue() * SCALE_FACTOR), hsvValues);

        double motorPower;
        if(robot.colorSensor.red() > robot.colorSensor.blue()){
            motorPower = .25;
        }
        else{
            motorPower = -.25;

        }
        runtime.reset();
        while(opModeIsActive() &&runtime.seconds() < 1 && !isStopRequested()){
            robot.leftMotor.setPower(motorPower);
            robot.rightMotor.setPower(motorPower);

        }

        robot.jewlX.setPosition(-.90);

        while(runtime.seconds() < 2 && opModeIsActive() && !isStopRequested()){
            robot.leftMotor.setPower(0);
            robot.rightMotor.setPower(0);
        }


//        while (opModeIsActive()) {
//            // convert the RGB values to HSV values.
//            // multiply by the SCALE_FACTOR.
//            // then cast it back to int (SCALE_FACTOR is a double)
//            Color.RGBToHSV((int) (colorSensor.red() * SCALE_FACTOR),
//                    (int) (colorSensor.green() * SCALE_FACTOR),
//                    (int) (colorSensor.blue() * SCALE_FACTOR),
//                    hsvValues);
//
//            // send the info back to driver station using telemetry function.
//            telemetry.addData("Distance (cm)",
//                    String.format(Locale.US, "%.02f", distanceSensor.getDistance(DistanceUnit.CM)));
//            telemetry.addData("Alpha", colorSensor.alpha());
//            telemetry.addData("Red  ", colorSensor.red());
//            telemetry.addData("Green", colorSensor.green());
//            telemetry.addData("Blue ", colorSensor.blue());
//            telemetry.addData("Hue", hsvValues[0]);
//
//            // change the background color to match the color detected by the RGB sensor.
//            // pass a reference to the hue, saturation, and value array as an argument
//            // to the HSVToColor method.
//
//
//            telemetry.update();
    }
}
