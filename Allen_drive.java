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
    
    @Override
    public void runOpMode() {
        
        // HardwareMapping goes here
        leftFront = hardwareMap.dcMotor.get("leftF");
        rightFront = hardwareMap.dcMotor.get("rightF");
        leftRear = hardwareMap.dcMotor.get("leftR");
        rightRear = hardwareMap.dcMotor.get("rightR");

        // Reversing direction goes here
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);
        
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

          // x & y are from left stick rotation is from right stick

          double x = -gamepad1.left_stick_x;
          double y = gamepad1.left_stick_y;
          double rotation = -gamepad1.right_stick_x;

          double wheelSpeeds[] = new double[4];

          wheelSpeeds[0] = x + y + rotation;
          wheelSpeeds[1] = -x + y - rotation;
          wheelSpeeds[2] = -x + y + rotation;
          wheelSpeeds[3] = x + y - rotation;

          normalize(wheelSpeeds);

          leftFront.setPower(wheelSpeeds[0]);
          rightFront.setPower(wheelSpeeds[1]);
          leftRear.setPower(wheelSpeeds[2]);
          rightRear.setPower(wheelSpeeds[3]);


          idle();
        }
    }

    
    // i found this method from the internet. 
    private void normalize(double[] wheelSpeeds){
      double maxMagnitude = Math.abs(wheelSpeeds[0]);

      for (int i = 1; i < wheelSpeeds.length; i++){
        double magnitude = Math.abs(wheelSpeeds[i]);

        if (magnitude > maxMagnitude){
          maxMagnitude = magnitude;
        }
      }

      if (maxMagnitude > 1.0){
        for (int i = 0; i < wheelSpeeds.length; i++){
          wheelSpeeds[i] /= maxMagnitude;
        }
      }
    }   //normalize


    
}
