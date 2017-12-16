package org.firstinspires.ftc.TeamCode_reformatted;

import java.util.Date;

/**
 * Created by Coby on 12/11/2017.
 */

public class BasicPID {
   private Date time;
    private  long startTime;
    private long timeStamp;
    private long previousTime = 0;
    private  double setPoint;
    private  long output;
    private    double error;
    private   double previousError;
    private double area = 0;

    double kP, kI, kD;


    public void update(double input){
        time = new Date();
        timeStamp = time.getTime();


    }
    public double getValue(){
       return calculateP() +calculateI() + calculateD();
    }
    private double calculateP(){
        return  kP *error;
    }
    private double calculateI() {
     long deltaTime =  timeStamp - previousTime;
      double integral =  deltaTime * (error - previousError);
        area += integral;
        return area * kI;
    }
    private double calculateD(){
      double deltaError =  error - previousError;
        double deltaTime = timeStamp - previousTime;
        double derivitive = deltaError/deltaTime;
        return kD * derivitive;
    }
}
