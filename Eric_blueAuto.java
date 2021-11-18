ackage org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp

public class template extends LinearOpMode {

    // Variables go here
    private ElapsedTime runtime = new ElapsedTime();    
    private DcMotor  leftFront;
    private DcMotor  rightFront;
    private DcMotor  leftRear;
    private DcMotor  rightRear;
    
    @Override
    public void runOpMode() {
        
        // HardwareMapping goes here
        leftFront = hardwareMap.dcMotor.get("leftF");
        rightFront = hardwareMap.dcMotor.get("rightF");
        leftRear = hardwareMap.dcMotor.get("leftR");
        rightRear = hardwareMap.dcMotor.get("rightR");
        

        // Reversing direction goes here
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        
        waitForStart();
        runtime.reset();
      
        while (opModeIsActive() && (runtime.seconds()<=0.926)) {
            turnRight();

        }
        while (opModeIsActive() && (runtime.seconds()<=0.1)) {
            allPower(0);

        }
        while (opModeIsActive() && (runtime.seconds()<=0.926)) {
            allPower(1.0);

        }
        while (opModeIsActive() && (runtime.seconds()<=0.1)) {
            allPower(0);

        }
        while (opModeIsActive() && (runtime.seconds()<=0.926)) {
            turnLeft();

        }
        while (opModeIsActive() && (runtime.seconds()<=0.1)) {
            allPower(0);

        }
        while (opModeIsActive() && (runtime.seconds()<=0.926)) {
            allPower(1.0);

        }
        while (opModeIsActive() && (runtime.seconds()<=0.1)) {
            allPower(0);

        }
        
    }

    public void allPower(double power){
        leftFront.setPower(power);
        leftRear.setPower(power);
        rightFront.setPower(power);
        rightRear.setPower(power);
        
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
