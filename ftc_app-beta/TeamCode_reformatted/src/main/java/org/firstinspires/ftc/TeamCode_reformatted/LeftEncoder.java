package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.vuforia.VuMarkTarget;

import org.firstinspires.ftc.TeamCode_reformatted.commands.BlueJewl;
import org.firstinspires.ftc.TeamCode_reformatted.commands.EncoderDrive;
import org.firstinspires.ftc.TeamCode_reformatted.commands.GyroTurn;
import org.firstinspires.ftc.TeamCode_reformatted.commands.TimedDriveStraight;
import org.firstinspires.ftc.TeamCode_reformatted.commands.ToggleClaws;
import org.firstinspires.ftc.TeamCode_reformatted.commands.UseLiftByTime;
import org.firstinspires.ftc.TeamCode_reformatted.commands.Wait;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;

/**
 * Created by Coby on 1/27/2018.
 */
@Autonomous(name = "Blue straight line",group = "test")
public class LeftEncoder extends AutonomousCommandGroup {
    private int distance;
    @Override
    public void addCommands() {
        addSequential(new ToggleClaws(false));
        addSequential(new BlueJewl());
        addSequential(new Wait(2000));
        addSequential(new UseLiftByTime(.5, 700));
        addSequential(new Wait(1000));

        addSequential(new EncoderDrive(robot, -29));

        addSequential(new Wait(1000));
        addSequential(new GyroTurn(robot, -90, -.45));
        addSequential(new TimedDriveStraight(300, .6));
        addSequential(new ToggleClaws(true));
        addSequential(new Wait(1000));
        addSequential(new TimedDriveStraight(250, -.6));
        addSequential(new Wait(1000));
        addSequential(new ToggleClaws(false));
        addSequential(new UseLiftByTime(-.5, 500));
        addSequential(new Wait(500));
        addSequential(new TimedDriveStraight(500, .6));
        addSequential(new Wait(500));
        addSequential(new TimedDriveStraight(200, -.5));

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


        //Vuforia stuff
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                if(vuMark.equals(RelicRecoveryVuMark.LEFT)){
                    distance = -29;
                }else if(vuMark.equals(RelicRecoveryVuMark.CENTER)){
                    distance = -34;

                }
                else{
                    distance = -39;

                }
                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */
            telemetry.addData("VuMark", "%s visible", vuMark);

                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */
            OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();

                /* We further illustrate how to decompose the pose into useful rotational and
                 * translational components */
            if (pose != null) {
                VectorF trans = pose.getTranslation();
                Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                // Extract the X, Y, and Z components of the offset of the target relative to the robot
                double tX = trans.get(0);
                double tY = trans.get(1);
                double tZ = trans.get(2);

                // Extract the rotational components of the target relative to the robot
                double rX = rot.firstAngle;
                double rY = rot.secondAngle;
                double rZ = rot.thirdAngle;
            }
        }
        else {
            telemetry.addData("VuMark", "not visible");
        }

        telemetry.addData("lift pos", robot.liftMotor.getCurrentPosition());
        telemetry.addData("state: ", state);
        telemetry.addData("target: ", robot.liftMotor.getTargetPosition());
        addTelemetry();
        telemetry.update();



    }
}
