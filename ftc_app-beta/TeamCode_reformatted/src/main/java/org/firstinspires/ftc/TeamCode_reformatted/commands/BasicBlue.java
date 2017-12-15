package org.firstinspires.ftc.TeamCode_reformatted.commands;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.TeamCode_reformatted.AutonomousCommandGroup;
import org.firstinspires.ftc.TeamCode_reformatted.BasicCommand;

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
