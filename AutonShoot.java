package ftc8390.vv;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Shooter Only")  // @Autonomous(...) is the other common choice
@Disabled
public class AutonShoot extends LinearOpMode {

    RobotVV mooMoo;
    boolean allianceIsRed;
    double xDirection;
    private ElapsedTime runtime = new ElapsedTime();

    public AutonShoot(boolean isRed) {
        allianceIsRed = isRed;
        if (allianceIsRed)
            xDirection = 1;
        else
            xDirection = -1;
    }

    @Override
    public void runOpMode() {
        mooMoo = new RobotVV();
        mooMoo.init(hardwareMap);

        AutonFileHandler autonFile;
        autonFile = new AutonFileHandler();
        autonFile.readDataFromFile(hardwareMap.appContext);

        waitForStart();

        sleep(autonFile.waitTime);

        mooMoo.shooter.turnOn();

        mooMoo.driveTrain.drive(0,-autonFile.driveSpeed,0);
        sleep(1000);
        mooMoo.driveTrain.stop();
        sleep(500);

        mooMoo.loader.raise();
        sleep(mooMoo.loader.timeToRaise);
        mooMoo.loader.lower();
        sleep(mooMoo.loader.timeToLower);
        mooMoo.loader.raise();
        sleep(mooMoo.loader.timeToRaise);
        mooMoo.loader.lower();
        mooMoo.shooter.turnOff();

        mooMoo.driveTrain.drive(0,0,xDirection* autonFile.driveSpeed); //Turn the robot
        sleep(1000);    //autonFile.shooterTurnValue

        mooMoo.driveTrain.drive(xDirection* autonFile.driveSpeed,0,0);
        sleep(1000);     //autonFile.shooterStrafeValue

        mooMoo.driveTrain.drive(0,-autonFile.driveSpeed,0);
        sleep(2000);

        mooMoo.driveTrain.stop();
    }
}
