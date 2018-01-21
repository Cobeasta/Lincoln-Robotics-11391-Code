package org.firstinspires.ftc.team_Teleop_Reformat.commands;


import org.firstinspires.ftc.team_Teleop_Reformat.Hardware;
import org.firstinspires.ftc.team_Teleop_Reformat.PIDController;
import org.firstinspires.ftc.team_Teleop_Reformat.commands.BasicCommand;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by Coby on 12/28/2017.
 */

//Stem robotics
public class PIDTurn implements BasicCommand {
    double correction = 0;
    PIDController pid;
    private double p, i, d;
    private double initialDegree;
    private Hardware robot;
    private double degrees = 0;
    private double accel = 0;
    Orientation angles;
    double leftDrive;
    double rightDrive;
    private double set;
    public PIDTurn(double angle, Hardware robot, double p, double i, double d, boolean right){
        this.robot = robot;
        this.p = p;
        this.i = i;
        this.d = d;
        set = angle;
        pid = new PIDController(p, i, d);
        pid.setSetpoint(set/2);
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
        pid.setSetpoint(set);
        pid.setOutputRange(0, 1);
        pid.setInputRange(-180, 180);
        pid.enable();
        initialDegree =  robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;



    }


    @Override
    public void execute(Hardware robot) {
        angles   = robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
         correction = pid.performPID(angles.firstAngle);
        robot.leftDrive.setPower(-correction);
        robot.rightDrive.setPower(correction);

    }

    @Override
    public boolean isFinished() {
        return correction < .1;
    }

    @Override
    public void stop() {

    }
    public String addTelemetry(){
        String s = "";
        s+= "Setpoint: " + set + "\n";
        s+= pid.performPID();
        s+= "\n";
        s+= set - initialDegree + "\n";
        if(angles != null){
            s+= angles.firstAngle;
        }
        return s;

    }
    public void setTolerance(double tolerance){
        pid.setTolerance(tolerance);
    }

}
