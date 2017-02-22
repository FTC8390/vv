package ftc8390.vv;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name = "RedBeaconAndShoot")  // @Autonomous(...) is the other common choice
@Disabled
public class AutonBeaconAndShootRed extends AutonBeaconAndShoot {

    public AutonBeaconAndShootRed()
    {
        super( true );
    }

}
