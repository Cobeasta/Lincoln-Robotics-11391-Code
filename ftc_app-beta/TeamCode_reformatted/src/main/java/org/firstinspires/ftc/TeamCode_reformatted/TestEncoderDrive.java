package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.TeamCode_reformatted.commands.EncoderDrive;

/**
 * Created by Coby on 1/16/2018.
 */
@Autonomous(name = "Test encoder", group = "test")
public class TestEncoderDrive extends AutonomousCommandGroup {
    EncoderDrive drive;
    @Override
    public void addCommands() {
        drive = new EncoderDrive(robot, 29);
        addSequential(drive);
    }

@Override
public void addTelemetry(){
    telemetry.addData("Position", drive.getPosition());
}
}

