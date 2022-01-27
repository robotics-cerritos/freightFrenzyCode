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

@Autonomous(name="RedAuto_Left_Blockade", group="Linear Opmode")

public class RedAuto_Right_Blockade extends LinearOpMode {

    // Variables go here
    private ElapsedTime runtime = new ElapsedTime();
    
    //Wheels
    private DcMotor  leftFront;
    private DcMotor  rightFront;
    private DcMotor  leftRear;
    private DcMotor  rightRear;
    
    private Servo outtakeS;
    
    private DcMotor caraselM;
    
    @Override
    public void runOpMode() {
        
        // HardwareMapping goes here
        
        //Wheel motors
        leftFront = hardwareMap.dcMotor.get("leftF");
        rightFront = hardwareMap.dcMotor.get("rightF");
        leftRear = hardwareMap.dcMotor.get("leftR");
        rightRear = hardwareMap.dcMotor.get("rightR");
        
        //Outtake Servo
        outtakeS = hardwareMap.servo.get("outtakeServo");
        
        //Carasel motor
        caraselM = hardwareMap.dcMotor.get("caraselMotor");
        
        //Reversing Direction
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        
        waitForStart();
        
        outtakeS.setPosition(0.58);
        
        runtime.reset();
      
      
        //Plan: Facing Blue Side - Forward, Rotate Right, Strafe Left, Forward
        
        
        //Forward
        while (opModeIsActive() && (runtime.seconds()<=2.2)) {
            allPower(1);
            teleUpdate();
        }
        runtime.reset();
        
        //stop
        while (opModeIsActive() && (runtime.seconds()<=0.3)) {
            allPower(0);
            teleUpdate();
        }
        runtime.reset();
        
        //Rotate Right
        while (opModeIsActive() && (runtime.seconds()<=0.5)) {
            turn(1);
            teleUpdate();
        }
        runtime.reset();
        
        //stop
        while (opModeIsActive() && (runtime.seconds()<=0.3)) {
            allPower(0);
            teleUpdate();
        }
        runtime.reset();
        
        //Strafe Left
        while (opModeIsActive() && (runtime.seconds()<=0.7)) {
            strafe(-0.7);
            teleUpdate();
        }
        runtime.reset();
        
        //stop
        while (opModeIsActive() && (runtime.seconds()<=0.3)) {
            allPower(0);
            teleUpdate();
        }
        runtime.reset();
        
        //Forward
        while (opModeIsActive() && (runtime.seconds()<=0.5)) {
            allPower(1);
            teleUpdate();
        }
        runtime.reset();
        
        
    }

    public void allPower(double power){
        leftFront.setPower(power);
        leftRear.setPower(power);
        rightFront.setPower(power);
        rightRear.setPower(power);
        
    }
    
    public void strafe(double power){
        leftFront.setPower(power);
        leftRear.setPower(-power);
        rightFront.setPower(-power);
        rightRear.setPower(power);
    }

    public void turn(double power){
        leftFront.setPower(power);
        leftRear.setPower(power);
        rightFront.setPower(-power);
        rightRear.setPower(-power);
    }
    
    public void teleUpdate(){
        telemetry.addData("LeftF: ",leftFront.getPower() );
        telemetry.addData("RightF: ",rightFront.getPower() );
        telemetry.addData("LeftR: ",leftRear.getPower() );
        telemetry.addData("RightR: ",rightRear.getPower() );
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
    
}
