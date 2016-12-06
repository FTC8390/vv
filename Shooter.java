package ftc8390.vv;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by jmgu3 on 11/7/2016.
 */
public class Shooter {

    public DcMotor leftMotor;
    public DcMotor rightMotor;

    public void init(HardwareMap hardwareMap) {


        leftMotor = hardwareMap.dcMotor.get("lsm");
        rightMotor = hardwareMap.dcMotor.get("rsm");
        // MAKE SURE TO PUT SHOOTER MOTORS INTO FLOAT MODE SO THEY DON'T BREAK!!!
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        //leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turnOn() {
        leftMotor.setPower(1);
        rightMotor.setPower(-1);
    }

    public void turnOff() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

}
