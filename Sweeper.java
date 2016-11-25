package ftc8390.vv;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by jmgu3 on 11/7/2016.
 */
public class Sweeper {

    public DcMotor motor;


    public void init(HardwareMap hardwareMap) {
        motor = hardwareMap.dcMotor.get("sweeper");
    }


    public void sweepIn()
    {
        motor.setPower(1);
    }

    public void sweepOut()
    {
        motor.setPower(-1);
    }

    public void stop()
    {
        motor.setPower(0);
    }
}
