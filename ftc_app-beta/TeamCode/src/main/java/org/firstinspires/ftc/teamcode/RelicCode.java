package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Coby on 11/24/2017.
 */
@TeleOp(name = "relic code", group ="test")
public class RelicCode extends OpMode {
    private DcMotor relicMotor;
    private Servo flipServo;
    private Servo gripServo;
    @Override
    public void init() {
        relicMotor = hardwareMap.dcMotor.get("relic_motor");
        flipServo = hardwareMap.servo.get("flip_servo");
        gripServo = hardwareMap.servo.get("grip_servo");
    }

    @Override
    public void loop() {
        relicMotor.setPower(gamepad1.left_stick_y);
        if(gamepad1.x){
            gripServo.setPosition(1);

        }
        if(gamepad1.b){
            gripServo.setPosition(-1);

        }
        if(gamepad1.y){
            flipServo.setPosition(1);

        }
        if(gamepad1.a){
            flipServo.setPosition(-1);
        }
    }
}
