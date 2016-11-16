package ftc8390.vv;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by jmgu3 on 11/7/2016.
 */
public class Sweeper {

    public DcMotor sweeper;


    public void init(HardwareMap hardwareMap) {
        sweeper = hardwareMap.dcMotor.get("sweeper");
    }


    public void sweepIn()
    {
        sweeper.setPower(1);
    }

    public void sweepOut()
    {
        sweeper.setPower(-1);
    }

    public void stop()
    {
        sweeper.setPower(0);
    }
}
