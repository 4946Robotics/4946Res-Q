package com.qualcomm.ftcrobotcontroller.opmodes;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

/**
 * Created by Robert on 11/20/2015.
 */
public class Utilities {

    public static double[] controlArms(int speed, double ratio, double x1, double y1, double x2, double y2) {
        double speedRearLeft;
        double speedRearRight;
        double speedFrontLeft;
        double speedFrontRight;
        double driveRearLeft;
        double driveRearRight;
        double driveFrontLeft;
        double driveFrontRight;


        double[] rear = joyToArms(x1, y1);
        speedRearLeft = speed * rear[0] / 100.0;
        speedRearRight = speed * rear[1] / 100.0;

        double[] front = joyToArms(x2, y2);
        speedFrontLeft = speed * front[0] / 100.0;
        speedFrontRight = speed * front[1] / 100.0;

        driveRearLeft = -driveArmTrack(speedRearLeft, ratio);
        driveRearRight = driveArmTrack(speedRearRight, ratio);
        driveFrontLeft = -driveArmTrack(speedFrontLeft, ratio);
        driveFrontRight = driveArmTrack(speedFrontRight, ratio);


        return new double[]{speedRearLeft, speedRearRight, speedFrontLeft, speedFrontRight, driveRearLeft, driveRearRight, driveFrontLeft, driveFrontRight};
    }

    public static double[] joyToArms(double x, double y) {
        double armLeft, armRight;

        armLeft = x / 2 + y / 2;

        armRight = -x / 2 + y / 2;

        return new double[]{armLeft, armRight};
    }

    public static float[] getOr(SensorEvent event) {
        float[] rotVec = new float[3];
        float[] or = new float[3];

        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            rotVec = event.values;
        }

        if (rotVec != null) {  //make sure we have it before calling stuff
            float orientation[] = new float[3];

            SensorManager.getRotationMatrixFromVector(orientation, rotVec);
            SensorManager.getOrientation(orientation, or);
            if (or == null) return rotVec;
            return or;
        }
        return new float[]{100, 100, 100};
    }

    public static double driveArmTrack(double speed, double ratio) {
        //ratio is trackmotor rotations/arm motor rotations for the track to stay still
        double trackSpeed;
        trackSpeed = speed * ratio;
        return trackSpeed;
    }

    public static double[] selfLevel(int speed, double ratio, double xdiff, double ydiff, double x1, double y1, double x2, double y2) {

        /*if diff is positive, robot is tilting forward or to the right
        *



         */
        double rotateRearLeft;
        double rotateRearRight;
        double rotateFrontLeft;
        double rotateFrontRight;
        double driveRearLeft;
        double driveRearRight;
        double driveFrontLeft;
        double driveFrontRight;

        double[] rear = joyToArms(x1, y1);
        rotateRearLeft = speed * rear[0] / 100.0 - xdiff / 100.0 - ydiff / 100.0;
        rotateRearRight = speed * rear[1] / 100.0 - xdiff / 100.0 + ydiff / 100.0;

        double[] front = joyToArms(x2, y2);
        rotateFrontLeft = speed * front[0] / 100.0 + xdiff / 100.0 - ydiff / 100.0;
        rotateFrontRight = speed * front[1] / 100.0 + xdiff / 100.0 + ydiff / 100.0;


        driveRearLeft = driveArmTrack(rotateRearLeft, ratio);
        driveRearRight = driveArmTrack(rotateRearRight, ratio);
        driveFrontLeft = driveArmTrack(rotateFrontLeft, ratio);
        driveFrontRight = driveArmTrack(rotateFrontRight, ratio);


        return new double[]{rotateRearLeft, rotateRearRight, rotateFrontLeft, rotateFrontRight, driveRearLeft, driveRearRight, driveFrontLeft, driveFrontRight};

    }

    // input light sensor intensity and threshold (where blue is more, red is less)
    // returns true for right, false for left.
    public static boolean getColor(double color, double threshold) {
        if (color > threshold) return true;
        else return false;
    }

    public static double[] runAuto(String state) {
        /*
        drive forwards
        drive backwards
        drive left
        drive right







         */

        String states[] = {
                "drive forwards",
                "drive backward",
                "drive left",
                "drive right",
                "press button",
        };

        if (state.equals(states[0])) {
            //  "drive forwards",


        }

        if (state.equals(states[1])) {
            //  "drive backwards",


        }

        if (state.equals(states[2])) {
            //  "drive left",


        }
        if (state.equals(states[3])) {
            //"drive right",


        }
        if (state.equals(states[4])) {
            //"press button"

        }
        return null;
    }
}
