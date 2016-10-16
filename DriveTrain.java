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
    final int blue = 3 ;

    final int blueYellow = 0;
    final int yellowRed = 1;
    final int redGreen = 2;
    final int greenBlue = 3;

    final int frontLeft = 0;
    final int frontRight = 1;
    final int backRight = 2;
    final int backLeft = 3;

    int front;

    public DriveTrain() {
        motor = new DcMotor[4];
        motorMap = new DcMotor[4];
    }

    public void init(HardwareMap hardwareMap) {
        motor[blueYellow] = hardwareMap.dcMotor.get("by");
        motor[yellowRed] = hardwareMap.dcMotor.get("yr");
        motor[redGreen] = hardwareMap.dcMotor.get("rg");
        motor[greenBlue] = hardwareMap.dcMotor.get("gb");

        setFront(yellow);
    }

    public void drive(double x, double y, double turn) {
        motorMap[frontLeft].setPower(Range.clip(x + y + turn, -1, 1));
        motorMap[frontRight].setPower(Range.clip(x - y + turn, -1, 1));
        motorMap[backRight].setPower(Range.clip(-x - y + turn, -1, 1));
        motorMap[backLeft].setPower(Range.clip(-x + y + turn, -1, 1));
    }

    //changes the front to the selected side by button on gamepad
    public void setFront(int frontNum) {
        front = frontNum;
        for (int i = 0; i < 4; i++)
            motorMap[i] = motor[(i + front) % 4];
    }

}
