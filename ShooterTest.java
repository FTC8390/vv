package ftc8390.vv;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by jmgu3 on 11/23/2016.
 */
@TeleOp(name = "ShooterTest" , group = "Test")
public class ShooterTest extends OpMode {
    Loader loader;
    Shooter shooter;


    double shootSpeed;
    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        loader = new Loader();
        loader.init(hardwareMap);
        shooter = new Shooter();
        shooter.init(hardwareMap);
        shootSpeed = 0;
    }

    public void loop() {
        if(gamepad1.dpad_left)
        {
            shootSpeed += .0005;
        }
        if(gamepad1.dpad_right)
        {
            shootSpeed -= .0005;
        }
        if(shootSpeed > 1)
            shootSpeed = 1;
        if(shootSpeed < 0)
            shootSpeed = 0;
        shooter.setPower(shootSpeed);


        if (gamepad2.right_bumper)
            loader.raise();
        else
            loader.lower();

       telemetry.addLine("Shooter Speed: " + shootSpeed + "Dpad_Left is up, Dpad_Right is down");
    }

}
