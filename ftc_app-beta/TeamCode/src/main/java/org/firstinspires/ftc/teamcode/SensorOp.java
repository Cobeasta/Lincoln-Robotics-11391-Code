package org.firstinspires.ftc.teamcode;

import android.content.Context;
import android.graphics.Path;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Coby on 10/15/2017.
 */

public class SensorOp extends OpMode {
    float[] gyroPosition;
    @Override
    public void init() {
    gyroPosition = new float[3];
        try {
            SensorManager sensorManager;
            sensorManager =
                    (SensorManager) hardwareMap.appContext.getSystemService(Context.SENSOR_SERVICE);
            sensorManager.registerListener(orientationListener, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                    SensorManager.SENSOR_DELAY_GAME);
        } catch (Exception e) {

            telemetry.addData("Exception", e.toString());
        }
    }

    @Override
    public void loop() {

    }
    @Override
    public String toString(){
        String s = "";
        s+= "\n runtime = " + getRuntime();
        s+= "\nMotors are:  " +hardwareMap.getAll(DcMotor.class);
        s+= "\nservos are:  " +hardwareMap.getAll(Servo.class);
        s+= "\n x speed is: " + gyroPosition[0] + ", y speed is: " +gyroPosition[1] +", " + "z speed is " + gyroPosition[2];
        s+= "\n gamepad 1: " +gamepad1 + " gamepad 2: " + gamepad2;

        return s;
    }

    private SensorEventListener orientationListener = new SensorEventListener() {
        float deltaX, deltaY, deltaZ;
        float[] position = new float[3];

        @Override
        public void onSensorChanged(SensorEvent event) {
            deltaX = event.values[0];
            deltaY = event.values[1];
            deltaZ = event.values[2];
            position[0] += deltaX;
            position[1] += deltaY;
            position[2] += deltaZ;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
        public void reset(){
            position[0] = 0;
            position[1] = 0;
            position[2] = 0;
        }
    };
}



