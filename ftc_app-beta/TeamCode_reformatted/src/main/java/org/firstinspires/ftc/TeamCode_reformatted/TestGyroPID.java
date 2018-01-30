package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.TeamCode_reformatted.commands.PIDTurn;

/**
 * Created by Coby on 12/28/2017.
 */
@Autonomous(name = "test gyro pid", group = "test")
public class TestGyroPID extends AutonomousCommandGroup{
PIDTurn pidTurn;
    @Override
    public void addCommands() {
        pidTurn = new PIDTurn(90, robot,  true);
        addSequential(pidTurn);
    }
    @Override
    public void addTelemetry(){
        telemetry.addData("pid:, ", pidTurn.addTelemetry());
    }
}
