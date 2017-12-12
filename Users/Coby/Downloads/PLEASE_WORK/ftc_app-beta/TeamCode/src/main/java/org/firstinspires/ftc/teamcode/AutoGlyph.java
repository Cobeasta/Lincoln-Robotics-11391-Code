package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.*;

import static com.sun.tools.javac.util.Constants.format;

/**
 * Created by Coby on 11/21/2017.
 */
@Autonomous(name = "autonomous glyph", group = "concept")
@Disabled
public class AutoGlyph extends LinearOpMode {
    int position;


    float hsvValues[] = {0F, 0F, 0F};
    final float values[] = hsvValues;
    final double SCALE_FACTOR = 255;
    VuforiaLocalizer vuforia;
    VuforiaTrackable relicTemplate;

    @Override
    public void runOpMode() throws InterruptedException {
        HardwareLiftBot robot = new HardwareLiftBot(hardwareMap);
        robot.init();


//
//        Color.RGBToHSV((int) (robot.colorSensor.red() * SCALE_FACTOR),
//                (int) (robot.colorSensor.green() * SCALE_FACTOR),
//                (int) (robot.colorSensor.blue() * SCALE_FACTOR),
//                hsvValues);
        telemetry.addData("initializing", "initializing");
        telemetry.update();
        ElapsedTime runtime = new ElapsedTime();
        robot.leftClaw.setPosition(-1);
        robot.rightClaw.setPosition(-1);
        setUpVuForia();
        waitForStart();
        robot.leftClaw.setPosition(1);
        robot.rightClaw.setPosition(1);
        runtime.reset();
        telemetry.addData("hello", "hello");
        telemetry.update();
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < .5){
            robot.liftMotor.setPower(-.3);
        }
        robot.liftMotor.setPower(0);



        /**
         * See if any of the instances of {@link relicTemplate} are currently visible.
         * {@link RelicRecoveryVuMark} is an enum which can have the following values:
         * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
         * UNKNOWN will be returned by {@link RelicRecoveryVuMark#from(VuforiaTrackable)}.
         */
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
      while(opModeIsActive() &&vuMark == RelicRecoveryVuMark.UNKNOWN){
          telemetry.addData("vumark: ", "not found");
          telemetry.update();
           vuMark = RelicRecoveryVuMark.from(relicTemplate);

      }
        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */
            telemetry.addData("VuMark", "%s visible", vuMark);
            telemetry.update();

                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */
            OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();

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
        }



        runtime.reset();

        telemetry.addData("hello", "world");
        telemetry.addData("vuforia", vuMark);
        if (vuMark == RelicRecoveryVuMark.LEFT){
            while(opModeIsActive() && runtime.seconds() < 1){
                robot.leftMotor.setPower(.35);
                robot.rightMotor.setPower(.35);
            }
        }else if (vuMark == RelicRecoveryVuMark.CENTER){
            while(opModeIsActive() && runtime.seconds() < 1.2) {
                robot.leftMotor.setPower(.3);
                robot.rightMotor.setPower(.3);
            }
        }else if (vuMark == RelicRecoveryVuMark.RIGHT){
            while(opModeIsActive() && runtime.seconds() < 1.3){
                robot.leftMotor.setPower(.25);
                robot.rightMotor.setPower(.25);
            }
    }
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 1){
            robot.leftMotor.setPower(.25);
            robot.rightMotor.setPower(-.25);

        }
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < 1){
            robot.leftMotor.setPower(.25);
            robot.rightMotor.setPower(.25);
        }
        robot.leftClaw.setPosition(-1);
        robot.rightClaw.setPosition(-1);
       runtime.reset();
        while(opModeIsActive() && runtime.seconds() < .5){

            robot.leftMotor.setPower(-.25);
            robot.rightMotor.setPower(-.25);
        }










    }
    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }

    public void setUpVuForia(){
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

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
