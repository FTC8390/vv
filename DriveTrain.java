package ftc8390.vv;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import static ftc8390.vv.DriveTrain.Color.BLUE;
import static ftc8390.vv.DriveTrain.Color.GREEN;
import static ftc8390.vv.DriveTrain.Color.RED;
import static ftc8390.vv.DriveTrain.Color.YELLOW;
import static ftc8390.vv.DriveTrain.CornerColor.BLUEYELLOW;
import static ftc8390.vv.DriveTrain.CornerColor.GREENBLUE;
import static ftc8390.vv.DriveTrain.CornerColor.REDGREEN;
import static ftc8390.vv.DriveTrain.CornerColor.YELLOWRED;
import static ftc8390.vv.DriveTrain.CornerDirection.BACKLEFT;
import static ftc8390.vv.DriveTrain.CornerDirection.BACKRIGHT;
import static ftc8390.vv.DriveTrain.CornerDirection.FRONTLEFT;
import static ftc8390.vv.DriveTrain.CornerDirection.FRONTRIGHT;

/**
 * Created by jmgu3 on 10/12/2016.
 */
public class DriveTrain {

    public enum Color {YELLOW, RED, GREEN, BLUE}
    public enum CornerColor {BLUEYELLOW, YELLOWRED, REDGREEN, GREENBLUE}
    public enum CornerDirection {FRONTLEFT, FRONTRIGHT, BACKRIGHT, BACKLEFT}
    public enum SpeedSetting {FAST, SLOW}
    
    private Color frontColor;
    private SpeedSetting speedMode;
    
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

        runWithoutEncoders();

        setFront(YELLOW);
    }

    public void runWithoutEncoders() {
        for (int i=0; i<4; i++) {
            motorByColor[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void runUsingEncoders() {
        for (int i=0; i<4; i++) {
            motorByColor[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void drive(double x, double y, double turn) {
        //Daniel speed change code
        double speedMultiplier = 1.0;
        switch (speedMode)
        {
            // lookup parameter for "fast mode"
            case SpeedSetting.FAST:
                 speedMultiplier = 1.0;
                break;
            case SpeedSetting.SLOW:
                // lookup slow speed parameter
                speedMultiplier = 0.5;
            default:
                speedMultiplier = 1.0;
        }
        
        motorByDirection[FRONTLEFT.ordinal()].setPower(Range.clip(speedMultiplier*(x + y + turn), -1, 1));
        motorByDirection[BACKRIGHT.ordinal()].setPower(Range.clip(speedMultiplier*(-x - y + turn), -1, 1));

        motorByDirection[FRONTRIGHT.ordinal()].setPower(Range.clip(speedMultiplier*(x - y + turn), -1, 1));
        motorByDirection[BACKLEFT.ordinal()].setPower(Range.clip(speedMultiplier*(-x + y + turn), -1, 1));
       
        
        
       /*Original Code
            motorByDirection[FRONTLEFT.ordinal()].setPower(Range.clip(x + y + turn, -1, 1));
            motorByDirection[BACKRIGHT.ordinal()].setPower(Range.clip(-x - y + turn, -1, 1));

            motorByDirection[FRONTRIGHT.ordinal()].setPower(Range.clip(x - y + turn, -1, 1));
            motorByDirection[BACKLEFT.ordinal()].setPower(Range.clip(-x + y + turn, -1, 1));
       */
        
    }

    public void stop() {
        drive(0,0,0);
    }

    //changes the front to the selected color and update motors
    public void setFront(Color color) {
        frontColor = color;
        for (int i = 0; i < 4; i++)
            motorByDirection[i] = motorByColor[(i + frontColor.ordinal()) % 4];
    }
    
    //changes the speed of the robot
    public void setSpeedMode(SpeedSetting speed) {
        speedMode = speed;
    }

    public void setBack(Color color) {
        switch (color)
        {
            case YELLOW:
                setFront(GREEN);
                break;
            case RED:
                setFront(BLUE);
                break;
            case GREEN:
                setFront(YELLOW);
                break;
            case BLUE:
                setFront(RED);
        }
    }

    public Color getFront() {
        return frontColor;
    }

}
