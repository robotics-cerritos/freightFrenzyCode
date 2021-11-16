package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp

public class carasel extends LinearOpMode {

    // Variables go here
    
    private DcMotor caraselMotor;

    @Override
    public void runOpMode() {
        
        // HardwareMapping goes here
caraselMotor = hardwareMap.DcMotor.get("caraselMotor");

        // Reversing direction goes here

        
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            caraselMotor.setPower(gamepad1.right_trigger);
            idle();
        }
    }
}
