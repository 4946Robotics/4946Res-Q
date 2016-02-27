package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import android.media.MediaPlayer;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
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
    MediaPlayer mediaPlayer = new MediaPlayer();
    String mediastate;
    String datastate = "";



    private String startDate;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {



        try {
            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory() +"/Download/rocky.mp3");
            datastate = "got data";
            mediaPlayer.prepare();
            mediastate = "Initialized";
        } catch (IOException e) {
            e.printStackTrace();
            datastate = "no data";

            mediastate = "error";
        }

        /*dbr = hardwareMap.dcMotor.get("drive.back.right");
        dbl = hardwareMap.dcMotor.get("drive.back.left");
        dfr = hardwareMap.dcMotor.get("drive.front.right");
        dfl = hardwareMap.dcMotor.get("drive.front.left");
*/



    }

    /*
       set startup code here
    */

    public void loop() {



        resetStartTime();

        String state;

        Boolean Started = false;


        if (Started == false){
            Started = true;
            mediaPlayer.start();
            mediastate = "Started";
        }

       /* if (getRuntime() <3)  state = "drive forward";

        else if (getRuntime() <6)  state = "drive left";

        else if (getRuntime() <10)  state = "drive forward";

        else if (getRuntime() <12)  state = "drive forward";

        else state = "stop";

        double[] drive = Utilities.runAuto(state);

        dbl.setPower(drive[0]);
        dfl.setPower(drive[1]);
        dbr.setPower(drive[2]);
        dfr.setPower(drive[3]);

*/
        telemetry.addData("datasource", Environment.getExternalStorageDirectory() +"/Download/rocky.mp3 "  + new File(Environment.getExternalStorageDirectory() +"/Download/rocky.mp3").exists());
        telemetry.addData("data", datastate);
        telemetry.addData("state", mediastate);
    }

    public void stop(){
        mediaPlayer.stop();
        mediastate = "ended";
    }



}
