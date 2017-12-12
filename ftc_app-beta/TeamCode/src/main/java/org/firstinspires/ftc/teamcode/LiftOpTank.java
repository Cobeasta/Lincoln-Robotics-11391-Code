package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Coby on 10/7/2017.
 */
@TeleOp(name = "Lift Op tank PLEASE WORK", group = "teleop")
public class LiftOpTank extends OpMode {
    private boolean isFast = true;
    private double motorScaleFactor;





    double claw_offset = 0.5;
    double claw_speed = .02;
    //HardwareLiftBot robot;
    HardwareMotors motors;
    HardwareServos servos;
    HardwareSensors sensors;
    @Override
    public void init() {
        isFast = true;
        //robot = new HardwareLiftBot(hardwareMap);
        //robot.init();
        motors = new HardwareMotors(hardwareMap);
        motors.init();
        servos = new HardwareServos(hardwareMap);
        servos.init();
        sensors = new HardwareSensors(hardwareMap);
        sensors.init();
    }

    @Override
    public void loop() {
        if(gamepad1.y){
            if(isFast){
                isFast= false;
            }
            else {
                isFast = true;
            }
            /*if(isFast) {
                motorScaleFactor = 1;
            }
            else{
                motorScaleFactor = .5;
            }*/
        }

        if(isFast) {
            motorScaleFactor = 1;
        }
        else{
            motorScaleFactor = .5;
        }

        motors.leftMotor.setPower(-gamepad1.left_stick_y * motorScaleFactor);
        motors.rightMotor.setPower(-gamepad1.right_stick_y * motorScaleFactor);



        if(gamepad2.right_bumper){
            claw_offset += claw_speed;
        }

        else if(gamepad2.left_bumper){
            claw_offset -= claw_speed;

        }

        claw_offset = Range.clip(claw_offset, 0, 1);
        if(gamepad2.left_bumper || gamepad2.right_bumper){
            servos.leftClaw.setPosition(servos.ROBOT_MID_SERVO + claw_offset);
            servos.rightClaw.setPosition(servos.ROBOT_MID_SERVO + claw_offset);

        }
        motors.liftMotor.setPower(gamepad2.left_stick_y);
    }

    public void drive() {


    }
}
