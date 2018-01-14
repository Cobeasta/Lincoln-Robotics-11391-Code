package org.firstinspires.ftc.TeamCode_reformatted.commands;

import org.firstinspires.ftc.TeamCode_reformatted.BasicCommand;
import org.firstinspires.ftc.TeamCode_reformatted.Hardware;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Date;
import java.util.Locale;

/**
 * Created by Coby on 12/1/2017.
 */

public class GyroTurn implements BasicCommand {
    double degrees;
    long startTime;
    double radians;
    Hardware robot;
    double power;
    Orientation angles;
    double initialDegree;
    Date date;

    private final double degrees2Radians = (2*Math.PI)/360;


    public GyroTurn(Hardware robot, double degrees, double power){
        this.degrees = degrees;
        this.power = power;
        this.robot = robot;


    }
    @Override
    public void init() {
        startTime = new Date().getTime();
       initialDegree =  robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    degrees += initialDegree;
    }

    @Override
    public void execute(Hardware robot) {
        this.robot = robot;
        if(new Date().getTime() - startTime < .02 ){
            initialDegree = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        }
            robot.leftDrive.setPower(-power);
            robot.rightDrive.setPower(power);
        angles   = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        System.out.println("ANGLE: " + angles.firstAngle);

    }

    @Override
    public boolean isFinished() {
        if(degrees < 0){
        return degrees - angles.firstAngle >= 0;
        }
        else if(degrees > 0){
            return degrees -angles.firstAngle <= 0;
        }
        else{
            return true;
        }
    }

    @Override
    public void stop() {
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
    }
    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }
    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}
