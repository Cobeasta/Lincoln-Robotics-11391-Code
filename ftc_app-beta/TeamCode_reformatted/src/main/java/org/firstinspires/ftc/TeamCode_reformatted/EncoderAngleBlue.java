package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.TeamCode_reformatted.commands.BlueJewl;
import org.firstinspires.ftc.TeamCode_reformatted.commands.EncoderDrive;
import org.firstinspires.ftc.TeamCode_reformatted.commands.GyroTurn;
import org.firstinspires.ftc.TeamCode_reformatted.commands.PIDTurn;
import org.firstinspires.ftc.TeamCode_reformatted.commands.TimedDriveStraight;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by Coby on 1/28/2018.
 */
@Autonomous(name = "Encoder angle blue",group = "test")
public class EncoderAngleBlue extends AutonomousCommandGroup {
    @Override
    public void addCommands() {
        addSequential(new BlueJewl());
        initializeLift();
        addSequential(new PIDTurn(180, robot, true));
        if(vuMark.equals(RelicRecoveryVuMark.LEFT))
            addSequential(new PIDTurn(10, robot, true));
        else if(vuMark.equals(RelicRecoveryVuMark.CENTER))
            addSequential(new PIDTurn(15, robot, true));
        else{
            addSequential(new PIDTurn(20, robot, true));

        }
        addSequential(new TimedDriveStraight(500, .5));
        addSequential(new PIDTurn(-10, robot, false));
        placeBlock();
    }
}
