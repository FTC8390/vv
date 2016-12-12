package ftc8390.vv;

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
        mooMoo.driveTrain.drive(-xDirection * autonFile.driveSpeed, 0 , 0);

        mooMoo.shooter.turnOn();

        sleep(1000);
        // wait until the first line
        while (!mooMoo.lineDetector.lineIsFound() & opModeIsActive())
            sleep(10);

        mooMoo.driveTrain.drive( 0 , -autonFile.driveSpeed , 0 );
        sleep((long)autonFile.driveBackTime.doubleValue());
        mooMoo.driveTrain.stop();

        mooMoo.loader.raise();
        sleep(mooMoo.loader.timeToRaise);
        mooMoo.loader.lower();
        sleep(mooMoo.loader.timeToLower);
        mooMoo.loader.raise();
        sleep(mooMoo.loader.timeToRaise);
        mooMoo.loader.lower();
        sleep(mooMoo.loader.timeToLower);

        // now get in position and shoot!

    }
}
