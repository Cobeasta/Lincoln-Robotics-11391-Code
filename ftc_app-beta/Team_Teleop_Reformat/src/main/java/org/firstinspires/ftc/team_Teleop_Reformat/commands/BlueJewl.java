package org.firstinspires.ftc.team_Teleop_Reformat.commands;

import org.firstinspires.ftc.team_Teleop_Reformat.Hardware;

import java.util.Date;

/**
 * Created by Coby on 12/5/2017.
 */

public class BlueJewl implements BasicCommand {
    private boolean finished = false;
    Date date;
    Hardware robot;
    private long startTime;
    private boolean movedServo = false;

    public BlueJewl(){

    }
    @Override
    public void init() {
        date = new Date();
        startTime = date.getTime();
    }



    @Override
    public void execute(Hardware robot) {
        date = new Date();

      if(date.getTime() - startTime < 1000) {
          robot.jewlX.setPosition(.2);
      }

      else if (date.getTime() - startTime < 2000) {
            robot.jewlY.setPosition(.67);
        }

      else if(date.getTime() - startTime < 3000){
          if(robot.colorSensor.red() < robot.colorSensor.blue() && !movedServo){
              robot.jewlX.setPosition(.35);
              movedServo = true;
          }
          else if(robot.colorSensor.blue() < robot.colorSensor.red() && !movedServo){
              robot.jewlX.setPosition(.1);
              movedServo = true;
          }
      }
      else if(date.getTime() - startTime < 4000){
          robot.jewlY.setPosition(.3);
      }
      else if(date.getTime() - startTime < 5000){
          robot.jewlX.setPosition(.2);
      }
      else if(date.getTime() - startTime < 5500){
          robot.jewlY.setPosition(0);
      }

        else if(date.getTime() - startTime < 6000){
          finished = true;
      }

    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void stop() {

    }
//
//    motors = new HardwareMotors(hardwareMap);
//    motors.init();
//    servos = new HardwareServos(hardwareMap);
//    servos.init();
//    sensors = new HardwareSensors(hardwareMap);
//    sensors.init();
//
//
//    time = new ElapsedTime(0);
//
//
//
//    while(opModeIsActive()) {
//
//        time.reset();
//
//
//
//
//        servos.jewlX.setPosition(.2);
//
//        time.reset();
//
//        while (opModeIsActive() && time.milliseconds() < 500) ;
//
//        servos.jewlY.setPosition(.63);
//        time.reset();
//        while (opModeIsActive() && time.milliseconds() < 1000) ;
//
//        if (opModeIsActive() && sensors.colorSensor.red() > sensors.colorSensor.blue()) {
//            servos.jewlX.setPosition(1);
//            time.reset();
//            while (opModeIsActive() && time.milliseconds() < 1000) ;
//
//            servos.jewlX.setPosition(.2);
//            servos.jewlY.setPosition(0);
//            time.reset();
//
//            while (opModeIsActive() && time.milliseconds() < 1000) ;
//            servos.jewlX.setPosition(.5);
//
//        } else if(opModeIsActive()){
//            servos.jewlX.setPosition(0);
//            time.reset();
//            while (opModeIsActive() && time.milliseconds() < 1000) ;
//            servos.jewlX.setPosition(.2);
//            time.reset();
//            while (opModeIsActive() && time.milliseconds() < 250) ;
//            servos.jewlY.setPosition(0);
//        }

    }
