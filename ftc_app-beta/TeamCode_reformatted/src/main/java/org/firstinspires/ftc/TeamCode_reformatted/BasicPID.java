package org.firstinspires.ftc.TeamCode_reformatted;

import java.util.Date;

/**
 * Created by Coby on 12/11/2017.
 */

public class BasicPID {
    Date time;
    long startTime;
    long timeStamp;

    double setPoint;
    long output;
    double error;
    double previousError;

    double kP, kI, kD;
    public void update(double input){
        time = new Date();
        timeStamp = time.getTime();
    }
    public double getValue(){
       return calculateP() * kP + kI * calculateI() + kD * calculateD();
    }
    private double calculateP(){
        return 0;
    }
    private double calculateI(){
        return 0;
    }
    private double calculateD(){
        return 0;
    }
}
