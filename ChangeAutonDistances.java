package ftc8390.vv;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by jmgu3 on 11/7/2016.
 */
@TeleOp(name = "ChangeAuton", group = "Test")
public class ChangeAutonDistances extends OpMode {

    AutonFileHandler autonFile;

    @Override
    public void init() {
        autonFile = new AutonFileHandler();
        autonFile.readDataFromFile(hardwareMap.appContext);
    }

    @Override
    public void init_loop() {

        telemetry.addData("A Left Trig = less, Bumper = more, DELAY START BY:", autonFile.waitTime);
        telemetry.addData("X is less, B is more, SHOOTER WAIT:", autonFile.shooterWait);
        telemetry.addData("LEFT_TRIGGER(2) is less, LEFT_BUMPER(2) is more, SHOOTER FORWARD AFTER SHOOT:", autonFile.shooterForwardAfterShoot);
        telemetry.addData("RIGHT_TRIGGER(2) is less, RIGHT_BUMPER(2) is more, SHOOTER FORWARD TIME:", autonFile.shooterForwardTime);
        telemetry.addData("A DPad_Up = more, DPad_down = less, DRIVE SPEED", autonFile.driveSpeed);
        telemetry.addData("Dpad_Left is more, Dpad_Right is less WHITE COLOR" , autonFile.whiteColor);
        telemetry.addData("Y is more, A is less DRIVE BACK_DIST" , autonFile.driveBackTime);




        if (gamepad2.left_bumper && gamepad2.right_bumper)
            autonFile.initializeValues();

        if(gamepad1.y)
            autonFile.driveBackTime += .5;
        if(gamepad1.a)
            autonFile.driveBackTime -= .5;

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

        if(gamepad1.dpad_left)
        {
            autonFile.whiteColor += .05;
        }
        if(gamepad1.dpad_right)
        {
            autonFile.whiteColor -= .05;
        }

        if(gamepad2.x)
        {
            autonFile.shooterWait -= .05;
        }
        if(gamepad2.b)
        {
            autonFile.shooterWait += .05;
        }





        if(gamepad2.right_trigger > .75)
        {
            autonFile.shooterForwardTime -= .05;
        }
        if(gamepad2.right_bumper)
        {
            autonFile.shooterForwardTime += .05;
        }



        if(gamepad2.left_bumper)
        {
            autonFile.shooterForwardAfterShoot += .05;
        }
        if(gamepad2.left_trigger > .75)
        {
            autonFile.shooterForwardAfterShoot -= .05;
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
