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

public class Allen_drive extends LinearOpMode {

    // Variables go here  
    //Wheel Motors 
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;
    
    // Intake Servos
    private Servo intakeS;
    private Servo intakeClampS;
    
    //Carasel Motor
    private DcMotor caraselM;
    
    //Default Speed Value
    private double speed = 1;

    //Carasel Boolean
    private boolean caraselMove=false;

    @Override
    public void runOpMode() {
        
        // HardwareMapping goes here
        
        //Wheels
        leftFront = hardwareMap.dcMotor.get("leftF");
        rightFront = hardwareMap.dcMotor.get("rightF");
        leftRear = hardwareMap.dcMotor.get("leftR");
        rightRear = hardwareMap.dcMotor.get("rightR");
        
        // Intake
        intakeS = hardwareMap.servo.get("intakeS");
        intakeClampS = hardwareMap.servo.get("intakeClampS");
        
        // Carasel Motor
        caraselM = hardwareMap.dcMotor.get("caraselMotor");

        // Reversing direction goes here
        
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);
        
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

          // x & y are from left stick
          double x = -gamepad1.left_stick_x;
          double y = gamepad1.left_stick_y;
          // rotation is from right stick
          double rotation = -gamepad1.right_stick_x;
          
          // Double array of wheel speeds 
          double wheelSpeeds[] = new double[4];

          wheelSpeeds[0] = x + y + rotation;
          wheelSpeeds[1] = -x + y - rotation;
          wheelSpeeds[2] = -x + y + rotation;
          wheelSpeeds[3] = x + y - rotation;

          normalize(wheelSpeeds);
          
          //setting the speed of motors
          if(gamepad1.a){
            speed=1;
          }
          if(gamepad1.b){
            speed=0.5;
          }
          //Changing wheel speeds
          leftFront.setPower(speed*wheelSpeeds[0]);
          rightFront.setPower(speed*wheelSpeeds[1]);
          leftRear.setPower(speed*wheelSpeeds[2]);
          rightRear.setPower(speed*wheelSpeeds[3]);
          
          // Setting intake positions based on dpad
          if(gamepad1.dpad_down){
            intakeClampS.setPosition(0.6);
            sleep(700);
            intakeS.setPosition(1);
          }
          else if(gamepad1.dpad_up){
            intakeClampS.setPosition(0.3);
            sleep(700);
            intakeS.setPosition(0.75);
          }
          
          //Setting the speed of the carasel motor based on the right stick
          
          if(gamepad2.dpad_up){
            caraselMove=true;
          }
          while (caraselMove && opModeIsActive()) {
            while((caraselM.getPower()<1)){
              caraselM.setPower(caraselM.getPower()+0.092);
              sleep(200);
            }
            sleep(700);
            caraselM.setPower(0);
            caraselMove = false;
          }
          
          if(gamepad2.dpad_down){
            caraselMove=true;
          }
          while (caraselMove && opModeIsActive()) {
            while((caraselM.getPower()>-1)){
              caraselM.setPower(caraselM.getPower()-0.092);
              sleep(200);
            }
            sleep(700);
            caraselM.setPower(0);
            caraselMove = false;
          }
          
          telemetry.addData("Carasel Motor: ",caraselM.getPower());
          telemetry.addData("LeftF: ",leftFront.getPower() );
          telemetry.addData("RightF: ",rightFront.getPower() );
          telemetry.addData("LeftR: ",leftRear.getPower() );
          telemetry.addData("RightR: ",rightRear.getPower() );
          telemetry.addData("IntakeS: ",intakeS.getPosition());
          telemetry.addData("IntakeClampS: ",intakeClampS.getPosition() );
          telemetry.update();
          idle();
        }
    }

    private void normalize(double[] wheelSpeeds){
      //Find the maximum wheel speed
      double maxMagnitude = Math.abs(wheelSpeeds[0]);
      for (int i = 1; i < wheelSpeeds.length; i++){
        double magnitude = Math.abs(wheelSpeeds[i]);
        if (magnitude > maxMagnitude){
          maxMagnitude = magnitude;
        }
      }
      //If the maximum wheel speed is greater than 1
      //then divide all the wheel speeds by the maximum wheel speed value
      if (maxMagnitude > 1.0){
        for (int i = 0; i < wheelSpeeds.length; i++){
          wheelSpeeds[i] /= maxMagnitude;
        }
      }
    }   //normalize
}
