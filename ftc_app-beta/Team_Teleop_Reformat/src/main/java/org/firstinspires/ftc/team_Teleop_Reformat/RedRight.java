package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.TeamCode_reformatted.commands.GyroTurn;
import org.firstinspires.ftc.TeamCode_reformatted.commands.RedJewl;
import org.firstinspires.ftc.TeamCode_reformatted.commands.TimedDriveStraight;
import org.firstinspires.ftc.TeamCode_reformatted.commands.ToggleClaws;
import org.firstinspires.ftc.TeamCode_reformatted.commands.UseLiftByTime;
import org.firstinspires.ftc.TeamCode_reformatted.commands.Wait;

/**
 * Created by Coby on 12/13/2017.
 */
@Autonomous(name = "red angled position",group = "")
public class RedRight extends AutonomousCommandGroup {
    public RedRight(){



    }

    @Override
    public void addCommands() {
        addSequential(new ToggleClaws(false));
        addSequential( new RedJewl());
        addSequential(new UseLiftByTime(.5, 700));
        addSequential(new Wait(1500));
        addSequential(new TimedDriveStraight(950, .25));
        addSequential(new Wait(1000));
        addSequential( new GyroTurn(robot, 15, .7));
        addSequential(new Wait(1000));

        addSequential(new TimedDriveStraight(750, .6));
        addSequential(new Wait(1000));

        addSequential(new ToggleClaws(true));
        addSequential(new Wait(1000));
addSequential(new TimedDriveStraight(250, -.5));

        addSequential(new ToggleClaws(false));
        addSequential(new UseLiftByTime(-.5, 500));
        addSequential(new Wait(500));
        addSequential(new TimedDriveStraight(500, .6));
        addSequential(new Wait(500));
        addSequential(new TimedDriveStraight(150, -.5));
    }
}
