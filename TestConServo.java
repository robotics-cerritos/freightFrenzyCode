package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp

public class TestConServo extends LinearOpMode {

    private CRServo crservo;

    
    @Override
    public void runOpMode() {
        
        crservo = hardwareMap.crservo.get("crservo");
        
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            crservo.setPower(gamepad1.right_trigger-gamepad1.left_trigger);
            telemetry.addData("Servo: ",crservo.getPower());
            telemetry.update();
            idle();
        }
    }
}
