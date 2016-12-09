package ftc8390.vv;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by jmgu3 on 11/23/2016.
 */
@TeleOp(name = "ServoTest")
public class ServoTest extends OpMode {
    BeaconPusher beaconPusher;
    Loader loader;
    LineDetector lineDetector;

    double leftPusherPos;
    double rightPusherPos;
    double loaderPos;
    double loaderPos2;
    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        loader = new Loader();
        loader.init(hardwareMap);
        beaconPusher = new BeaconPusher();
        beaconPusher.init(hardwareMap);
        lineDetector = new LineDetector();
        lineDetector.init(hardwareMap);
        leftPusherPos = .5;
        rightPusherPos = .5;
        loaderPos = .5;
        loaderPos2 = .5;
    }

    public void loop() {
        if (gamepad1.left_bumper) {
            leftPusherPos -= .001;
        }
        if (gamepad1.left_trigger > .5) {
            leftPusherPos += .001;
        }
        if (leftPusherPos < 0)
            leftPusherPos = 0;
        if (leftPusherPos > 1)
            leftPusherPos = 1;
        beaconPusher.leftServo.setPosition(leftPusherPos);

        if (gamepad1.right_bumper) {
            rightPusherPos -= .001;
        }
        if (gamepad1.right_trigger > .5) {
            rightPusherPos += .001;
        }
        if (rightPusherPos < 0)
            rightPusherPos = 0;
        if (rightPusherPos > 1)
            rightPusherPos = 1;
        beaconPusher.rightServo.setPosition(rightPusherPos);

        if (gamepad1.dpad_up) {
            loaderPos += .001;
        }
        if (gamepad1.dpad_down) {
            loaderPos -= .001;
        }
        if (loaderPos < 0)
            loaderPos = 0;
        if (loaderPos > 1)
            loaderPos = 1;
        loader.servo.setPosition(loaderPos);

        if (gamepad1.y) {
            loaderPos2 += .001;
        }
        if (gamepad1.a) {
            loaderPos2 -= .001;
        }
        if (loaderPos2 < 0)
            loaderPos2 = 0;
        if (loaderPos2 > 1)
            loaderPos2 = 1;
        loader.servo2.setPosition(loaderPos2);

        telemetry.addData("Right Beacon Pusher Position: " + beaconPusher.rightServo.getPosition(), " Right Trigger is Up, Bumper is Down ");
        telemetry.addData("Left Beacon Pusher Position: " + beaconPusher.leftServo.getPosition(), " Left Trigger is Up, Bumper is Down ");
        telemetry.addData("Loader Position: " + loader.servo.getPosition(), " Dpad_Up is Up, Dpad_Down is Down ");
        telemetry.addData("Loader2 Position: " + loader.servo2.getPosition(), " Y is Up, A is Down ");
        telemetry.addData("A: Left Light Sensor Value: ", lineDetector.left.getLightDetected());
        telemetry.addData("A: Middle Light Sensor Value: ", lineDetector.middle.getLightDetected());
        telemetry.addData("A: Right Light Sensor Value: ", lineDetector.right.getLightDetected());
    }

}
