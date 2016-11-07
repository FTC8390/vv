package ftc8390.vv;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by jmgu3 on 11/7/2016.
 */
@TeleOp(name = "ChangeAuton")
public class ChangeAutonDistances extends OpMode {

    AutonFileHandler autonFile;

    public ChangeAutonDistances() {
        //gamepad1 = new Gamepad(); // to fix bug in 201601 SDK
        //gamepad2 = new Gamepad();
    }

    @Override
    public void init() {
        autonFile = new AutonFileHandler();
        autonFile.readDataFromFile(hardwareMap.appContext);
    }

    @Override
    public void init_loop() {

        telemetry.addData("A Left Trig = less, Bumper = more, DELAY START BY:", autonFile.waitTime);
        telemetry.addData("A DPad_Up = more, DPad_down = less, DRIVE SPEED", autonFile.driveSpeed);

        telemetry.addData("CLOSE B = more, X = less, DRIVE DISTANCE", autonFile.driveDistanceClose);

        telemetry.addData("FAR Y = more, A = less DRIVE DISTANCE", autonFile.driveDistanceFar);


        //First Drive Distance
        if (gamepad1.y) {
            autonFile.driveDistanceFar += 2;
        }

        if (gamepad1.a) {
            autonFile.driveDistanceFar -= 2;
        }
        //Wait Time at the start
        if (gamepad1.left_bumper) {
            autonFile.waitTime += 4;
        }

        if (gamepad1.left_trigger > .75) {
            autonFile.waitTime -= 4;
            if (autonFile.waitTime < 0) {
                autonFile.waitTime = 0;
            }
        }

        if (gamepad1.dpad_down) {
            autonFile.driveSpeed -= .001;
        }

        if (gamepad1.dpad_up) {
            autonFile.driveSpeed += .001;
        }

        if (gamepad1.x) {
            autonFile.driveDistanceClose -= 2;
        }
        if (gamepad1.b) {
            autonFile.driveDistanceClose += 2;
        }

    }

    @Override
    public void start() {
        autonFile.writeDataToFile(hardwareMap.appContext);
    }

    @Override
    public void loop() {

    }
}