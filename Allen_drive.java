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

public class Allen_drive extends LinearOpMode {

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

    private boolean hasBlock = false;
    
    private DistanceSensor sensorRange;
    private double distanceThreshold = 5.0; 


    

    @Override
    public void runOpMode() {
        
        // HardwareMapping goes here
        
        //Wheels
        leftFront = hardwareMap.dcMotor.get("leftF");
        rightFront = hardwareMap.dcMotor.get("rightF");
        leftRear = hardwareMap.dcMotor.get("leftR");
        rightRear = hardwareMap.dcMotor.get("rightR");
        
        // Intake
        intakeM = hardwareMap.dcMotor.get("intakeMotor");
        
        // Outttake
        outtakeM = hardwareMap.dcMotor.get("outtakeMotor");
        outtakeS = hardwareMap.servo.get("outtakeServo");

        sensorRange = hardwareMap.get(DistanceSensor.class, "sensor_range");
        
        //outtakeM.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        //outtakeM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      
        // Carasel Motor
        caraselM = hardwareMap.dcMotor.get("caraselMotor");

        // Reversing direction goes here
        
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);
        
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

          // x & y (strafing and forwards/backwards) are from left stick
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
          
          //Intake
          if(sensorRange.getDistance(DistanceUnit.CM))<blockThreshold){
            hasBlock=true;
          }
          
          // trigger to power intake 
          // if there is no block then allow intake.
          // if there is a block then stop intake.
          if(!hasBlock){
            intakeM.setPower((gamepad2.right_trigger - gamepad2.left_trigger)*2);
          }else{
            intakeM.setPower(0);
          }

          




          
          
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
          
          
          // Encoder Outtake Control
          if(outtakeM.getCurrentPosition() >= RAISE_MAX && outtakeM.getCurrentPosition() <= RAISE_MIN)
          {
            if(gamepad2.b){
              outtakeMove(groundLevelVal); // Ground Level
            }
            if(gamepad2.a)
            {
              outtakeMove(firstLevelVal);// First Level
            }
            if(gamepad2.x){
              outtakeMove(secondLevelVal);// Second Level
            }
            if(gamepad2.y){
              outtakeMove(thirdLevelVal);// Third Level
            }
            if(gamepad2.left_bumper)
            {
              outtakeS.setPosition(servoRetractVal);
            }
            if(gamepad2.right_bumper)
            {
              outtakeS.setPosition(servoDropVal);
              hasBlock=false;
            }
          }
          else
          {
            outtakeMove(groundLevelVal); //Ground Level
          }
          
          
          // Manual Outtake Control
          /*
          outtakeM.setPower((gamepad2.left_stick_y)/2);
          if(outtakeM.getCurrentPosition() < RAISE_MAX){
            outtakeM.setPower(0.2);
            sleep(80);
            outtakeM.setPower(0);
          }
          else if(outtakeM.getCurrentPosition() > RAISE_MIN){
            outtakeM.setPower(-0.2);
            sleep(80);
            outtakeM.setPower(0);
          }
          if(gamepad2.x)
            {
              outtakeS.setPosition(servoRetractVal);
            }
            if(gamepad2.y)
            {
              outtakeS.setPosition(servoDropVal);
            }
          */
          //telemetry.addData("Carasel Motor: ",caraselM.getPower());
          telemetry.addData("LeftF: ",leftFront.getPower() );
          telemetry.addData("RightF: ",rightFront.getPower() );
          telemetry.addData("LeftR: ",leftRear.getPower() );
          telemetry.addData("RightR: ",rightRear.getPower() );
          telemetry.addData("IntakeM: ",intakeM.getPower());
          telemetry.addData("Outtake Encoder: ", outtakeM.getCurrentPosition());
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
    
    
    public void outtakeMove(int move)
    {
      outtakeM.setTargetPosition(move);
      outtakeM.setPower(0.3);
      outtakeM.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      
      
      while(opModeIsActive() && outtakeM.isBusy());
      {
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
      }
      
    }
}
