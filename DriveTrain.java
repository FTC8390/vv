package ftc8390.vv;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by jmgu3 on 10/12/2016.
 */
public class DriveTrain {
    DcMotor[] motor;
    DcMotor[] motorMap;
    final int yellow = 0;
    final int red = 1;
    final int green = 2;
    final int blue = 3;

    final int by = 0;
    final int yr = 1;
    final int rg = 2;
    final int gb = 3;

    final int FL = 0;
    final int FR = 1;
    final int BL = 2;
    final int BR = 3;

    int front;

    public DriveTrain() {
        motor = new DcMotor[4];
        motorMap = new DcMotor[4];
    }

    public void init(HardwareMap hardwareMap) {
        motor[by] = hardwareMap.dcMotor.get("by");
        motor[yr] = hardwareMap.dcMotor.get("yr");
        motor[rg] = hardwareMap.dcMotor.get("rg");
        motor[gb] = hardwareMap.dcMotor.get("gb");
        setFront(yellow);
    }

    public void drive(double x, double y, double turn) {
        motorMap[FL].setPower(Range.clip(x + y + turn, -1, 1));
        motorMap[FR].setPower(Range.clip(x - y + turn, -1, 1));
        motorMap[BR].setPower(Range.clip(-x - y + turn, -1, 1));
        motorMap[BL].setPower(Range.clip(-x + y + turn, -1, 1));
    }

    //changes the front to the selected side by button on gamepad
    public void setFront(int frontNum) {
        front = frontNum;
        for (int i = 0; i < 4; i++)
            motorMap[i] = motor[(i + front) % 4];
    }

}
