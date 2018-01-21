package org.firstinspires.ftc.team_Teleop_Reformat.commands;

import org.firstinspires.ftc.TeamCode_reformatted.*;
import org.firstinspires.ftc.team_Teleop_Reformat.Hardware;

/**
 * Steps to create Command:
 * 1. implement this interface
 * 2. Add whatever parameters you require into the constructor. If you need to include hardware require
 *    the hardware parameter.
 * 3. Add whatever action to be performed in the execute method. Make sure you change the isFinished()
 *    method to return true at the proper time.
 * 4. Tie up any loose ends in the stop method.
 *    i
 *
 * Created by Coby on 12/1/2017.
 */

public interface  BasicCommand {
    /**
     * Called once before the command is run
     */
    public  void init();
    /**
     * Called repeatedly until isFinsished returns true
     */
    public  void execute(Hardware robot);

    /**
     * Set to return true once the opMode is complete.
     * @return
     */
    public  boolean isFinished();

    /**
     * Tie up any loose ends. Called once the command is done.
     */
    public  void stop();
}
