/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package ftc8390.vv;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import for_camera_opmodes.LinearOpModeCamera;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="AutonBeacon")  // @Autonomous(...) is the other common choice
@Disabled
public class AutonBeacon extends LinearOpModeCamera {

    RobotVV mooMoo;
    boolean allianceIsRed = true;
    boolean cameraIsWorking = false;
    int ds2 = 2;
    private ElapsedTime runtime = new ElapsedTime();
    // DcMotor leftMotor = null;
    // DcMotor rightMotor = null;

    @Override
    public void runOpMode() {
        mooMoo = new RobotVV();
        mooMoo.init(hardwareMap);

        AutonFileHandler autonFile;
        autonFile = new AutonFileHandler();
        autonFile.readDataFromFile(hardwareMap.appContext);

        if (isCameraAvailable()) {

            setCameraDownsampling(8);
            // parameter determines how downsampled you want your images
            // 8, 4, 2, or 1.
            // higher number is more downsampled, so less resolution but faster
            // 1 is original resolution, which is detailed but slow
            // must be called before super.init sets up the camera
            BeaconColorDetector BCD = new BeaconColorDetector();
            BCD.init(hardwareMap);
            telemetry.addLine("Wait for camera to finish initializing!");
            telemetry.update();
            startCamera();  // can take a while.
            // best started before waitForStart
            telemetry.addLine("Camera ready!");
            telemetry.update();
            cameraIsWorking = true;
        }else {
            cameraIsWorking = false;
            telemetry.addLine("CAMERA NOT WORKING!!!!!!!!!!!!!!!!!!!");
            telemetry.update();
        }


            waitForStart();
        mooMoo.shooter.turnOn();
        double xDirection;
        if(allianceIsRed)
            xDirection = 1;
        else
            xDirection = -1;
        //Move diagnal to first beacon
        mooMoo.driveTrain.drive( xDirection * autonFile.driveSpeed , autonFile.driveSpeed , 0 );
        //wait until detected line
        while( !mooMoo.lineDetector.lineIsFoundInMiddle() & opModeIsActive() )
            sleep(10);

        mooMoo.driveTrain.drive(0,0,0);

        mooMoo.driveTrain.drive(0, autonFile.driveSpeed, 0);
        sleep(500);
        mooMoo.driveTrain.drive( 0 , -autonFile.driveSpeed , 0 );
        sleep(250);
        mooMoo.driveTrain.drive(0,0,0);

        if(cameraIsWorking)
        {
            if (imageReady()) { // only do this if an image has been returned from the camera
                Bitmap rgbImage;
                rgbImage = convertYuvImageToRgb(yuvImage, width, height, ds2);
                boolean blueIsOnLeft = mooMoo.beaconColorDetector.blueIsOnLeft(rgbImage);
                if ((blueIsOnLeft & !allianceIsRed) || (!blueIsOnLeft & allianceIsRed) ) {
                    mooMoo.beaconPusher.leftOut();
                }else{
                    mooMoo.beaconPusher.rightOut();
                }
                sleep (250);
            }
        }

        mooMoo.driveTrain.drive(0, autonFile.driveSpeed, 0);
        sleep(250);
        mooMoo.driveTrain.drive(0,0,0);


        /*
        mooMoo.loader.raise();
        sleep(500);
        mooMoo.loader.lower();
        sleep(1000);
        mooMoo.loader.raise();
        sleep(500);
        mooMoo.loader.lower();

        mooMoo.beaconPusher.rightIn();
        mooMoo.beaconPusher.leftIn();

        mooMoo.shooter.turnOff();

        mooMoo.driveTrain.drive(-xDirection * autonFile.driveSpeed, .25 * autonFile.driveSpeed , 0 );

        while(!mooMoo.lineDetector.lineIsFoundInMiddle() & opModeIsActive())
        {
            sleep(10);
        }

        //mooMoo.driveTrain.drive( 0 , autonFile.driveSpeed , 0 );
       // sleep(250);
        mooMoo.driveTrain.drive( 0, -autonFile.driveSpeed , 0);
        sleep(250);

        if(cameraIsWorking)
        {
            if (imageReady()) { // only do this if an image has been returned from the camera
                Bitmap rgbImage;
                rgbImage = convertYuvImageToRgb(yuvImage, width, height, ds2);
                boolean blueIsOnLeft = mooMoo.beaconColorDetector.blueIsOnLeft(rgbImage);
                if ((blueIsOnLeft & !allianceIsRed) || (!blueIsOnLeft & allianceIsRed) ) {
                    mooMoo.beaconPusher.leftOut();
                }else{
                    mooMoo.beaconPusher.rightOut();
                }
                sleep (250);
            }
        }

        mooMoo.driveTrain.drive(0, autonFile.driveSpeed, 0);
        sleep(250);
        mooMoo.driveTrain.drive(0,0,0);

        sleep(1000);

        mooMoo.beaconPusher.rightIn();
        mooMoo.beaconPusher.leftIn();
         */
    }
}
