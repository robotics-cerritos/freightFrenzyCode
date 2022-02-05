package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

@TeleOp

public class Test_Auto extends LinearOpMode {

    // Variables go here  
    //Wheel Motors 
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;
    
    // Intake Servos
    private DcMotor intakeM;
    
    //Carasel Motor
    private DcMotor caraselM;
    
    //Default Speed Value
    private double speed = 1;

    //Carasel Boolean
    private boolean caraselMove=false;
    
    //Outtake Variables
    private DcMotor outtakeM;
    private Servo outtakeS;
    private double RAISE_MAX = -1750;
    private double RAISE_MIN = 10;
    private double servoDropVal = 0.95;
    private double servoRetractVal = 0.58;
    private int groundLevelVal = -10;
    private int firstLevelVal = -600;
    private int secondLevelVal = -1050;
    private int thirdLevelVal = -1600;
    
    

    outtakeS.setPosition(0.58);
        
    runtime.reset();
      
      
        //Plan: Face Red Side - Forward, Rotate Left, Strafe Right, Forward
        
        
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
        
        //Rotate Left
        while (opModeIsActive() && (runtime.seconds()<=0.3)) {
            turn(-1);
            teleUpdate();
        }
        runtime.reset();
        
        //stop
        while (opModeIsActive() && (runtime.seconds()<=0.3)) {
            allPower(0);
            teleUpdate();
        }
        runtime.reset();
        
        //Strafe Right
        while (opModeIsActive() && (runtime.seconds()<=0.7)) {
            strafe(0.7);
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
