package ftc8390.vv;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import static ftc8390.vv.DriveTrain.Color.YELLOW;
import static ftc8390.vv.DriveTrain.CornerColor.BLUEYELLOW;
import static ftc8390.vv.DriveTrain.CornerColor.GREENBLUE;
import static ftc8390.vv.DriveTrain.CornerColor.REDGREEN;
import static ftc8390.vv.DriveTrain.CornerColor.YELLOWRED;
import static ftc8390.vv.DriveTrain.CornerLocation.BACKLEFT;
import static ftc8390.vv.DriveTrain.CornerLocation.BACKRIGHT;
import static ftc8390.vv.DriveTrain.CornerLocation.FRONTLEFT;
import static ftc8390.vv.DriveTrain.CornerLocation.FRONTRIGHT;

/**
 * Created by jmgu3 on 10/12/2016.
 */
public class DriveTrain {

    public enum Color {YELLOW, RED, GREEN, BLUE}

    public enum CornerColor {BLUEYELLOW, YELLOWRED, REDGREEN, GREENBLUE}

    public enum CornerLocation {FRONTLEFT, FRONTRIGHT, BACKRIGHT, BACKLEFT}

    private Color frontColor;

    public DcMotor[] motorByColor;
    public DcMotor[] motorByDirection;

    public DriveTrain() {
        motorByColor = new DcMotor[4];
        motorByDirection = new DcMotor[4];
    }

    public void init(HardwareMap hardwareMap) {
        motorByColor[BLUEYELLOW.ordinal()] = hardwareMap.dcMotor.get("by");
        motorByColor[YELLOWRED.ordinal()] = hardwareMap.dcMotor.get("yr");
        motorByColor[REDGREEN.ordinal()] = hardwareMap.dcMotor.get("rg");
        motorByColor[GREENBLUE.ordinal()] = hardwareMap.dcMotor.get("gb");

        setFront(YELLOW);
    }

    public void drive(double x, double y, double turn) {
        motorByDirection[FRONTLEFT.ordinal()].setPower(Range.clip(x + y + turn, -1, 1));
        motorByDirection[FRONTRIGHT.ordinal()].setPower(Range.clip(x - y + turn, -1, 1));
        motorByDirection[BACKRIGHT.ordinal()].setPower(Range.clip(-x - y + turn, -1, 1));
        motorByDirection[BACKLEFT.ordinal()].setPower(Range.clip(-x + y + turn, -1, 1));
    }

    //changes the front to the selected side by button on gamepad
    public void setFront(Color color) {
        frontColor = color;
        for (int i = 0; i < 4; i++)
            motorByDirection[i] = motorByColor[(i + frontColor.ordinal()) % 4];
    }

    public Color getFront() {
        return frontColor;
    }

}
