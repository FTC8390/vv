package ftc8390.vv;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name = "BlueBeacon")  // @Autonomous(...) is the other common choice
@Disabled
public class AutonBeaconBlue extends AutonBeacon {

    public AutonBeaconBlue()
    {
        super( false );
    }

}
