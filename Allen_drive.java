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
    
    //Carasel Motor
    private DcMotor caraselM;
    
    //Default Speed Value
    private double speed = 1;
    @Override
    public void runOpMode() {
        
        // HardwareMapping goes here
        
        //Wheels
        leftFront = hardwareMap.dcMotor.get("leftF");
        rightFront = hardwareMap.dcMotor.get("rightF");
        leftRear = hardwareMap.dcMotor.get("leftR");
        rightRear = hardwareMap.dcMotor.get("rightR");
        
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
            speed=0.3;
          }
          //Changing wheel speeds
          leftFront.setPower(speed*wheelSpeeds[0]);
          rightFront.setPower(speed*wheelSpeeds[1]);
          leftRear.setPower(speed*wheelSpeeds[2]);
          rightRear.setPower(speed*wheelSpeeds[3]);
          
          //Setting the speed of the carasel motor based on the right stick
          caraselM.setPower(gamepad2.right_stick_y);
          telemetry.addData("Servo: ",caraselM.getPower());
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
