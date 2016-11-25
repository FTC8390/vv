package ftc8390.vv;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by jmgu3 on 11/7/2016.
 */
public class BeaconPusher {
public Servo  rightPusher;
    public Servo  leftPusher;
    public void init(HardwareMap hardwareMap) {
        rightPusher = hardwareMap.servo.get("rightPusher");
        leftPusher = hardwareMap.servo.get("leftPusher");
    }
    public void rightIn(){
        rightPusher.setPosition(.5);
    }
    public void rightOut(){
        rightPusher.setPosition(.5);
    }
    public void leftIn(){
        leftPusher.setPosition(.5);
    }
    public void leftOut(){
        leftPusher.setPosition(.5);
    }
}
