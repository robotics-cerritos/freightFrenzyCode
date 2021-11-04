package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp

public class TestDrive extends LinearOpMode {

    private DcMotor  leftFront;
    private DcMotor  rightFront;
    private DcMotor  leftRear;
    private DcMotor  rightRear;
    
    private double r;
    private double robotAngle;
    private double rightX;
    private double v1;
    private double v2;
    private double v3;
    private double v4;
    
    private double power = 1.0;
    
    @Override
    public void runOpMode() {
        leftFront = hardwareMap.dcMotor.get("leftF");
        rightFront = hardwareMap.dcMotor.get("rightF");
        leftRear = hardwareMap.dcMotor.get("leftR");
        rightRear = hardwareMap.dcMotor.get("rightR");
        
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);
        
        
        
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if(gamepad1.a){
                power = 1.0;
            }
            if(gamepad1.b){
                power = 0.5;
            }
            r = Math.hypot(-gamepad1.left_stick_x, gamepad1.left_stick_y);
            robotAngle = Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI/4;
            rightX = -gamepad1.right_stick_x;
            v1 = power * (r * Math.cos(robotAngle) + rightX);
            v2 = power * (r * Math.sin(robotAngle) - rightX);
            v3 = power * (r * Math.sin(robotAngle) + rightX);
            v4 = power * (r * Math.cos(robotAngle) - rightX);
        
            leftFront.setPower(v1);
            rightFront.setPower(v2);
            leftRear.setPower(v3);
            rightRear.setPower(v4);
            
            telemetry.addData("LeftF: ",leftFront.getPower() );
            telemetry.addData("RightF: ",rightFront.getPower() );
            telemetry.addData("LeftR: ",leftRear.getPower() );
            telemetry.addData("RightR: ",rightRear.getPower() );
            telemetry.update();
            idle();
        }
    }
}
