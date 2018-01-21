package org.firstinspires.ftc.team_Teleop_Reformat;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.team_Teleop_Reformat.commands.RaiseLift;
import org.firstinspires.ftc.TeamCode_reformatted.commands.Wait;
import org.firstinspires.ftc.team_Teleop_Reformat.auto_command_groups.AutonomousCommandGroup;

/**
 * Created by Coby on 12/12/2017.
 */
@Autonomous(name = "test lift motor", group = "test")
@Disabled
public class TestLiftMotor extends AutonomousCommandGroup {
    public TestLiftMotor(){

    }

    @Override
    public void addCommands() {
        addSequential(new RaiseLift(robot, true));
        addSequential(new Wait(3000));
        addSequential(new RaiseLift(robot, false));
    }
}
