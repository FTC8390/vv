package ftc8390.vv;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by jmgu3 on 10/12/2016.
 */
public class RobotVV {
    DriveTrain driveTrain;
    public RobotVV ()
    {
        driveTrain = new DriveTrain();
    }
    public void init (HardwareMap hardwareMap)
    {
        driveTrain.init(hardwareMap);
    }
}
