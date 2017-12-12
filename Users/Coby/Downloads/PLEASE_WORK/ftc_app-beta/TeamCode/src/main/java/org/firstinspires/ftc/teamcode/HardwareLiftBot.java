package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Coby on 10/7/2017.
 */

public class HardwareLiftBot {
    public static final double ROBOT_MID_SERVO = .5;

    DcMotor relicMotor;
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor liftMotor;
    Servo leftClaw;
    Servo rightClaw;
    HardwareMap hardware;
    Servo jewlX;
    Servo jewlY;
    ColorSensor colorSensor;
    Servo relicGrip;
    Servo relicFlip;

    //  DistanceSensor distanceSensor;
    public HardwareLiftBot(HardwareMap hwMap) {
        this.hardware = hwMap;
    }

    public void init() {
        relicGrip = hardware.servo.get("relic_grip");
        relicFlip = hardware.servo.get("relic_flip");
        relicMotor = hardware.dcMotor.get("relic_motor");
        leftMotor = hardware.dcMotor.get("left_drive");
        rightMotor = hardware.dcMotor.get("right_drive");
        liftMotor = hardware.dcMotor.get("lift_motor");
        leftClaw = hardware.servo.get("left_claw");
        rightClaw = hardware.servo.get("right_claw");
        jewlX = hardware.servo.get("jewl_x");
        jewlY = hardware.servo.get("jewl_y");

        colorSensor = hardware.get(ColorSensor.class, "imu");
        rightClaw.setDirection(Servo.Direction.REVERSE);
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //   distanceSensor = hardware.get(DistanceSensor.class, "imu");
        //       Toast.makeText(hardware.appContext, "done with init", Toast.LENGTH_SHORT);

    }



}




