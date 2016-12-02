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
    // DcMotor leftMotor = null;
    // DcMotor rightMotor = null;

    public AutonBeacon (boolean isRed)
    {
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

        mooMoo.shooter.turnOn();
        double xDirection;
        if (allianceIsRed)
            xDirection = 1;
        else
            xDirection = -1;

        //Move diagonal to first beacon
        mooMoo.driveTrain.drive(xDirection * autonFile.driveSpeed, autonFile.driveSpeed, 0);

        //wait until detected line
        while (!mooMoo.lineDetector.lineIsFoundInMiddle() & opModeIsActive())
            sleep(10);

        mooMoo.driveTrain.drive(0, 0, 0);

        checkAndPressBeacon(autonFile);

        /*
        mooMoo.loader.raise();
        sleep(waitTimeAtRaise);
        mooMoo.loader.lower();
        sleep(waitTimeAtLower);
        mooMoo.loader.raise();
        sleep(waitTimeAtRaise);
        mooMoo.loader.lower();
        mooMoo.shooter.turnOff();

        mooMoo.beaconPusher.rightIn();
        mooMoo.beaconPusher.leftIn();

        mooMoo.driveTrain.drive(-xDirection * autonFile.driveSpeed, .25 * autonFile.driveSpeed , 0 );

        while(!mooMoo.lineDetector.lineIsFoundInMiddle() & opModeIsActive())
        {
            sleep(10);
        }

        checkAndPressBeacon(autonFile);

        sleep(1000);
        mooMoo.beaconPusher.rightIn();
        mooMoo.beaconPusher.leftIn();
         */

        stopCamera();
    }

    public void checkAndPressBeacon(AutonFileHandler autonFile) {
        mooMoo.driveTrain.drive(0, autonFile.driveSpeed, 0);
        sleep(500);
        mooMoo.driveTrain.drive(0, -autonFile.driveSpeed, 0);
        sleep(250);
        mooMoo.driveTrain.drive(0, 0, 0);

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

        mooMoo.driveTrain.drive(0, autonFile.driveSpeed, 0);
        sleep(350);
        mooMoo.driveTrain.drive(0, 0, 0);
    }

}
