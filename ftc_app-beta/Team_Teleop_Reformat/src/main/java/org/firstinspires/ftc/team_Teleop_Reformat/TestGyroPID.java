package org.firstinspires.ftc.team_Teleop_Reformat;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team_Teleop_Reformat.commands.PIDTurn;
import org.firstinspires.ftc.team_Teleop_Reformat.auto_command_groups.AutonomousCommandGroup;

/**
 * Created by Coby on 12/28/2017.
 */
@Autonomous(name = "test gyro pid", group = "test")
public class TestGyroPID extends AutonomousCommandGroup {
PIDTurn pidTurn;
    @Override
    public void addCommands() {
        pidTurn = new PIDTurn(90, robot, .03225, 0, 0, true);
        addSequential(pidTurn);
    }
    @Override
    public void addTelemetry(){
        telemetry.addData("pid:, ", pidTurn.addTelemetry());
    }
}
