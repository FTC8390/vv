package ftc8390.vv;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by jmgu3 on 10/12/2016.
 */
public class RobotVV {
    DriveTrain driveTrain;
    Shooter shooter;
    Loader loader;
    Sweeper sweeper;
    BeaconPusher beaconPusher;
    BeaconColorDetector beaconColorDetector;

    public RobotVV ()
    {
        driveTrain = new DriveTrain();
        shooter = new Shooter();
        beaconColorDetector = new BeaconColorDetector();
        beaconPusher = new BeaconPusher();
        loader = new Loader();
        sweeper = new Sweeper();
    }

    public void init (HardwareMap hardwareMap)
    {
        driveTrain.init(hardwareMap);
        shooter.init(hardwareMap);
        beaconColorDetector.init(hardwareMap);
        beaconPusher.init(hardwareMap);
        loader.init(hardwareMap);
        sweeper.init(hardwareMap);
    }
}
