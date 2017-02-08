package ftc8390.vv;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by jmgu3 on 11/7/2016.
 */
public class Shooter {

    public DcMotor leftMotor;
    public DcMotor rightMotor;
    public double shootSpeed;

    public void init(HardwareMap hardwareMap) {

        leftMotor = hardwareMap.dcMotor.get("lsm");
        rightMotor = hardwareMap.dcMotor.get("rsm");

        runUsingEncoders();

        // MAKE SURE TO PUT SHOOTER MOTORS INTO FLOAT MODE SO THEY DON'T BREAK!!!
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //shootSpeed = .23;
        shootSpeed = .531;
    }

    public void turnOn() {
        leftMotor.setPower(shootSpeed);
        rightMotor.setPower(-shootSpeed);
    }

    public void turnOff() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public void setPower(double power) {
        leftMotor.setPower(power);
        rightMotor.setPower(-power);
    }

    public void stopAndResetEncoders() {
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runUsingEncoders() {
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void runWithoutEncoders() {
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

}
