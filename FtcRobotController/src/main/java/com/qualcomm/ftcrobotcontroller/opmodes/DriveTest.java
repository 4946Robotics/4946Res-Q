/*
 * Copyright (c) 2014, 2015 Qualcomm Technologies Inc
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * (subject to the limitations in the disclaimer below) provided that the following conditions are
 * met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 * and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * Neither the name of Qualcomm Technologies Inc nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS LICENSE. THIS
 * SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
 * THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Follow an IR Beacon
 * <p>
 * How to use: <br>
 * Make sure the Modern Robotics IR beacon is off. <br>
 * Set it to 1200 at 180.  <br>
 * Make sure the side of the beacon with the LED on is facing the robot. <br>
 * Turn on the IR beacon. The robot will now follow the IR beacon. <br>
 * To stop the robot, turn the IR beacon off. <br>
 */
public class DriveTest extends OpMode {
    int speed = 100; //(out of 100)
    double ratio = 1.0/5;
    DcMotor dbr;
    DcMotor dbl;
    DcMotor dfr;
    DcMotor dfl;
    DcMotor rbr;
    DcMotor rbl;
    DcMotor rfr;
    DcMotor rfl;


  @Override
  public void init() {
      dbr = hardwareMap.dcMotor.get("drive.back.right");
      dbl = hardwareMap.dcMotor.get("drive.back.left");
      dfr = hardwareMap.dcMotor.get("drive.front.right");
      dfl = hardwareMap.dcMotor.get("drive.front.left");
      rbr = hardwareMap.dcMotor.get("rotate.back.right");
      rbl = hardwareMap.dcMotor.get("rotate.back.left");
      rfr = hardwareMap.dcMotor.get("rotate.front.right");
      rfl = hardwareMap.dcMotor.get("rotate.front.left");
  }

  @Override
  public void loop() {
      float throttle = gamepad1.left_stick_y;
      float direction = gamepad1.left_stick_x;
      float xl2 = gamepad2.left_stick_x;
      float yl2 = gamepad2.left_stick_y;
      float xr2 = gamepad2.right_stick_x;
      float yr2 = gamepad2.right_stick_y;
      float driveright = throttle - direction;
      float driveleft = throttle + direction;


      // clip the right/left values so that the values never exceed +/- 1
      driveright = Range.clip(driveright, -1, 1);
      driveleft = Range.clip(driveleft, -1, 1);


    double [] rotValues = Utilities.controlArms(speed, ratio, xl2, yl2, xr2, yr2);
      rbl.setPower(rotValues[0]);
      rbr.setPower(rotValues[1]);
      rfl.setPower(rotValues[2]);
      rfr.setPower(rotValues[3]);

      dbl.setPower(Range.clip((driveleft + rotValues[4]), -1, 1));
      dbr.setPower(Range.clip((driveright+ rotValues[5]), -1, 1));
      dfl.setPower(Range.clip((driveleft + rotValues[6]), -1, 1));
      dfr.setPower(Range.clip((driveright + rotValues[7]), -1, 1));






    telemetry.addData("x rotation", direction);
    telemetry.addData("y rotation", direction);
    telemetry.addData("z rotation", getRuntime());
}


  }

