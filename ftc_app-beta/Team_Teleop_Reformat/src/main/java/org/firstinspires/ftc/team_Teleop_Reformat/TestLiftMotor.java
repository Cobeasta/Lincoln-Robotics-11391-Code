package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.TeamCode_reformatted.commands.RaiseLift;
import org.firstinspires.ftc.TeamCode_reformatted.commands.Wait;

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
