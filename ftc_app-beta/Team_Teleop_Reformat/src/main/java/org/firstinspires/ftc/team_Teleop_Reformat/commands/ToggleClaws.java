package org.firstinspires.ftc.TeamCode_reformatted.commands;

import org.firstinspires.ftc.team_Teleop_Reformat.commands.BasicCommand;
import org.firstinspires.ftc.TeamCode_reformatted.Hardware;

/**
 * Created by Coby on 12/13/2017.
 */

public class ToggleClaws implements BasicCommand {
    private boolean open;
    public ToggleClaws(boolean open){
        this.open = open;
    }
    @Override
    public void init() {

    }

    @Override
    public void execute(Hardware robot) {
        if(open) {
            robot.leftClaw.setPosition(0);
            robot.rightClaw.setPosition(0);
        }
        else{
            robot.leftClaw.setPosition(1);
            robot.rightClaw.setPosition(1);
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void stop() {

    }
}
