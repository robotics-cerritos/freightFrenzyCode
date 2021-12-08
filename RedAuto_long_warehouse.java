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

@Autonomous(name="RedAuto_long_warehouse", group="Linear Opmode")

public class Allen_redAuto extends LinearOpMode {

    // Variables go here
    private ElapsedTime runtime = new ElapsedTime();
    
    //Wheels
    private DcMotor  leftFront;
    private DcMotor  rightFront;
    private DcMotor  leftRear;
    private DcMotor  rightRear;
    
    private Servo intakeS;
    
    private DcMotor caraselM;
    
    @Override
    public void runOpMode() {
        
        // HardwareMapping goes here
        leftFront = hardwareMap.dcMotor.get("leftF");
        rightFront = hardwareMap.dcMotor.get("rightF");
        leftRear = hardwareMap.dcMotor.get("leftR");
        rightRear = hardwareMap.dcMotor.get("rightR");
        
        intakeS = hardwareMap.servo.get("intakeS");
        
        caraselM = hardwareMap.dcMotor.get("caraselMotor");
        
        
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        
        waitForStart();
        
        intakeS.setPosition(0.5);
        
        runtime.reset();
      /*
        //Strafe Right
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
        
        //Stop
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
        while (opModeIsActive() && (runtime.seconds()<=0.25)) {
            strafe(0.7);
            telemetry.addData("LeftF: ",leftFront.getPower() );
            telemetry.addData("RightF: ",rightFront.getPower() );
            telemetry.addData("LeftR: ",leftRear.getPower() );
            telemetry.addData("RightR: ",rightRear.getPower() );
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
        runtime.reset();
        
        //stop
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
        
        //Go Backward
        while (opModeIsActive() && (runtime.seconds()<=0.4)) {
            allPower(-1);
            telemetry.addData("LeftF: ",leftFront.getPower() );
            telemetry.addData("RightF: ",rightFront.getPower() );
            telemetry.addData("LeftR: ",leftRear.getPower() );
            telemetry.addData("RightR: ",rightRear.getPower() );
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
        runtime.reset();
        
        //stop
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
        
        //Turn Carasel Motor
        while (opModeIsActive() && (runtime.seconds()<=5)) {
            caraselM.setPower(1);
            telemetry.addData("LeftF: ",leftFront.getPower() );
            telemetry.addData("RightF: ",rightFront.getPower() );
            telemetry.addData("LeftR: ",leftRear.getPower() );
            telemetry.addData("RightR: ",rightRear.getPower() );
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
        runtime.reset();
        
        //Stop Carasel Motor
        while (opModeIsActive() && (runtime.seconds()<=0.3)) {
            caraselM.setPower(0);
            telemetry.addData("LeftF: ",leftFront.getPower() );
            telemetry.addData("RightF: ",rightFront.getPower() );
            telemetry.addData("LeftR: ",leftRear.getPower() );
            telemetry.addData("RightR: ",rightRear.getPower() );
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
        runtime.reset();
        
        //Go Forward
        while (opModeIsActive() && (runtime.seconds()<=0.4)) {
            allPower(1);
            telemetry.addData("LeftF: ",leftFront.getPower() );
            telemetry.addData("RightF: ",rightFront.getPower() );
            telemetry.addData("LeftR: ",leftRear.getPower() );
            telemetry.addData("RightR: ",rightRear.getPower() );
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
        runtime.reset();
        
        //Strafe Right
        while (opModeIsActive() && (runtime.seconds()<=0.6)) {
            strafe(-0.7);
            telemetry.addData("LeftF: ",leftFront.getPower() );
            telemetry.addData("RightF: ",rightFront.getPower() );
            telemetry.addData("LeftR: ",leftRear.getPower() );
            telemetry.addData("RightR: ",rightRear.getPower() );
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
        runtime.reset();
        
        //stop
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
        
        //Move forward
        while (opModeIsActive() && (runtime.seconds()<= 1.6)) {
            allPower(1.0);
            telemetry.addData("LeftF: ",leftFront.getPower() );
            telemetry.addData("RightF: ",rightFront.getPower() );
            telemetry.addData("LeftR: ",leftRear.getPower() );
            telemetry.addData("RightR: ",rightRear.getPower() );
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }

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

    public void turnLeft(){
        leftFront.setPower(-1.0);
        leftRear.setPower(-1.0);
        rightFront.setPower(1.0);
        rightRear.setPower(1.0);

    }

    public void turnRight(){
        leftFront.setPower(1.0);
        leftRear.setPower(1.0);
        rightFront.setPower(-1.0);
        rightRear.setPower(-1.0);
    }
}
