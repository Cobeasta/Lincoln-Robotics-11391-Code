package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.TeamCode_reformatted.commands.*;

/**
 * Created by Coby on 12/13/2017.
 */
@Autonomous(name = "red left",group = "")
public class RedLeft extends AutonomousCommandGroup {
    public RedLeft(){

    }

    @Override
    public void addCommands() {
        addSequential( new ToggleClaws(false));
        addSequential(new RedJewl());
        addSequential(new Wait(2000));
        addSequential(new UseLiftByTime(.5, 700));
        addSequential(new Wait(1000));
        addSequential(new TimedDriveStraight(910, .6));
        addSequential(new GyroTurn(robot, -90, -.5));
        addSequential(new TimedDriveStraight(300, .5));
        addSequential( new Wait(1000));
        addSequential( new ToggleClaws(true));
        addSequential(new Wait(1000));
        addSequential(new ToggleClaws(true));
        addSequential(new TimedDriveStraight(250, -.6));
        addSequential(new Wait(1000));
        addSequential(new ToggleClaws(false));
        addSequential(new UseLiftByTime(-.5, 500));
        addSequential(new Wait(500));
        addSequential(new TimedDriveStraight(500, .6));
        addSequential(new Wait(500));
        addSequential(new TimedDriveStraight(200, -.5));

    }
}
