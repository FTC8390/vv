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
        mooMoo.shooter.turnOn();
    }

    @Override
    public void loop() {
        mooMoo.driveTrain.drive(gamepad1.right_stick_x, -gamepad1.right_stick_y, gamepad1.left_stick_x);

        //Set drive train direction
        if (gamepad1.a)
            mooMoo.driveTrain.setBack(DriveTrain.Color.GREEN);
        if (gamepad1.y)
            mooMoo.driveTrain.setBack(DriveTrain.Color.YELLOW);
        if (gamepad1.b)
            mooMoo.driveTrain.setBack(DriveTrain.Color.RED);
        if (gamepad1.x)
            mooMoo.driveTrain.setBack(DriveTrain.Color.BLUE);

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

        telemetry.addData("Status", "Running: " + runtime.toString());
    }

    @Override
    public void stop() {
        mooMoo.shooter.turnOff();
    }

}
