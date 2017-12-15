package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.TeamCode_reformatted.commands.RaiseLift;
import org.firstinspires.ftc.TeamCode_reformatted.commands.Wait;

/**
 * Created by Coby on 12/12/2017.
 */
@Autonomous(name = "test lift motor", group = "test")
public class TestLiftMotor extends AutonomousCommandGroup {
    public TestLiftMotor(){
        addSequential(new RaiseLift(true));
        addSequential(new Wait(1000));
        addSequential(new RaiseLift(false));
    }

    @Override
    public void addCommands() {

    }
}
