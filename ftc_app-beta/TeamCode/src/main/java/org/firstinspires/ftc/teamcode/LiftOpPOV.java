package org.firstinspires.ftc.teamcode;

import android.media.AudioManager;
import android.media.SoundPool;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Coby on 10/7/2017.
 * This is the op mode for a POV control of the lift bot
 * It used one analog stick to control driving and 1 for the lift.
 * It toggles "fast" and 'slow' drive with y
 * a sets the jewl servo to the inward xPosition
 * the bumpers control the claws on the lift
 */
@TeleOp(name="Lift POV", group="lift_bot")
public class LiftOpPOV extends OpMode {
    private boolean isFast = false;
    private double motorScaleFactor;
    private  double claw_offset = 0.5;
    private final double claw_speed  = 0.02;

    HardwareLiftBot robot;
   // SoundPool sounds;
   // public int beepid;
    ElapsedTime runtime;
    @Override
    public void init() {
        motorScaleFactor = 1;
         runtime = new ElapsedTime();
        robot = new HardwareLiftBot(hardwareMap);
        robot.init();
        isFast = true;
     //   sounds = new SoundPool(1, AudioManager.USE_DEFAULT_STREAM_TYPE, 0);
     //   beepid = sounds.load(hardwareMap.appContext, R.raw.sax, 1);
      //  runtime.reset();


    }

    @Override
    public void loop() {
        //Temperary variables
        double left;
        double right;
        double max;

        //Toggles "fast mode" and "slow mode"
        if(gamepad1.y){
            if(isFast == true){
                isFast = false;
            }
            else{
                isFast = true;
            }
            if(isFast){
                motorScaleFactor = 1;
            }
            else{
                motorScaleFactor = .5;
            }
        }
        //sets the jewl servo to its retracted xPosition in case it gets caught after auto
       if(gamepad1.a){
         //   robot.jewlServo.setPosition(-.90);
        }
        telemetry.addData("fast speed: ", isFast);
        telemetry.addData("motor multiplier", motorScaleFactor);
//        if(gamepad1.y){
//            sounds.stop(AudioManager.USE_DEFAULT_STREAM_TYPE);
//        }




    //    rotationVector = HardwareLiftBot.getRotationVector();
//        telemetry.addData("x", rotationVector[0]);
//        telemetry.addData("y", rotationVector[1]);
//        telemetry.addData("z", rotationVector[2]);
//        telemetry.addData("osdj", rotationVector[2]);

//Drive control part

        double drive = -gamepad1.left_stick_y;
        double turn = gamepad1.left_stick_x;
        turn *= .5;
        left = drive + turn;
        right = drive - turn;
        max = Math.max(left, right);

        if(max > 1){
            left /= max;
            right /= max;

        }
        left *= motorScaleFactor;
        right *= motorScaleFactor;
        robot.leftMotor.setPower(left);
        robot.rightMotor.setPower(right);

        //claw control part

        if(gamepad1.right_bumper){
            claw_offset += claw_speed;        }


        else if(gamepad1.left_bumper){
            claw_offset -= claw_speed;

        }
        if(gamepad1.right_bumper || gamepad1.left_bumper) {
            claw_offset = Range.clip(claw_offset, -1, 1);
            robot.leftClaw.setPosition(robot.ROBOT_MID_SERVO + claw_offset);
            robot.rightClaw.setPosition(robot.ROBOT_MID_SERVO + claw_offset);
        }

        //Lift control part
        robot.liftMotor.setPower(gamepad1.right_stick_y * .75);
        telemetry.addData("motor speed", left);
        telemetry.addData("motor speed", right);
    }
    @Override
    public void stop(){
        robot.leftClaw.setPosition(0);
        robot.rightClaw.setPosition(0);
    }
}
