package org.firstinspires.ftc.TeamCode_reformatted.commands;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.TeamCode_reformatted.BasicCommand;
import org.firstinspires.ftc.TeamCode_reformatted.Hardware;
import org.firstinspires.ftc.TeamCode_reformatted.PIDController;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Date;

/**
 * Created by Coby on 12/28/2017.
 */

//Stem robotics
public class PIDTurn implements BasicCommand {
    long startTime = 0;
    double correction = 0;
    PIDController pid;
    private double initialDegree;
    private Hardware robot;
    private double degrees = 0;
    double accel = 0;
    Orientation angles;
    double leftDrive;
    double rightDrive;
   double p = .03225;
    double i =0;
    double d = 0;
    private double set;
    public PIDTurn(double angle, Hardware robot,  boolean right){

        set = angle;
        pid = new PIDController(p, i, d);
        pid.setSetpoint(set);
        pid.setTolerance(2);
        if(right){
            leftDrive = -1;
            rightDrive = 1;
        }
        else{
            leftDrive = 1;
            rightDrive = -1;
        }
    }
    @Override
    public void init() {
        startTime = new Date().getTime();
        pid.setSetpoint(set);
        pid.setOutputRange(0, 1);
        pid.setInputRange(-180, 180);
        pid.enable();
        initialDegree =  robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;



    }


    @Override
    public void execute(Hardware robot) {
        this.robot = robot;
        accel = robot.gyro.getAngularVelocity().zRotationRate;

        angles   = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        angles.firstAngle += initialDegree;
         correction = pid.performPID(angles.firstAngle);
        robot.leftDrive.setPower(-correction);
        robot.rightDrive.setPower(correction);

    }

    @Override
    public boolean isFinished() {
        return pid.onTarget() && new Date().getTime() - startTime >=.5;
    }

    @Override
    public void stop() {
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

    }
    public String addTelemetry(){
        String s = "";
        s+= "First angle" + initialDegree + "\n";
      if(angles != null) {
          s += "Current Angle: " + angles.firstAngle + "\n";
      }
        s+= "setpoint: " + set + "\n";
        s += "Acceleration" + accel;
        return s;

    }
    public void setTolerance(double tolerance){
        pid.setTolerance(tolerance);
    }

}
