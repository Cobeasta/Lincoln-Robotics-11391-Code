package org.firstinspires.ftc.TeamCode_reformatted.commands;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.TeamCode_reformatted.AutonomousCommandGroup;

/**
 * Created by Coby on 12/4/2017.
 */
@Autonomous(name ="sample command group", group = "test")
public class SampleCommandGroup extends AutonomousCommandGroup {
    public SampleCommandGroup(){
        addSequential(new Wait(5000));
    }
}
