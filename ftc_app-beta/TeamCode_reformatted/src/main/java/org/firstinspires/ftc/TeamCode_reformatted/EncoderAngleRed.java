package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.TeamCode_reformatted.commands.PIDTurn;
import org.firstinspires.ftc.TeamCode_reformatted.commands.RedJewl;
import org.firstinspires.ftc.TeamCode_reformatted.commands.TimedDriveStraight;
import org.firstinspires.ftc.TeamCode_reformatted.commands.ToggleClaws;
import org.firstinspires.ftc.TeamCode_reformatted.commands.UseLiftByTime;
import org.firstinspires.ftc.TeamCode_reformatted.commands.Wait;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by Coby on 1/28/2018.
 */
@Autonomous(name = "Red encoder Angle",group = "test")
public class EncoderAngleRed extends AutonomousCommandGroup {
    @Override
    public void addCommands() {
        addSequential(new RedJewl());
        addSequential(new ToggleClaws(false));
        addSequential(new UseLiftByTime(.5, 700));
        if(vuMark.equals(RelicRecoveryVuMark.LEFT))
            addSequential(new PIDTurn(20, robot, false));
        else if(vuMark.equals(RelicRecoveryVuMark.CENTER))
            addSequential(new PIDTurn(25, robot, false));
        else
            addSequential(new PIDTurn(30, robot, false));


            placeBlock();
    }
}
