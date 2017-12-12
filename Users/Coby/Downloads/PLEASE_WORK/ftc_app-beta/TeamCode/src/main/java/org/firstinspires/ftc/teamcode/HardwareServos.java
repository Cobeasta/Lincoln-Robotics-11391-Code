package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Coby on 12/1/2017.
 */

public class HardwareServos {
    public static final double ROBOT_MID_SERVO = .5;
    HardwareMap hwMap;
    Servo leftClaw;
    Servo rightClaw;
    Servo jewlX;
    Servo jewlY;
    Servo relicGrip;
    Servo relicFlip;


    public HardwareServos(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }

    public void init() {
        leftClaw = hwMap.servo.get("left_claw");
        rightClaw = hwMap.servo.get("right_claw");
        relicGrip = hwMap.servo.get("relic_grip");
        relicFlip = hwMap.servo.get("relic_flip");
        jewlX = hwMap.servo.get("jewl_x");
        jewlY = hwMap.servo.get("jewl_y");
        relicGrip.setDirection(Servo.Direction.REVERSE);

        rightClaw.setDirection(Servo.Direction.REVERSE);
    }
}
