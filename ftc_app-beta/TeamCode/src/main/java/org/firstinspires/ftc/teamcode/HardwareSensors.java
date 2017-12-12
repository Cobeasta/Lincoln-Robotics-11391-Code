package org.firstinspires.ftc.teamcode;

import android.hardware.SensorManager;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import android.util.Log;
/**
 * Created by Coby on 12/1/2017.
 */

public class HardwareSensors {
    ColorSensor colorSensor;

    HardwareMap hwMap;


    public HardwareSensors(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }

    public void init() {
        Log.e("HARDWARE:", "initializing sensor");

        colorSensor = hwMap.get(ColorSensor.class, "imu");

    }


}

