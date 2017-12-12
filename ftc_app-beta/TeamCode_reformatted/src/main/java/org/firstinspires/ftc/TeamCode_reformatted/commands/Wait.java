package org.firstinspires.ftc.TeamCode_reformatted.commands;

import org.firstinspires.ftc.TeamCode_reformatted.BasicCommand;
import org.firstinspires.ftc.TeamCode_reformatted.Hardware;

import java.util.Date;

/**
 * Created by Coby on 12/4/2017.
 */

/**
 * Waits the specified number of milliseconds
 * uses EPOCH to determine how much time has passed.
 *
 */
public class Wait implements BasicCommand {

    Date date;
    private boolean finished;
    long startTime;
    long duration;

    public Wait(long time){
        duration = time;
    }
    @Override
    public void init() {
        date = new Date();
        startTime = date.getTime();

    }

    @Override
    public void execute(Hardware robot) {
        date = new Date();
        if(date.getTime() - startTime >= duration){
            finished = true;
        }
        else{
            finished = false;
        }
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void stop() {

    }
}
