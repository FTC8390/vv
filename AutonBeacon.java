package ftc8390.vv;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import for_camera_opmodes.LinearOpModeCamera;

@Autonomous(name = "AutonBeacon")  // @Autonomous(...) is the other common choice
@Disabled
public class AutonBeacon extends LinearOpModeCamera {

    RobotVV mooMoo;
    boolean allianceIsRed = true;
    boolean cameraIsWorking = false;
    int ds2 = 2;
    private ElapsedTime runtime = new ElapsedTime();

    public AutonBeacon(boolean isRed) {
        allianceIsRed = isRed;
    }

    @Override
    public void runOpMode() {
        mooMoo = new RobotVV();
        mooMoo.init(hardwareMap);

        AutonFileHandler autonFile;
        autonFile = new AutonFileHandler();
        autonFile.readDataFromFile(hardwareMap.appContext);

        double waitTimeAtRaise = 500;
        double waitTimeAtLower = 1000;

        if (isCameraAvailable()) {
            setCameraDownsampling(8);
            // parameter determines how downsampled you want your images
            // 8, 4, 2, or 1.
            // higher number is more downsampled, so less resolution but faster
            // 1 is original resolution, which is detailed but slow
            // must be called before super.init sets up the camera
            telemetry.addLine("Wait for camera to finish initializing!");
            telemetry.update();
            startCamera();  // can take a while.
            // best started before waitForStart
            telemetry.addLine("Camera ready!");
            telemetry.update();
            cameraIsWorking = true;
        } else {
            cameraIsWorking = false;
            telemetry.addLine("CAMERA NOT WORKING!!!!!!!!!!!!!!!!!!!");
            telemetry.update();
        }

        waitForStart();

        sleep(autonFile.waitTime);

        //mooMoo.shooter.turnOn();

        double xDirection;
        if (allianceIsRed)
            xDirection = 1;
        else
            xDirection = -1;

        // move diagonal to first beacon
        mooMoo.driveTrain.drive(xDirection * autonFile.driveSpeed, autonFile.driveSpeed, 0);

        // wait until the first line
        while (!mooMoo.lineDetector.lineIsFound() & opModeIsActive())
            sleep(10);

        checkAndPressBeacon(autonFile, xDirection);

        mooMoo.beaconPusher.rightIn();
        mooMoo.beaconPusher.leftIn();

        // shoot
        /*
        mooMoo.loader.raise();
        sleep(waitTimeAtRaise);
        mooMoo.loader.lower();
        sleep(waitTimeAtLower);
        mooMoo.loader.raise();
        sleep(waitTimeAtRaise);
        mooMoo.loader.lower();
        mooMoo.shooter.turnOff();
        */

        // move against the wall to second beacon
        mooMoo.driveTrain.drive(xDirection * autonFile.driveSpeed, .25 * autonFile.driveSpeed, 0);

        sleep(1000); // wait to get past the first line again

        // wait until the second line
        while (!mooMoo.lineDetector.lineIsFound() & opModeIsActive())
            sleep(10);

        checkAndPressBeacon(autonFile, xDirection);
        mooMoo.driveTrain.stop();

        mooMoo.beaconPusher.rightIn();
        mooMoo.beaconPusher.leftIn();
        sleep(1000);

        stopCamera();
    }

    public void checkAndPressBeacon(AutonFileHandler autonFile, double xDirection) {
        // go forward into wall
        mooMoo.driveTrain.drive(0, autonFile.driveSpeed, 0);
        sleep(500);

        // back up a little
        mooMoo.driveTrain.drive(0, -autonFile.driveSpeed, 0);
        sleep(125);

        // drive sideways and look for the white line
        mooMoo.driveTrain.drive(0 - xDirection * 1 / 3 * autonFile.driveSpeed, 0, 0);
        while (!mooMoo.lineDetector.lineIsFoundInMiddle() & opModeIsActive())
            sleep(10);

        // stop
        mooMoo.driveTrain.stop();

        // find beacon colors and push out the correct beacon pusher
        if (cameraIsWorking) {
            if (imageReady()) { // only do this if an image has been returned from the camera
                Bitmap rgbImage;
                rgbImage = convertYuvImageToRgb(yuvImage, width, height, ds2);

                boolean blueIsOnLeft = mooMoo.beaconColorDetector.blueIsOnLeft(rgbImage);
                if ((blueIsOnLeft & !allianceIsRed) || (!blueIsOnLeft & allianceIsRed)) {
                    mooMoo.beaconPusher.leftOut();
                } else {
                    mooMoo.beaconPusher.rightOut();
                }
                sleep(250);
            }
        }

        // drive forward to push beacon button
        mooMoo.driveTrain.drive(0, 1.25 * autonFile.driveSpeed, 0);
        sleep(350);

        // wiggle in case the beacon pusher is off by a little bit?
        mooMoo.driveTrain.drive(-xDirection * autonFile.driveSpeed, 0, 0);
        sleep(125);
        mooMoo.driveTrain.drive(xDirection * autonFile.driveSpeed, 0, 0);
        sleep(250);
        //mooMoo.driveTrain.drive(0, 0, 0);
    }

}
