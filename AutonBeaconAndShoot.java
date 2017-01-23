package ftc8390.vv;

import android.graphics.Bitmap;

/**
 * Created on 12/11/2016.
 */

public class AutonBeaconAndShoot extends AutonBeacon {

    AutonBeaconAndShoot(boolean allianceIsRed) {
        super(allianceIsRed);
    }

    @Override
    public void runOpMode() {
        super.runOpMode(); // find and press the beacons

        mooMoo.shooter.turnOn();
        mooMoo.driveTrain.drive(-xDirection * autonFile.driveSpeed, 0, 0);

        sleep(1000);
        // wait until the first line;
        double startTime;
        startTime = runtime.seconds();
        while (!mooMoo.lineDetector.lineIsFound() & opModeIsActive() & (runtime.seconds() - startTime < 5))
            sleep(10);

        mooMoo.driveTrain.drive(0, autonFile.driveSpeed, 0);
        sleep(750);

        // back up a little
        mooMoo.driveTrain.drive(0, -autonFile.driveSpeed, 0);
        sleep(125);

        mooMoo.driveTrain.drive(xDirection * 1 / 3 * autonFile.driveSpeed, 0, 0);
        startTime = runtime.seconds();
        while (!mooMoo.lineDetector.lineIsFoundInMiddle() & opModeIsActive() & (runtime.seconds() - startTime < 4))
            sleep(10);
        mooMoo.driveTrain.stop();
       /* if(allianceIsRed)
        {
            Bitmap rgbImage;
            rgbImage = convertYuvImageToRgb(yuvImage, width, height, ds2);
            if(!mooMoo.beaconColorDetector.wholeBeaconRed( rgbImage )) {

                checkAndPressBeacon();
            }
        }else {
            Bitmap rgbImage;
            rgbImage = convertYuvImageToRgb(yuvImage, width, height, ds2);
            if(!mooMoo.beaconColorDetector.wholeBeaconBlue(rgbImage))
            {
                checkAndPressBeacon();
            }
        }*/
        //mooMoo.driveTrain.stop();

        //sleep(100000);

        mooMoo.sweeper.sweepIn();

        mooMoo.driveTrain.drive(0, -autonFile.driveSpeed, 0);
        sleep((long) autonFile.driveBackTime.doubleValue());
        mooMoo.driveTrain.stop();

        sleep(2000);

        mooMoo.loader.raise();
        sleep(mooMoo.loader.timeToRaise);
        mooMoo.loader.lower();
        sleep(mooMoo.loader.timeToLower);
        mooMoo.loader.raise();
        sleep(mooMoo.loader.timeToRaise);
        mooMoo.loader.lower();
        //sleep(mooMoo.loader.timeToLower);
        mooMoo.shooter.turnOff();

        mooMoo.sweeper.stop();

        mooMoo.driveTrain.drive(0, -autonFile.driveSpeed, 0);
        sleep(1500);

        mooMoo.driveTrain.drive(0, 0, xDirection * autonFile.driveSpeed);
        sleep(1500);
        mooMoo.driveTrain.stop();

    }

    public int wholeBeaconColored()
    {
        if (cameraIsWorking) {
            if (imageReady()) { // only do this if an image has been returned from the camera
                Bitmap rgbImage;
                rgbImage = convertYuvImageToRgb(yuvImage, width, height, ds2);

                if(!mooMoo.beaconColorDetector.wholeBeaconBlue(rgbImage)&&!mooMoo.beaconColorDetector.wholeBeaconRed(rgbImage))
                    return 0;
                else
                    return 1;

            }
        }
        return 2;
    }


}
