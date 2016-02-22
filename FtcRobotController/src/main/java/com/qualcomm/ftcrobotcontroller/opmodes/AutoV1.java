package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TeleOp Mode
 * <p>
 *Enables control of the robot via the gamepad
 */
public class AutoV1 extends OpMode {
    DcMotor dbr;
    DcMotor dbl;
    DcMotor dfr;
    DcMotor dfl;



    private String startDate;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {

        dbr = hardwareMap.dcMotor.get("drive.back.right");
        dbl = hardwareMap.dcMotor.get("drive.back.left");
        dfr = hardwareMap.dcMotor.get("drive.front.right");
        dfl = hardwareMap.dcMotor.get("drive.front.left");
    }

    /*
       set startup code here
    */

    public void loop() {

        Utilities.runAuto("drive forward");


    }
}
