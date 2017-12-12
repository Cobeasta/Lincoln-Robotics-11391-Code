package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Coby on 11/24/2017.
 */
@TeleOp(name = "jewl Controls", group = "test")
public class JewlControls extends OpMode {
    double xPosition = 0;
    double yPosition = 0;
    final double claw_offset = .01;

    Servo jewlX;
    Servo jewlY;
    @Override
    public void init() {
        jewlX = hardwareMap.servo.get("jewl_x");
        jewlY = hardwareMap.servo.get("jewl_y");
    }

    @Override
    public void loop() {
        if(gamepad1.x){
            xPosition -= claw_offset;
        }
        if(gamepad1.b){
            xPosition += claw_offset;
        }
        if(gamepad1.a){
            yPosition -= claw_offset;
        }
        if(gamepad1.y){
            yPosition += claw_offset;
        }
        jewlX.setPosition(Range.clip(xPosition, -1, 1));
        jewlY.setPosition(Range.clip(yPosition, -1, 1));
        telemetry.addData("x Position", jewlX.getPosition());
        telemetry.addData("y position", jewlY.getPosition());
        telemetry.update();
    }
}
