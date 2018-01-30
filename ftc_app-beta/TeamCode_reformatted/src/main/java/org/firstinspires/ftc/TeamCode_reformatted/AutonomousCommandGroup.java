package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.TeamCode_reformatted.commands.TimedDriveStraight;
import org.firstinspires.ftc.TeamCode_reformatted.commands.ToggleClaws;
import org.firstinspires.ftc.TeamCode_reformatted.commands.UseLiftByTime;
import org.firstinspires.ftc.TeamCode_reformatted.commands.Wait;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;

/**Steps to create CommandGroup
 * Created by Coby on 12/4/2017.
 * extend Autonomous command group in a class.
 * Add @Autonomous(name = "name"group = "group"
 * Create fields below declaration of variables that need to be stored.
 * Override the constructor to pass in parameters for the command.
 * Set your above fields' values to what you pass into constructor.
 *
 * Override the addCommands  method.
 *      In here: call addSequential for each command. Pass in a new Command. For example addSequential(new TimedDriveStraight(500, .5);
 *
 * If you want to add telemetry, override the addTelemetry method and access fields from each of your commands.
 *
 */


public abstract class AutonomousCommandGroup extends OpMode {
   // public RelicRecoveryVuMark vuMark;


    State state = State.INIT;
    ArrayList<BasicCommand> commands;
    BasicCommand currCommand;
    protected Hardware robot;
    public RelicRecoveryVuMark vuMark;
   protected VuforiaLocalizer vuforia;
    VuforiaTrackables relicTrackables;
VuforiaTrackable relicTemplate;

    public AutonomousCommandGroup(){

        commands = new ArrayList<BasicCommand>();

    }
    public abstract void addCommands();


    @Override
    public void init() {
         robot = new Hardware(hardwareMap);
        robot.init();
        addCommands();
        telemetry.addData("calibration done", "");
        telemetry.update();
        currCommand = commands.get(0);

        //Initialize Vuforia
           /*
         * To start up Vuforia, tell it the view that we wish to use for camera monitor (on the RC phone);
         * If no camera monitor is desired, use the parameterless constructor instead (commented out below).
         */
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // OR...  Do Not Activate the Camera Monitor View, to save power
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        /*
         * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
         * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
         * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
         * web site at https://developer.vuforia.com/license-manager.
         *
         * Vuforia license keys are always 380 characters long, and look as if they contain mostly
         * random data. As an example, here is a example of a fragment of a valid key:
         *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
         * Once you've obtained a license key, copy the string from the Vuforia web site
         * and paste it in to your code onthe next line, between the double quotes.
         */
        parameters.vuforiaLicenseKey = "Abli3rX/////AAAAGVlaOnN+gUH7qUnLECTcHV57zM3P4JmT66WDt/Zz1MHzkKkO9ViTkj6iExGxIMy" +
                "aYMj9RbThtSUiG+en9xXOm0Cf31ohkRL8UIyL/AeOoQw3yrmuPhmZ5lCVUH2TXDvKfQbsWzLfUOelt5hSL0aQWeWxP7VRaF2OUVTa" +
                "V6pvOoRzG5bxmjWN/5FNkzBVAUzBvrlVHnY0PJ8BiDKCj2mImMTH+MURCP8bXXEQMc3/+0fVteILoruSNgN9z/4C2CrsGdsoji9NwZ+u" +
                "tbsatPutoOF9sw3ms6zjncsjiQuIDQa9okBiDxCnYWDGiTOFftp64DVrTVgUbmJlHYwFaiOhkaQNzHu3+sL50MIRtN2c0FeF";

        /*
         * We also indicate which camera on the RC that we wish to use.
         * Here we chose the back (HiRes) camera (for greater range), but
         * for a competition robot, the front camera might be more convenient.
         */
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        /**
         * Load the data set containing the VuMarks for Relic Recovery. There's only one trackable
         * in this data set: all three of the VuMarks in the game were created from this one template,
         * but differ in their instance id information.
         * @see VuMarkInstanceId
         */
         relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
         relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        telemetry.addData(">", "Press Play to start");
    }

    /**
     * This method manages the commands. This schedules the next command and calls the execute method of the command.
     */
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

         vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

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

    /**
     * Adds a command in sequential order.
     *
     * @param command The next command to run.
     */
    public void addSequential(BasicCommand command){
        commands.add(command);
    }
    public Hardware getHardware(){
        return new Hardware(hardwareMap);
    }

    /**
     * Override me!
     *
     * This is where you telemetry.addData(). This is called after each run of the robot.
     */
    public void addTelemetry(){

    }
    /**
     * @deprecated
     * dOES NOT WORK YET
     * @param command1
     * @param command2
     */
    private void addConsecutive(BasicCommand command1, BasicCommand command2){

    }

    /**
     * Returns the current state of the current command.
     */
    public enum State{
        INIT, EXECUTE, STOP, DONE;
    }

    /**
     * Places the cube in the slot in front of the robot.
     *  1. Drives forward
     *  2. opens claws.
     *  3. Drives backwards.
     *  4. closes claws and lowers lift.
     *  5. drives forward.
     *  6. back off the block.
     */
    protected void placeBlock(){

        //Place the block in the slot.
        addSequential(new TimedDriveStraight(300, .5));
        addSequential( new Wait(1000));
        addSequential( new ToggleClaws(true));
        addSequential(new Wait(1000));
        addSequential(new ToggleClaws(true));
        addSequential(new TimedDriveStraight(250, -.6));
        addSequential(new Wait(1000));
        addSequential(new ToggleClaws(false));
        addSequential(new UseLiftByTime(-.5, 500));
        addSequential(new Wait(500));
        addSequential(new TimedDriveStraight(500, .6));
        addSequential(new Wait(500));
        addSequential(new TimedDriveStraight(200, -.5));
    }
    protected void initializeLift(){
        addSequential(new ToggleClaws(false));
        addSequential(new Wait(1000));
        addSequential(new UseLiftByTime(.5, 500));

    }
}
