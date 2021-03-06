package com.qualcomm.ftcrobotcontroller.opmodes;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.text.SimpleDateFormat;
import java.util.Date;


public class OrientOp extends OpMode implements SensorEventListener {
    private String startDate;
    private SensorManager mSensorManager;
    private Sensor rotV;

    // orientation values
    private float azimuth = 0.0f;      // value in radians
    private float pitch = 0.0f;        // value in radians
    private float roll = 0.0f;         // value in radians
    public int update = 0;
    private float[] rotVec;       // latest sensor values


    /*
    * Constructor
    */
    public OrientOp() {

    }

    /*
    * Code to run when the op mode is first enabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#init()
    */
    @Override
    public void init() {
        mSensorManager = (SensorManager) hardwareMap.appContext.getSystemService(Context.SENSOR_SERVICE);
        rotV = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        azimuth = 0.0f;      // value in radians
        pitch = 0.0f;        // value in radians
        roll = 0.0f;
    }

    /*
* Code to run when the op mode is first enabled goes here
* @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
*/
    @Override
    public void start() {
        startDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

        // delay value is SENSOR_DELAY_UI which is ok for telemetry, maybe not for actual robot use
        mSensorManager.registerListener(this, rotV, SensorManager.SENSOR_DELAY_UI);
    }

    /*
    * This method will be called repeatedly in a loop
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
    */
    @Override
    public void loop() {
//        telemetry.addData("1 Start", "OrientOp started at " + startDate);
//        telemetry.addData("2 note1", "values below are in degrees" );
//        telemetry.addData("3 note2", "azimuth relates to magnetic north" );
//        telemetry.addData("4 note3", " " );
        telemetry.addData("azimuth", Math.round(Math.toDegrees(azimuth)));
        telemetry.addData("pitch", Math.round(Math.toDegrees(pitch)));
        telemetry.addData("roll", Math.round(Math.toDegrees(roll)));
        telemetry.addData("time", getRuntime());
        telemetry.addData("Changed?", update);
    }

    /*
    * Code to run when the op mode is first disabled goes here
    * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
    */
    @Override
    public void stop() {
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not sure if needed, placeholder just in case
    }

    public void onSensorChanged(SensorEvent event) {
        // we need sensor values to calculate orientation

        update ++;
        float[] or = Utilities.getOr(event);
        azimuth = or[0];
        pitch = or[1];
        roll = or[2];
    }
}
