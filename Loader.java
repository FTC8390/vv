package ftc8390.vv;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by jmgu3 on 11/7/2016.
 */
public class Loader {
   public Servo servo;

    public void init(HardwareMap hardwareMap) {
        servo = hardwareMap.servo.get("servo");
    }

}
