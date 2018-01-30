package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.TeamCode_reformatted.commands.BlueJewl;
import org.firstinspires.ftc.TeamCode_reformatted.commands.EncoderDrive;
import org.firstinspires.ftc.TeamCode_reformatted.commands.PIDTurn;
import org.firstinspires.ftc.TeamCode_reformatted.commands.ToggleClaws;
import org.firstinspires.ftc.TeamCode_reformatted.commands.UseLiftByTime;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by Coby on 1/28/2018.
 */
@Autonomous(name = "Encoder Square Blue",group = "test")
public class EncoderSquareBlue extends AutonomousCommandGroup {
    @Override
    public void addCommands() {
        addSequential(new BlueJewl());
        addSequential(new ToggleClaws(false));
        addSequential(new UseLiftByTime(.5, 500));
        if(vuMark.equals(RelicRecoveryVuMark.LEFT))
        addSequential(new EncoderDrive(robot, -39));
        else if(vuMark.equals(RelicRecoveryVuMark.CENTER))
            addSequential(new EncoderDrive(robot, 45));
        else
            addSequential(new EncoderDrive(robot, 51));

        addSequential(new PIDTurn(90, robot, true));

        placeBlock();


    }
}
