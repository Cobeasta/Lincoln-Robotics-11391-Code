package org.firstinspires.ftc.team_Teleop_Reformat.auto_command_groups;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team_Teleop_Reformat.commands.EncoderDrive;

/**
 * Created by Coby on 1/16/2018.
 */
@Autonomous(name = "Test encoder", group = "test")
public class TestEncoderDrive extends AutonomousCommandGroup {
    @Override
    public void addCommands() {
        addSequential(new EncoderDrive(robot, 300));
    }
}
