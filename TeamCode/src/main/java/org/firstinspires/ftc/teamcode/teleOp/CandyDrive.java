package org.firstinspires.ftc.teamcode.TeleOp;
// ip to connect is 192.168.43.1
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.CandyHardware;

@TeleOp(name = "Candy Drive")
public class CandyDrive extends OpMode {
    CandyHardware candyHardware;

    @Override
    public void init() {
        candyHardware = new CandyHardware();
        candyHardware.init(hardwareMap);

        telemetry.addData("Status", "initialized");
        telemetry.update();
    }

    public void start() {
        telemetry.addData("Status ", "started");
        telemetry.update();
    }

    @Override
    public void loop() {
        double y = gamepad1.left_stick_y; // This is reversed
        double x = gamepad1.left_stick_x; // Counteract imperfect strafing
        double z = -gamepad1.right_stick_x;

        double leftFrontPower = y + x + z;
        double leftBackPower = y - x + z;

        double rightFrontPower = y - x - z;
        double rightBackPower = y + x - z;

        if (Math.abs(leftFrontPower) > 1 || Math.abs(leftBackPower) > 1 ||
                Math.abs(rightFrontPower) > 1 || Math.abs(rightBackPower) > 1 ){
            // Find the largest power
            double max;
            max = Math.max(Math.abs(leftFrontPower), Math.abs(leftBackPower));
            max = Math.max(Math.abs(rightFrontPower), max);
            max = Math.max(Math.abs(rightBackPower), max);

            // Everything is Positive, do not worry about signs
            leftFrontPower /= max;
            leftBackPower /= max;
            rightFrontPower /= max;
            rightBackPower /= max;
        }
        if(gamepad1.dpad_left){
            leftFrontPower = -1;
            rightBackPower = -1;
            leftBackPower = 1;
            rightFrontPower = 1;
        }
        else if(gamepad1.dpad_right){
            leftFrontPower = 1;
            rightBackPower = 1;
            leftBackPower = -1;
            rightFrontPower = -1;
        }
        else if (gamepad1.dpad_up)
        {
            leftFrontPower = -1;
            rightBackPower = -1;
            leftBackPower = -1;
            rightFrontPower = -1;
        }
        else if(gamepad1.dpad_down)
        {
            leftFrontPower = 1;
            leftBackPower = 1;
            rightBackPower = 1;
            rightFrontPower = 1;
        }

        candyHardware.frontLeft.setPower(leftFrontPower);
        candyHardware.backLeft.setPower(leftBackPower);
        candyHardware.frontRight.setPower(rightFrontPower);
        candyHardware.backRight.setPower(rightBackPower);

        candyHardware.flywheelLeft.setPower(gamepad2.right_trigger * 1);
        candyHardware.flywheelRight.setPower(gamepad2.right_trigger * 1);
        candyHardware.flywheelLeft.setPower(gamepad2.left_trigger * .5);
        candyHardware.flywheelRight.setPower(gamepad2.left_trigger * .5);

        boolean pushCandy = gamepad2.square;
        if(pushCandy) {
            candyHardware.servoRight.setPosition(1.0);
            candyHardware.servoLeft.setPosition(0.0);
        }
        else {
            candyHardware.servoRight.setPosition(0.0);
            candyHardware.servoLeft.setPosition(1.0);
        }
    }
}
