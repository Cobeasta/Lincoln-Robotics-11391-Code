package org.firstinspires.ftc.team_Teleop_Reformat.auto_command_groups;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.TeamCode_reformatted.commands.TimedDriveStraight;
import org.firstinspires.ftc.TeamCode_reformatted.commands.ToggleClaws;
import org.firstinspires.ftc.TeamCode_reformatted.commands.UseLiftByTime;
import org.firstinspires.ftc.TeamCode_reformatted.commands.Wait;
import org.firstinspires.ftc.team_Teleop_Reformat.auto_command_groups.AutonomousCommandGroup;
import org.firstinspires.ftc.team_Teleop_Reformat.commands.BlueJewl;
import org.firstinspires.ftc.team_Teleop_Reformat.commands.GyroTurn;

/**
 * Created by Coby on 12/14/2017.
 */
@Autonomous(name = "blue left", group = "")
public class BlueLeftAuto extends AutonomousCommandGroup {
    @Override
    public void addCommands() {
        addSequential(new ToggleClaws(false));
        addSequential(new BlueJewl());
        addSequential(new Wait(2000));
        addSequential(new UseLiftByTime(.5, 700));
        addSequential(new Wait(1000));

        addSequential(new TimedDriveStraight(600, -.5));
        addSequential(new Wait(1000));
        addSequential(new GyroTurn(robot, -90, -.45));
        addSequential(new TimedDriveStraight(300, .6));
        addSequential(new ToggleClaws(true));
        addSequential(new Wait(1000));
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
