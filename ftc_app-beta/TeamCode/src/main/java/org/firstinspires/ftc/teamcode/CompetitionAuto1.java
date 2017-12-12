package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Coby on 11/28/2017.
 */
@Autonomous(name = "Red Team Autonomous", group = "test")
public class CompetitionAuto1 extends LinearOpMode {
    VuforiaTrackable relicTemplate;
    //Hardware used
    HardwareMotors motors;
    HardwareServos servos;
    HardwareSensors sensors;
    VuforiaLocalizer vuforia;

    ElapsedTime time;


    //jewl servo constants and fields
    final double LOW_MOTOR_POWER = .2;
    final double HIGH_MOTOR_POWER = 1;

    //Color Constants and fields
    float hsvValues[] = {0F, 0F, 0F};
    final float values[] = hsvValues;
    final double SCALE_FACTOR = 255;




    @Override
    public void runOpMode() throws InterruptedException {

            motors = new HardwareMotors(hardwareMap);
            motors.init();
            servos = new HardwareServos(hardwareMap);
            servos.init();
            sensors = new HardwareSensors(hardwareMap);
            sensors.init();


        time = new ElapsedTime(0);

        setUpVuForia();
        waitForStart();

        while(!isStopRequested() && !opModeIsActive()){

        }
        while(opModeIsActive()) {

            time.reset();
            while (opModeIsActive() && time.seconds() < 5) {

            }
            servos.leftClaw.setPosition(1);
            servos.rightClaw.setPosition(1);
            time.reset();
            while(opModeIsActive() && time.seconds() < 1){

            }
            time.reset();
            while (opModeIsActive() && time.seconds() < .5) {
                motors.liftMotor.setPower(-.5);

            }
            motors.liftMotor.setPower(0);
            servos.jewlX.setPosition(.2);

            time.reset();

            while (opModeIsActive() && time.milliseconds() < 500) ;

            servos.jewlY.setPosition(.63);
            time.reset();
            while (opModeIsActive() && time.milliseconds() < 1000) ;

            if (opModeIsActive() && sensors.colorSensor.red() > sensors.colorSensor.blue()) {
                servos.jewlX.setPosition(1);
                time.reset();
                while (opModeIsActive() && time.milliseconds() < 1000) ;

                servos.jewlX.setPosition(.2);
                servos.jewlY.setPosition(0);
                time.reset();

                while (opModeIsActive() && time.milliseconds() < 1000) ;
                servos.jewlX.setPosition(.5);

            } else if(opModeIsActive()){
                servos.jewlX.setPosition(0);
                time.reset();
                while (opModeIsActive() && time.milliseconds() < 1000) ;
                servos.jewlX.setPosition(.2);
                time.reset();
                while (opModeIsActive() && time.milliseconds() < 250) ;
                servos.jewlY.setPosition(0);
            }


            time.reset();
            while (opModeIsActive() && time.milliseconds() < 600) {
                motors.leftMotor.setPower(.25);
                motors.rightMotor.setPower(.25);
            }
            if(opModeIsActive()) {
                motors.leftMotor.setPower(0);
                motors.rightMotor.setPower(0);
            }

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

            while (opModeIsActive() && vuMark == RelicRecoveryVuMark.UNKNOWN) {
                telemetry.addData("vumark: ", "not found");
                telemetry.update();
                vuMark = RelicRecoveryVuMark.from(relicTemplate);

            }

                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */
                telemetry.addData("VuMark", "%s visible", vuMark);
                telemetry.update();

                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();

            /* We further illustrate how to decompose the pose into useful rotational and
                 * tdewrtgt56ranslational components */
                if (pose != null) {
                    VectorF trans = pose.getTranslation();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                    // Extract the X, Y, and Z components of the offset of the target relative to the robot
                    double tX = trans.get(0);
                    double tY = trans.get(1);
                    double tZ = trans.get(2);

                    // Extract the rotational components of the target relative to the robot
                    double rX = rot.firstAngle;
                    double rY = rot.secondAngle;
                    double rZ = rot.thirdAngle;
                    String position = format(pose);

                    telemetry.addData("pose", format(pose));
                }



            time.reset();

            telemetry.addData("hello", "world");
            telemetry.addData("vuforia", vuMark);
            while (opModeIsActive() &&time.seconds() < 1) {
                motors.leftMotor.setPower(.25);
                motors.rightMotor.setPower(.25);

            }
            motors.leftMotor.setPower(0);
            motors.rightMotor.setPower(0);

            if (opModeIsActive() && vuMark == RelicRecoveryVuMark.LEFT) {
                while (opModeIsActive() && time.seconds() < 1) {
                    motors.leftMotor.setPower(.35);
                    motors.rightMotor.setPower(.35);
                }
            } else if (opModeIsActive() &&vuMark == RelicRecoveryVuMark.CENTER) {
                while (opModeIsActive() && time.seconds() < 1.2) {
                    motors.leftMotor.setPower(.3);
                    motors.rightMotor.setPower(.3);
                }
            } else if (opModeIsActive() &&vuMark == RelicRecoveryVuMark.RIGHT) {
                while (opModeIsActive() && time.seconds() < 1.3) {
                    motors.leftMotor.setPower(.25);
                    motors.rightMotor.setPower(.25);
                }
            }
            time.reset();
            while (opModeIsActive() && time.seconds() < 1) {
                motors.leftMotor.setPower(.25);
                motors.rightMotor.setPower(-.25);

            }
            time.reset();
            while (opModeIsActive() && time.seconds() < 1) {
                motors.leftMotor.setPower(.25);
                motors.rightMotor.setPower(.25);
            }
            servos.leftClaw.setPosition(-1);
            servos.rightClaw.setPosition(-1);
            time.reset();
            while (opModeIsActive() && time.seconds() < .5) {

                motors.leftMotor.setPower(-.25);
                motors.rightMotor.setPower(-.25);
            }
            motors.leftMotor.setPower(0);
            motors.rightMotor.setPower(0);
            time.reset();
            while (opModeIsActive() && time.seconds() < .5) {
                motors.leftMotor.setPower(.25);
                motors.rightMotor.setPower(.25);
            }
            while (opModeIsActive() && time.seconds() < .5) {
                motors.leftMotor.setPower(-.25);
                motors.rightMotor.setPower(-.25);
            }
            motors.leftMotor.setPower(0);
            motors.rightMotor.setPower(0);


        }

    }














    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }

    public void setUpVuForia(){
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
         VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = "Abli3rX/////AAAAGVlaOnN+gUH7qUnLECTcHV57zM3P4JmT66WDt/Zz1MHzkKkO9ViTkj6iExGxIMy" +
                "aYMj9RbThtSUiG+en9xXOm0Cf31ohkRL8UIyL/AeOoQw3yrmuPhmZ5lCVUH2TXDvKfQbsWzLfUOelt5hSL0aQWeWxP7VRaF2OUVTa" +
                "V6pvOoRzG5bxmjWN/5FNkzBVAUzBvrlVHnY0PJ8BiDKCj2mImMTH+MURCP8bXXEQMc3/+0fVteILoruSNgN9z/4C2CrsGdsoji9NwZ+u" +
                "tbsatPutoOF9sw3ms6zjncsjiQuIDQa9okBiDxCnYWDGiTOFftp64DVrTVgUbmJlHYwFaiOhkaQNzHu3+sL50MIRtN2c0FeF";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);


        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        relicTrackables.activate();

    }



}
