package org.firstinspires.ftc.team_Teleop_Reformat.auto_command_groups;

import org.firstinspires.ftc.team_Teleop_Reformat.commands.EncoderDrive;

/**
 * Created by Coby on 1/16/2018.
 */

public class TestEncoderDrive extends AutonomousCommandGroup {
    @Override
    public void addCommands() {
        addSequential(new EncoderDrive(robot, 300));
    }
}
