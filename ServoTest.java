package ftc8390.vv;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by jmgu3 on 11/23/2016.
 */
@TeleOp(name = "ServoTest")
public class ServoTest extends OpMode {
    RobotVV mooMoo;

    double leftPusherVar;
    double rightPusherVar;
    double loaderPos;
    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        mooMoo = new RobotVV();
        mooMoo.init(hardwareMap);
        leftPusherVar = .5;
        rightPusherVar = .5;
        loaderPos = .5;
    }
    public ServoTest()
    {

    }


     public void loop()
     {
         if(gamepad1.left_bumper)
         {
             leftPusherVar -= .001;
         }
         if(gamepad1.left_trigger > .5)
         {
             leftPusherVar += .001;
         }
         if(leftPusherVar < 0)
             leftPusherVar = 0;
         if(leftPusherVar > 1)
             leftPusherVar = 1;
         mooMoo.beaconPusher.leftPusher.setPosition(leftPusherVar);

         if(gamepad1.right_bumper)
         {
             rightPusherVar -= .001;
         }
         if(gamepad1.right_trigger > .5)
         {
             rightPusherVar += .001;
         }
         if(rightPusherVar < 0)
             rightPusherVar = 0;
         if(rightPusherVar > 1)
             rightPusherVar = 1;
         mooMoo.beaconPusher.rightPusher.setPosition(rightPusherVar);

        if(gamepad1.dpad_up)
        {
            loaderPos += .001;
        }
         if(gamepad1.dpad_down)
         {
             loaderPos -= .001;
         }

         if(loaderPos < 0)
             loaderPos = 0;
         if(loaderPos > 1)
             loaderPos = 1;
         mooMoo.loader.servo.setPosition(loaderPos);

         telemetry.addData("Right Beacon Pusher Position: " + mooMoo.beaconPusher.rightPusher.getPosition() ,  " Right Trigger is Up, Bumper is Down ");
         telemetry.addData("Left Beacon Pusher Position: " + mooMoo.beaconPusher.leftPusher.getPosition() ,  " Left Trigger is Up, Bumper is Down ");
         telemetry.addData("Loader Position: " + mooMoo.loader.servo.getPosition() ,  " Dpad_Up is Up, Dpad_Down is Down ");
         telemetry.addData("Color Value: ", mooMoo.lineDetector.middle.getLightDetected());
     }

}
