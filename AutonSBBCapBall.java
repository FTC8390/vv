package ftc8390.vv;

/**
 * Created by jmgu3 on 2/8/2017.
 */

public class AutonSBBCapBall extends AutonShootAndBeacon {

    AutonSBBCapBall(boolean allianceIsRed) {
        super(allianceIsRed);
    }

    @Override
    public void runOpMode() {
        super.runOpMode();
            DriveTrain.CornerColor wheel;
        if(allianceIsRed)
        {
            wheel = DriveTrain.CornerColor.REDGREEN;
        }else{
            wheel = DriveTrain.CornerColor.YELLOWRED;
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
