package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp

public class template extends LinearOpMode {

    // Variables go here
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;



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
        
        // HardwareMapping goes here
        leftFront = hardwareMap.DcMotor.get("leftF");



        // Reversing direction goes here

        
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            

            leftFront.setPower(v1);
            rightFront.setPower(v2);
            leftRear.setPower(v3);
            rightRear.setPower(v4);
            
            telemetry.addData("LeftF: ",leftFront.getPower() );
            
            idle();
        }
    }
}
