package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Coby on 12/1/2017.
 */

public class HardwareMotors {


    DcMotor relicMotor;
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor liftMotor;
    HardwareMap hwMap;

    public HardwareMotors(HardwareMap hwMapt) {
        this.hwMap = hwMapt;
    }

    public void init() {
            relicMotor = hwMap.dcMotor.get("relic_motor");
            leftMotor = hwMap.dcMotor.get("left_drive");
            rightMotor = hwMap.dcMotor.get("right_drive");
            liftMotor = hwMap.dcMotor.get("lift_motor");

            leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }



}
