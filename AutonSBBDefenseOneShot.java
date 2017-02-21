package ftc8390.vv;

/**
 * Created by jmgu3 on 2/8/2017.
 */

public class AutonSBBDefenseOneShot extends AutonShootAndBeacon {

    AutonSBBDefenseOneShot(boolean allianceIsRed) {
        super(allianceIsRed);
    }

    @Override
    public void runOpMode() {
        DriveTrain.CornerColor wheel;
        if(allianceIsRed)
        {
            wheel = DriveTrain.CornerColor.REDGREEN;
        }else{
            wheel = DriveTrain.CornerColor.YELLOWRED;
        }



        super.runOpMode();



        if(waitTimeDefense > 10000) {
            mooMoo.driveTrain.drive(xDirection * autonFile.driveSpeed , 0 , 0);
            sleep(1500);

            mooMoo.driveTrain.stop();

            mooMoo.driveTrain.drive(-xDirection * autonFile.driveSpeed, 0, 0);
            sleep(333);
            mooMoo.driveTrain.stop();
            sleep(100);

            while ((waitTimeDefense > runtime.milliseconds()) && opModeIsActive()) {
                sleep(10);
            }

            mooMoo.driveTrain.drive(0, -autonFile.driveSpeed, 0);
            sleep(1500);
            mooMoo.driveTrain.drive(0, autonFile.driveSpeed, 0);
            sleep(3000);

            double startTime = runtime.seconds();
            mooMoo.driveTrain.drive( -xDirection * autonFile.driveSpeed , 0 , 0 );
            while (!mooMoo.lineDetector.lineIsFound() & opModeIsActive() & (runtime.seconds() - startTime < 5))
                sleep(10);
        }


        int startingEncoder = mooMoo.driveTrain.getCurrentPositionByColor(wheel);

        mooMoo.driveTrain.drive(-xDirection * autonFile.driveSpeed, -autonFile.driveSpeed, .025);

        while(opModeIsActive() && mooMoo.driveTrain.getCurrentPositionByColor(wheel) < startingEncoder + 5000)
            sleep(10);
        mooMoo.driveTrain.stop();
        sleep(250);

        mooMoo.driveTrain.drive( 0, -autonFile.driveSpeed, 0 );

        sleep(1000);

        mooMoo.driveTrain.stop();



    }

}
