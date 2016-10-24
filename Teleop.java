package ftc8390.vv;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Teleop")  // @Autonomous(...) is the other common choice
//@Disabled
public class Teleop extends OpMode {
    RobotVV angus;

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        angus = new RobotVV();
        angus.init(hardwareMap);

    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {

        angus.driveTrain.drive(gamepad1.right_stick_x, -gamepad1.right_stick_y, gamepad1.left_stick_x);

        if (gamepad1.a)
            angus.driveTrain.setFront(DriveTrain.Color.GREEN);
        if (gamepad1.y)
            angus.driveTrain.setFront(DriveTrain.Color.YELLOW);
        if (gamepad1.b)
            angus.driveTrain.setFront(DriveTrain.Color.RED);
        if (gamepad1.x)
            angus.driveTrain.setFront(DriveTrain.Color.BLUE);

        telemetry.addData("Status", "Running: " + runtime.toString());
    }

    @Override
    public void stop() {
    }

}
