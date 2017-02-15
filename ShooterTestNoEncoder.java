package ftc8390.vv;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by jmgu3 on 11/23/2016.
 */
@TeleOp(name = "ShooterTestNoEncoder", group = "Test")
public class ShooterTestNoEncoder extends OpMode {
    Loader loader;
    Shooter shooter;

    double shootSpeed;
    double shootSpeedHigher;
    double timeForHigher;
    ElapsedTime timer;

    boolean loadedLastTime = false;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        timer = new ElapsedTime();

        loader = new Loader();
        loader.init(hardwareMap);
        shooter = new Shooter();
        shooter.init(hardwareMap);
        shooter.runWithoutEncoders();
        shootSpeed = .1215;
        shootSpeedHigher = .17;
        timeForHigher = 1000;
        timer.reset();
    }

    public void loop() {
        if (gamepad1.dpad_left)
            shootSpeed += .0005;
        if (gamepad1.dpad_right)
            shootSpeed -= .0005;

        if (shootSpeed > 1)
            shootSpeed = 1;
        if (shootSpeed < 0)
            shootSpeed = 0;

        if (gamepad1.dpad_up)
            shootSpeedHigher += .0005;
        if (gamepad1.dpad_down)
            shootSpeedHigher -= .0005;

        if (shootSpeedHigher > 1)
            shootSpeedHigher = 1;
        if (shootSpeedHigher < 0)
            shootSpeedHigher = 0;

        if (gamepad1.y)
            timeForHigher += 50;
        if (gamepad1.a)
            timeForHigher -= 50;

        if (timeForHigher < 0)
            timeForHigher = 0;

        if (gamepad1.right_bumper && !loadedLastTime)
            timer.reset();

        if (timer.milliseconds() < timeForHigher)
            shooter.setPower(shootSpeedHigher);
        else
            shooter.setPower(shootSpeed);

        if (gamepad1.right_bumper)
            loader.raise();
        else
            loader.lower();

        loadedLastTime = gamepad1.right_bumper;

        telemetry.addLine("Shooter Speed: " + shootSpeed + "Dpad_Left is up, Dpad_Right is down");
        telemetry.addLine("Shooter Speed Higher: " + shootSpeedHigher + "Dpad_up is up, Dpad_down is down");
        telemetry.addLine("Time Delay: " + timeForHigher + "y is up, a is down");
    }
}
