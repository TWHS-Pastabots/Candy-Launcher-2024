package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.internal.system.Assert;

public class CandyHardware {
    public DcMotorEx frontLeft;
    public DcMotorEx frontRight;
    public DcMotorEx backLeft;
    public DcMotorEx backRight;
    public DcMotorEx[] driveMotors;

    public DcMotorEx flywheelRight;
    public DcMotorEx flywheelLeft;
    public Servo servoLeft;
    public Servo servoRight;

    public CandyHardware() {

    }

    public void init(HardwareMap hardwareMap) {
        Assert.assertNotNull(hardwareMap);
        initDriveMotors(hardwareMap);
        initSuperStructure(hardwareMap);
    }

    public void initDriveMotors(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotorEx.class, HardwareIDs.FRONT_LEFT);
        frontRight = hardwareMap.get(DcMotorEx.class, HardwareIDs.FRONT_RIGHT);
        backLeft = hardwareMap.get(DcMotorEx.class, HardwareIDs.BACK_LEFT);
        backRight = hardwareMap.get(DcMotorEx.class, HardwareIDs.BACK_RIGHT);

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        driveMotors = new DcMotorEx[] {frontLeft, frontRight, backRight, backLeft};

        for (DcMotorEx motor: driveMotors) {
            motor.setPower(0.0);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

    }
    public void initSuperStructure(HardwareMap hardwareMap)
    {
        flywheelLeft = hardwareMap.get(DcMotorEx.class, HardwareIDs.FLYWHEEL_LEFT);
        flywheelRight = hardwareMap.get(DcMotorEx.class, HardwareIDs.FLYWHEEL_RIGHT);
        servoRight = hardwareMap.get(Servo.class, HardwareIDs.SERVO_RIGHT);
        servoLeft = hardwareMap.get(Servo.class, HardwareIDs.SERVO_LEFT);

        flywheelLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        flywheelLeft.setPower(0.0);
        flywheelLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        flywheelLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flywheelRight.setDirection(DcMotorSimple.Direction.FORWARD);
        flywheelRight.setPower(0.0);
        flywheelRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        flywheelRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        servoLeft.setPosition(0.0);
        servoRight.setPosition(1.0);
    }
}
