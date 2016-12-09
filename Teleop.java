package ftc8390.vv;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Teleop")  // @Autonomous(...) is the other common choice
//@Disabled
public class Teleop extends OpMode {
    RobotVV mooMoo;

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    double timeLeft = 120;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        mooMoo = new RobotVV();
        mooMoo.init(hardwareMap);
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
        // mooMoo.shooter.turnOn();
    }

    @Override
    public void loop() {
        timeLeft = 120 - runtime.seconds();

        //Set drive train direction
        if (gamepad1.a)
            mooMoo.driveTrain.setBack(DriveTrain.Color.GREEN);
        if (gamepad1.y)
            mooMoo.driveTrain.setBack(DriveTrain.Color.YELLOW);
        if (gamepad1.b)
            mooMoo.driveTrain.setBack(DriveTrain.Color.RED);
        if (gamepad1.x)
            mooMoo.driveTrain.setBack(DriveTrain.Color.BLUE);

        mooMoo.driveTrain.drive(gamepad1.right_stick_x, -gamepad1.right_stick_y, gamepad1.left_stick_x);

        //Shooter on and off
        if (gamepad1.dpad_up)
            mooMoo.shooter.turnOn();
        if (gamepad1.dpad_down)
            mooMoo.shooter.turnOff();
        if (gamepad2.dpad_up)
            mooMoo.shooter.turnOn();
        if (gamepad2.dpad_down)
            mooMoo.shooter.turnOff();

        //Sweeper on and off
        if (gamepad2.right_stick_y<-.5)
            mooMoo.sweeper.sweepOut();
        else if (gamepad2.right_stick_y>0.5)
            mooMoo.sweeper.sweepIn();
        else
            mooMoo.sweeper.stop();

        //Loader raise and lower
        if (gamepad2.right_bumper)
            mooMoo.loader.raise();
        else
            mooMoo.loader.lower();

        //Beacon pusher in and out
        if (gamepad1.left_bumper) {
            mooMoo.beaconPusher.leftOut();
            mooMoo.beaconPusher.rightOut();
        }
        if (gamepad1.left_trigger>0.5){
            mooMoo.beaconPusher.leftIn();
            mooMoo.beaconPusher.rightIn();
        }
        if (gamepad2.y) {
            mooMoo.beaconPusher.leftOut();
            mooMoo.beaconPusher.rightOut();
        }
        if (gamepad2.a){
            mooMoo.beaconPusher.leftIn();
            mooMoo.beaconPusher.rightIn();
        }
        if (gamepad2.x)
            mooMoo.beaconPusher.leftOut();
        if (gamepad2.b)
            mooMoo.beaconPusher.rightOut();


        telemetry.addData("Status", "Running: " + runtime.toString());
        telemetry.addLine("Time Left: " + timeLeft );
    }

    @Override
    public void stop() {
        mooMoo.shooter.turnOff();
    }

}
