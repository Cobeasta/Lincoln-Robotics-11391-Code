package org.firstinspires.ftc.TeamCode_reformatted.commands;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team_Teleop_Reformat.auto_command_groups.AutonomousCommandGroup;
import org.firstinspires.ftc.team_Teleop_Reformat.commands.BlueJewl;

/**
 * Created by Coby on 12/9/2017.
 */
@Autonomous(name = "blue",group = "auto")
public class BasicBlue extends AutonomousCommandGroup{
    public BasicBlue(){
        addSequential(new BlueJewl());
    }

    @Override
    public void addCommands() {

    }
}
