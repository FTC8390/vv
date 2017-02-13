package ftc8390.vv;

import static android.R.attr.x;

/**
 * Created by jmgu3 on 2/8/2017.
 */

public class AutonSBBDefense extends AutonShootAndBeacon {

    AutonSBBDefense(boolean allianceIsRed) {
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
          mooMoo.driveTrain.drive(xDirection * autonFile.driveSpeed , 0 , 0);
        sleep(1000);
        while(opModeIsActive() && runtime.seconds() < 22) {
            // .3  seconds back up, towards beacon 1 sec, back 1.2 sec, .5 sec into the wall
            mooMoo.driveTrain.drive(-xDirection * autonFile.driveSpeed, 0, 0);
            sleep(333);
            mooMoo.driveTrain.stop();
            sleep(100);
            mooMoo.driveTrain.drive( 0 , -autonFile.driveSpeed , 0 );
            sleep(1000);
            mooMoo.driveTrain.drive( 0 , autonFile.driveSpeed , 0 );
            sleep(1400);
            mooMoo.driveTrain.drive(xDirection * autonFile.driveSpeed , 0 , 0);
            sleep(700);

        }
        double startTime = runtime.seconds();

        mooMoo.driveTrain.drive( -xDirection * autonFile.driveSpeed , 0 , 0 );
        while (!mooMoo.lineDetector.lineIsFound() & opModeIsActive() & (runtime.seconds() - startTime < 5))
            sleep(10);

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
