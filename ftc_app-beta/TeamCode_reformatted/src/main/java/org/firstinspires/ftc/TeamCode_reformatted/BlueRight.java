package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.TeamCode_reformatted.commands.BlueJewl;
import org.firstinspires.ftc.TeamCode_reformatted.commands.GyroTurn;
import org.firstinspires.ftc.TeamCode_reformatted.commands.RedJewl;
import org.firstinspires.ftc.TeamCode_reformatted.commands.TimedDriveStraight;
import org.firstinspires.ftc.TeamCode_reformatted.commands.ToggleClaws;
import org.firstinspires.ftc.TeamCode_reformatted.commands.UseLiftByTime;
import org.firstinspires.ftc.TeamCode_reformatted.commands.Wait;

/**
 * Created by Coby on 12/14/2017.
 */
@Autonomous(name = "blue angled position", group = "")
public class BlueRight extends AutonomousCommandGroup {
    @Override
    public void addCommands() {
        addSequential(new ToggleClaws(false));
        addSequential( new BlueJewl());
        addSequential(new UseLiftByTime(.5, 700));
        addSequential(new Wait(1000));
        addSequential(new TimedDriveStraight(1000, -.25));
        addSequential(new Wait(1000));
        addSequential(new GyroTurn(robot, 150, .55));
        addSequential(new Wait(1000));
        addSequential(new TimedDriveStraight(1000, .6));

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
