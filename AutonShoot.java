package ftc8390.vv;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Shooter Only")  // @Autonomous(...) is the other common choice
//@Disabled
public class AutonShoot extends LinearOpMode {

    RobotVV mooMoo;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        mooMoo = new RobotVV();
        mooMoo.init(hardwareMap);

        AutonFileHandler autonFile;
        autonFile = new AutonFileHandler();
        autonFile.readDataFromFile(hardwareMap.appContext);

        waitForStart();
        mooMoo.shooter.turnOn();

        mooMoo.driveTrain.drive(0,-autonFile.driveSpeed,0);
        sleep(1000);
        mooMoo.driveTrain.drive(0,0,0);
        sleep(500);
        mooMoo.loader.raise();
        sleep(500);
        mooMoo.loader.lower();
        sleep(1000);
        mooMoo.loader.raise();
        sleep(500);
        mooMoo.loader.lower();
        mooMoo.shooter.turnOff();
        mooMoo.driveTrain.drive(0,-autonFile.driveSpeed,0);
        sleep(2000);
        mooMoo.driveTrain.drive(0,0,0);

    }
}
