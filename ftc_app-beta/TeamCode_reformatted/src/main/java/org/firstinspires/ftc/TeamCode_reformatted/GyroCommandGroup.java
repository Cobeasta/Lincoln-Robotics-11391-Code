package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.TeamCode_reformatted.commands.GyroTurn;
import org.firstinspires.ftc.TeamCode_reformatted.commands.Wait;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by Coby on 12/12/2017.
 */
@Autonomous(name = "gyro turn", group = "test")
@Disabled
public class GyroCommandGroup extends AutonomousCommandGroup {
    public GyroCommandGroup(){


    }


    @Override
    public void addCommands() {
        addSequential(new GyroTurn(robot, 60, .5));
        addSequential(new Wait(2000));
        addSequential(new GyroTurn(robot, -60, -.5));
    }
    @Override
    public void loop() {
        switch (state) {

            case INIT:
                currCommand.init();
                state = State.EXECUTE;
                break;

            case EXECUTE:
                currCommand.execute(robot);
                if (currCommand.isFinished()) {
                    state = State.STOP;
                }
                break;

            case STOP:
                currCommand.stop();
                commands.remove(currCommand);
                currCommand = null;
                if (commands.size() >= 1) {
                    currCommand = commands.get(0);
                }
                if (currCommand != null) {
                    state = State.INIT;
                } else {
                    state = State.DONE;
                }
                break;

            case DONE:
                break;
        }
        telemetry.addData("gyro", robot.gyro.getAngularOrientation(AxesReference.INTRINSIC,AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
    }
}
