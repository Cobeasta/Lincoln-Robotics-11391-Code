package org.firstinspires.ftc.TeamCode_reformatted;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Coby on 12/1/2017.
 */
//This is a stupid change
public class Hardware {

    HardwareMap hwMap;
    public BNO055IMU gyro;
   public Servo leftClaw;
    public Servo rightClaw;
    public  Servo jewlX;
    public Servo jewlY;
    public Servo relicFlip;
    public  Servo relicGrip;
    public   DcMotor leftDrive, rightDrive, liftMotor, relicMotor;
    public   ColorSensor colorSensor;
    public static final double ROBOT_MID_SERVO = .5;
    BNO055IMU.Parameters parameters;


    public Hardware(HardwareMap hwMap) {
        this.hwMap = hwMap;

    }
    public void init(){
      parameters  = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        gyro = hwMap.get(BNO055IMU.class, "gyro");
        gyro.initialize(parameters);


        leftClaw = hwMap.servo.get("left_claw");
        rightClaw = hwMap.servo.get("right_claw");
        jewlX = hwMap.servo.get("jewl_x");
        jewlY = hwMap.servo.get("jewl_y");
        relicFlip = hwMap.servo.get("relic_flip");
        relicGrip = hwMap.servo.get("relic_grip");


        leftDrive = hwMap.dcMotor.get("left_drive");
        rightDrive = hwMap.dcMotor.get("right_drive");
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightClaw.setDirection(Servo.Direction.REVERSE);
        liftMotor = hwMap.dcMotor.get("lift_motor");
        relicMotor = hwMap.dcMotor.get("relic_motor");
        colorSensor = hwMap.get(ColorSensor.class, "imu");

    }







}
