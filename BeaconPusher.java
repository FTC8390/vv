package ftc8390.vv;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by jmgu3 on 11/7/2016.
 */
public class BeaconPusher {
    public Servo rightServo;
    public Servo leftServo;

    public void init(HardwareMap hardwareMap) {
        rightServo = hardwareMap.servo.get("rightPusher");
        leftServo = hardwareMap.servo.get("leftPusher");
    }

    public void rightIn() {
        rightServo.setPosition(.5);
    }

    public void rightOut() {
        rightServo.setPosition(.5);
    }

    public void leftIn() {
        leftServo.setPosition(.5);
    }

    public void leftOut() {
        leftServo.setPosition(.5);
    }
}
