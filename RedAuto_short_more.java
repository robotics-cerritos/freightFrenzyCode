package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="RedAuto_short_more", group="Linear Opmode")

public class RedAuto_short_more extends LinearOpMode {

    // Variables go here
    private ElapsedTime runtime = new ElapsedTime();    
    private DcMotor  leftFront;
    private DcMotor  rightFront;
    private DcMotor  leftRear;
    private DcMotor  rightRear;
    
    private Servo intakeS;
    
    private DcMotor caraselM;
    
    @Override
    public void runOpMode() {
        
        // HardwareMapping goes here
        
        //Wheel motors
        leftFront = hardwareMap.dcMotor.get("leftF");
        rightFront = hardwareMap.dcMotor.get("rightF");
        leftRear = hardwareMap.dcMotor.get("leftR");
        rightRear = hardwareMap.dcMotor.get("rightR");
        
        intakeS = hardwareMap.servo.get("intakeS");
        
        //Carasel motor
        caraselM = hardwareMap.dcMotor.get("caraselMotor");
        
        //Reversing Direction
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        
        waitForStart();
        
        intakeS.setPosition(0.5);
        
        runtime.reset();
      /*
        //Strafing Right
        while (opModeIsActive() && (runtime.seconds()<=0.3)) {
            strafe(-0.2);
            telemetry.addData("LeftF: ",leftFront.getPower() );
            telemetry.addData("RightF: ",rightFront.getPower() );
            telemetry.addData("LeftR: ",leftRear.getPower() );
            telemetry.addData("RightR: ",rightRear.getPower() );
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
        runtime.reset();
        
        //Stopping
        while (opModeIsActive() && (runtime.seconds()<=0.3)) {
            allPower(0);
            telemetry.addData("LeftF: ",leftFront.getPower() );
            telemetry.addData("RightF: ",rightFront.getPower() );
            telemetry.addData("LeftR: ",leftRear.getPower() );
            telemetry.addData("RightR: ",rightRear.getPower() );
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
        runtime.reset();
        */
        
        //Strafe Left
        while (opModeIsActive() && (runtime.seconds()<=0.8)) {
            strafe(0.7);
            telemetry.addData("LeftF: ",leftFront.getPower() );
            telemetry.addData("RightF: ",rightFront.getPower() );
            telemetry.addData("LeftR: ",leftRear.getPower() );
            telemetry.addData("RightR: ",rightRear.getPower() );
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
        runtime.reset();
        
        //Stopping
        while (opModeIsActive() && (runtime.seconds()<=0.3)) {
            allPower(0);
            telemetry.addData("LeftF: ",leftFront.getPower() );
            telemetry.addData("RightF: ",rightFront.getPower() );
            telemetry.addData("LeftR: ",leftRear.getPower() );
            telemetry.addData("RightR: ",rightRear.getPower() );
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
        runtime.reset();
        
        //Going Forward
        while (opModeIsActive() && (runtime.seconds()<=1)) {
            allPower(1);
            telemetry.addData("LeftF: ",leftFront.getPower() );
            telemetry.addData("RightF: ",rightFront.getPower() );
            telemetry.addData("LeftR: ",leftRear.getPower() );
            telemetry.addData("RightR: ",rightRear.getPower() );
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
        
        //Stopping
        while (opModeIsActive() && (runtime.seconds()<=0.3)) {
            allPower(0);
            telemetry.addData("LeftF: ",leftFront.getPower() );
            telemetry.addData("RightF: ",rightFront.getPower() );
            telemetry.addData("LeftR: ",leftRear.getPower() );
            telemetry.addData("RightR: ",rightRear.getPower() );
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
        runtime.reset();
        
    }
    
    //All the wheels move in the same direction
    public void allPower(double power){
        leftFront.setPower(power);
        leftRear.setPower(power);
        rightFront.setPower(power);
        rightRear.setPower(power);
        
    }
    
    
    public void strafe(double power){
        leftFront.setPower(-power);
        leftRear.setPower(power);
        rightFront.setPower(power);
        rightRear.setPower(-power);
    }

    public void turn(double power){
        leftFront.setPower(-power);
        leftRear.setPower(-power);
        rightFront.setPower(power);
        rightRear.setPower(power);

    }
}
