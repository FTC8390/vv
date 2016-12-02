package ftc8390.vv;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import for_camera_opmodes.LinearOpModeCamera;

@Autonomous(name = "RedBeacon")  // @Autonomous(...) is the other common choice
public class AutonBeaconRed extends AutonBeacon {

    public AutonBeaconRed()
    {
        super( true );
    }

}
