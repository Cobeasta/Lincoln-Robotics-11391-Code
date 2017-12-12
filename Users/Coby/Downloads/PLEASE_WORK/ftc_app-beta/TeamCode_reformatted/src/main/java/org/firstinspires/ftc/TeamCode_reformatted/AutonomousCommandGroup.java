package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.ArrayList;

/**Steps to create CommandGroup
 * Created by Coby on 12/4/2017.
 */

public class AutonomousCommandGroup extends OpMode {
    State state = State.INIT;
    ArrayList<BasicCommand> commands;
    BasicCommand currCommand;
    protected Hardware robot;

    public AutonomousCommandGroup(){
        commands = new ArrayList<BasicCommand>();

    }
    @Override
    public void init() {
         robot = new Hardware(hardwareMap);
        robot.init();

        telemetry.addData("calibration done", "");

        telemetry.update();
        currCommand = commands.get(0);

    }

    @Override
    public void loop() {
        switch(state){

            case INIT:
                currCommand.init();
                state = State.EXECUTE;
                break;

            case EXECUTE:
                currCommand.execute(robot);
                if(currCommand.isFinished()){
                    state = State.STOP;
                }
                break;

            case STOP:
                currCommand.stop();
                commands.remove(currCommand);
                currCommand = null;
                if(commands.size() >= 1) {
                    currCommand = commands.get(0);
                }
                if(currCommand != null){
                    state = State.INIT;
                }
                else{
                    state = State.DONE;
                }
                break;

            case DONE:
                break;
        }

        telemetry.addData("state: ", state);

    }
    public void addSequential(BasicCommand command){
        commands.add(command);
    }
    public Hardware getHardware(){
        return new Hardware(hardwareMap);
    }

    /**
     * @deprecated
     * dOES NOT WORK YET
     * @param command1
     * @param command2
     */
    private void addConsecutive(BasicCommand command1, BasicCommand command2){

    }


    public enum State{
        INIT, EXECUTE, STOP, DONE;
    }
}
